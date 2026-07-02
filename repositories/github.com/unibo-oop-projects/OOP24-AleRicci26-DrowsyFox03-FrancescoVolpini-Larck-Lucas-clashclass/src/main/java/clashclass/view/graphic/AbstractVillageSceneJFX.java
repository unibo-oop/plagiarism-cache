package clashclass.view.graphic;

import clashclass.battle.manager.BattleManagerControllerImpl;
import clashclass.battle.manager.BattleManagerModelImpl;
import clashclass.battle.manager.BattleManagerViewJavaFXImpl;
import clashclass.engine.GameEngine;
import clashclass.engine.GameEngineImpl;
import clashclass.gamestate.GameStateManager;
import clashclass.gamestate.GameStateManagerImpl;
import clashclass.village.manager.PlayerVillageControllerImpl;
import clashclass.village.manager.PlayerVillageModelImpl;
import clashclass.village.manager.PlayerVillageViewJavaFXImpl;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a {@link AbstractBaseScene} extension used for game initialization.
 */
public abstract class AbstractVillageSceneJFX extends AbstractBaseScene {
    private final GameStateManager gameStateManager;

    /**
     * Constructs the scene.
     *
     * @param window the window
     * @param stage the stage
     * @param sceneTitle the title of the scene
     * @param playerCsvPath the player village file path
     * @param battleCsvPath the battle village fila path
     */
    public AbstractVillageSceneJFX(
            final Window window,
            final Stage stage,
            final String sceneTitle,
            final Path playerCsvPath,
            final Path battleCsvPath) {
        super(window);
        final AnchorPane root = new AnchorPane();

        final Scene scene = new Scene(root, getWindowWidth(), getWindowHeight());
        stage.setScene(scene);
        stage.setTitle(sceneTitle);
        stage.setFullScreen(true);

        final Canvas canvas = new Canvas(getWindowWidth(), getWindowHeight());
        canvas.setId("canvas");
        final var gc = canvas.getGraphicsContext2D();
        final var graphics = new GraphicJavaFXImpl(gc, canvas, getWindowWidth(), getWindowHeight());

        root.getChildren().add(canvas);
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());

        final GameEngine gameEngine = new GameEngineImpl(Optional.of(graphics));
        this.gameStateManager = new GameStateManagerImpl(
                gameEngine,
                () -> new PlayerVillageControllerImpl(
                        new PlayerVillageModelImpl(playerCsvPath),
                        new PlayerVillageViewJavaFXImpl(root)),
                () -> new BattleManagerControllerImpl(
                        new BattleManagerModelImpl(playerCsvPath, battleCsvPath),
                        new BattleManagerViewJavaFXImpl(root, gameEngine)
                )
        );

        this.gameStateManager.setStatePlayerVillage();
        stage.setOnCloseRequest(event -> {
            this.gameStateManager.stopEngine();
        });

        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeScene() {
        this.gameStateManager.startEngine();
    }
}
