package clashclass.shop;

import clashclass.elements.buildings.VillageElementData;
import clashclass.gamestate.GameStateManager;
import clashclass.resources.Player;
import clashclass.resources.ResourceType;
import clashclass.saveload.BattleVillageDecoderImpl;
import clashclass.saveload.PlayerVillageDecoderImpl;
import clashclass.saveload.VillageEncoderImpl;
import clashclass.saveload.VillageSaveLoadManager;
import clashclass.saveload.SimpleFileWriterImpl;
import clashclass.village.manager.PlayerVillageController;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a {@link ShopMenuModel} implementation.
 */
public class ShopMenuModelImpl implements ShopMenuModel {
    private final ShopManager shopManager;
    private GameStateManager gameStateManager;
    private PlayerVillageController playerVillageController;

    /**
     * Constructs the model.
     *
     * @param player the player reference
     */
    public ShopMenuModelImpl(final Player player) {
        this.shopManager = new ShopManagerImpl(List.of(
                new ShopItemImpl(VillageElementData.GOLD_EXTRACTOR, ResourceType.GOLD,
                        BuildingPrice.GOLD_EXTRACTOR.getPrice(), player),
                new ShopItemImpl(VillageElementData.GOLD_STORAGE, ResourceType.GOLD,
                        BuildingPrice.GOLD_STORAGE.getPrice(), player),
                new ShopItemImpl(VillageElementData.ELIXIR_EXTRACTOR, ResourceType.ELIXIR,
                        BuildingPrice.ELIXIR_EXTRACTOR.getPrice(), player),
                new ShopItemImpl(VillageElementData.ELIXIR_STORAGE, ResourceType.ELIXIR,
                        BuildingPrice.ELIXIR_STORAGE.getPrice(), player),
                new ShopItemImpl(VillageElementData.WALL, ResourceType.GOLD,
                        BuildingPrice.WALL.getPrice(), player),
                new ShopItemImpl(VillageElementData.ARMY_CAMP, ResourceType.GOLD,
                        BuildingPrice.ARMY_CAMP.getPrice(), player),
                new ShopItemImpl(VillageElementData.CANNON, ResourceType.GOLD,
                        BuildingPrice.CANNON.getPrice(), player),
                new ShopItemImpl(VillageElementData.ARCHER_TOWER, ResourceType.ELIXIR,
                        BuildingPrice.ARCHER_TOWER.getPrice(), player)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShopManager getShopManager() {
        return this.shopManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setGameStateManager(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
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
        public boolean tryToBuyItem(final ShopItem item) {
        if (this.shopManager.canAfford(item)) {
            this.shopManager.buyItem(item);
            //this.playerVillageController.addBuilding(item.getBuilding());
            final var saveLoadManager = new VillageSaveLoadManager(
                    new VillageEncoderImpl(),
                    new PlayerVillageDecoderImpl(),
                    new BattleVillageDecoderImpl(),
                    new SimpleFileWriterImpl(),
                    Paths.get("Villages-Data")
            );
            try {
                saveLoadManager.saveVillage(this.playerVillageController.getPlayerVillage(),
                        "player-village");
            } catch (final IOException e) {
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public void setPlayerVillageController(final PlayerVillageController playerVillageController) {
        this.playerVillageController = playerVillageController;
    }
}
