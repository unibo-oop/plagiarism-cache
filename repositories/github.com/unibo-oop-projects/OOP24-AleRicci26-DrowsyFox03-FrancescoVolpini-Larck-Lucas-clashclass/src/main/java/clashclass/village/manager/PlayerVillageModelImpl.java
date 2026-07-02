package clashclass.village.manager;

import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.VillageElementData;
import clashclass.gamestate.GameStateManager;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.shop.ShopMenuController;
import clashclass.shop.ShopMenuControllerImpl;
import clashclass.shop.ShopMenuModelImpl;
import clashclass.shop.ShopMenuView;
import clashclass.village.Village;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a {@link PlayerVillageModel} implementation.
 */
public class PlayerVillageModelImpl implements PlayerVillageModel {
    private final Village playerVillage;
    private GameStateManager gameStateManager;
    private ShopMenuController shopMenuController;

    /**
     * Constructs the model.
     *
     * @param playerVillageCsvPath the player village file path
     */
    public PlayerVillageModelImpl(final Path playerVillageCsvPath) {
        this.playerVillage = this.loadVillage(playerVillageCsvPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.shopMenuController.setGameStateManager(gameStateManager);
    }

    private Village loadVillage(final Path csvPath) {
        final var decoder = new PlayerVillageDecoderImpl();
        decoder.setComponentFactory(new ComponentFactoryImpl());
        final var csvData = this.readCsvFile(csvPath);
        return decoder.decode(csvData);
    }

    private String readCsvFile(final Path csvPath) {
        if (Files.exists(csvPath)) {
            try {
                return Files.readString(csvPath);
            } catch (final IOException e) {
                return "";
            }
        }
        final var fileStream = Objects.requireNonNull(ClassLoader
                .getSystemResourceAsStream(csvPath.toString().replace("\\", "/")));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public Village getPlayerVillage() {
        return this.playerVillage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.playerVillage.getGroundObjects().forEach(GameObject::destroy);
        this.playerVillage.getGameObjects().forEach(GameObject::destroy);
        this.shopMenuController.clearScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildShop(final PlayerVillageController controller, final ShopMenuView view) {
        this.shopMenuController = new ShopMenuControllerImpl(
                new ShopMenuModelImpl(this.playerVillage.getPlayer()),
                view
        );
        this.shopMenuController.setPlayerVillageController(controller);
        this.shopMenuController.hide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShop() {
        this.shopMenuController.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBuilding(final VillageElementData buildingType) {
        //final var factoryMapper = new BuildingFactoryMapper<>(new PlayerBuildingFactoryImpl());
        //final var building = factoryMapper.getFactoryFor(buildingType).apply(new VectorInt2D(0,0));
        //final var tileData = building.getComponentOfType(GridTileData2D.class).get();
    }
}
