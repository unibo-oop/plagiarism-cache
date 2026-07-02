package view.gamegui;

import java.util.List;

import controller.Controller;
import enums.GameMode;
import enums.GameScene;
import enums.KeyInput;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;
import view.gamegui.subscene.GameSubScene;

/**
 * Class for the game GUI. Here the user see the game on monitor.
 */
public final class GameGuiScene extends Scene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION;
    private static final double SUB_SCENE_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 12.8;
    private static final double SUB_SCENE_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 12.8;
    private static final double ENEMY_ICONS_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 1.09;
    private static final double ENEMY_ICONS_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 11.0;
    private static final double LIFE_PANEL_P1_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 1.09;
    private static final double LIFE_PANEL_P1_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 1.85;
    private static final double LIFE_PANEL_P2_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 1.09;
    private static final double LIFE_PANEL_P2_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 1.57;
    private static final double STAGE_PANEL_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 1.09;
    private static final double STAGE_PANEL_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 1.30;

    // The controller of the game.
    private final Controller controller;
    // The root of the scene.
    private final AnchorPane root;
    // The sub-scene of the game.
    private final GameSubScene gameSubScene;
    // The icons of the enemy to be spawned.
    private final EnemyIconsPanel enemyIcons;
    // The Life Panel for player 1.
    private final LifePanel lifePanel1;
    // The Life Panel for player 2.
    private LifePanel lifePanel2;
    // The Stage Panel.
    private final StagePanel stagePanel;

    /**
     * The constructor of the scene.
     * 
     * @param controller the controller of the game.
     */
    public GameGuiScene(final Controller controller) {
        super(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);
        this.controller = controller;
        this.gameSubScene = new GameSubScene();
        this.enemyIcons = new EnemyIconsPanel();
        this.lifePanel1 = new LifePanel(1);
        if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            this.lifePanel2 = new LifePanel(2);
        }
        this.stagePanel = new StagePanel(this.controller.getCurrentStageNumber());
        this.root = (AnchorPane) getRoot();
        init();
    }

    /*
     * Method that initialize the scene.
     */
    private void init() {
        setFill(Color.GREY);
        setRootBackground();

        /* The sub-scene of the game */
        gameSubScene.setLayoutX(SUB_SCENE_LAYOUT_X);
        gameSubScene.setLayoutY(SUB_SCENE_LAYOUT_Y);
        root.getChildren().add(gameSubScene);

        /* The enemy-icons panel */
        enemyIcons.setLayoutX(ENEMY_ICONS_LAYOUT_X);
        enemyIcons.setLayoutY(ENEMY_ICONS_LAYOUT_Y);
        root.getChildren().add(enemyIcons);

        /* The life panel for the player 1 */
        lifePanel1.setLayoutX(LIFE_PANEL_P1_LAYOUT_X);
        lifePanel1.setLayoutY(LIFE_PANEL_P1_LAYOUT_Y);
        root.getChildren().add(lifePanel1);

        if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            /* The life panel for the player 2 */
            lifePanel2.setLayoutX(LIFE_PANEL_P2_LAYOUT_X);
            lifePanel2.setLayoutY(LIFE_PANEL_P2_LAYOUT_Y);
            root.getChildren().add(lifePanel2);
        }

        /* The stage panel with the number of the level */
        stagePanel.setLayoutX(STAGE_PANEL_LAYOUT_X);
        stagePanel.setLayoutY(STAGE_PANEL_LAYOUT_Y);
        root.getChildren().add(stagePanel);

        setKeyPressed();
        setKeyReleased();
    }

    /*
     * This method set the root background.
     */
    private void setRootBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        root.setBackground(background);
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        enemyIcons.drawIcons(controller.getGameStatus().getResidueTank());
        lifePanel1.drawNLives(controller.getGameStatus().getPlayerLife().get(0));
        if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
            lifePanel2.drawNLives(controller.getGameStatus().getPlayerLife().get(1));
        }
        gameSubScene.render(gameEntities);
    }

    /*
     * Set the behavior of the menu when a key is pressed.
     */
    private void setKeyPressed() {
        setOnKeyPressed(event -> {

            /* COMMANDS FOR PLAYER ONE */
            if (controller.getGameMode().equals(GameMode.ONE_PLAYER)
                    || controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
                // Up Arrow Key.
                if (event.getCode() == KeyCode.UP) {
                    controller.notifyKeyPressedEvent(KeyInput.UP_PLAYER_ONE);
                }
                // Right Arrow Key.
                if (event.getCode() == KeyCode.RIGHT) {
                    controller.notifyKeyPressedEvent(KeyInput.RIGHT_PLAYER_ONE);
                }
                // Down Arrow Key.
                if (event.getCode() == KeyCode.DOWN) {
                    controller.notifyKeyPressedEvent(KeyInput.DOWN_PLAYER_ONE);
                }
                // Left Arrow Key.
                if (event.getCode() == KeyCode.LEFT) {
                    controller.notifyKeyPressedEvent(KeyInput.LEFT_PLAYER_ONE);
                }
                // Space Bar Key.
                if (event.getCode() == KeyCode.SPACE) {
                    controller.notifyKeyPressedEvent(KeyInput.FIRE_PLAYER_ONE);
                }
            }

            /* COMMANDS FOR PLAYER TWO */
            if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
                // W Key.
                if (event.getCode() == KeyCode.W) {
                    controller.notifyKeyPressedEvent(KeyInput.UP_PLAYER_TWO);
                }
                // D Key.
                if (event.getCode() == KeyCode.D) {
                    controller.notifyKeyPressedEvent(KeyInput.RIGHT_PLAYER_TWO);
                }
                // S Key.
                if (event.getCode() == KeyCode.S) {
                    controller.notifyKeyPressedEvent(KeyInput.DOWN_PLAYER_TWO);
                }
                // A Key.
                if (event.getCode() == KeyCode.A) {
                    controller.notifyKeyPressedEvent(KeyInput.LEFT_PLAYER_TWO);
                }
                // F Key.
                if (event.getCode() == KeyCode.F) {
                    controller.notifyKeyPressedEvent(KeyInput.FIRE_PLAYER_TWO);
                }
            }

            /* OTHER COMMANDS */

            // Enter Key.
            if (event.getCode() == KeyCode.ENTER) {
                controller.stopGame();
                controller.setGameScene(GameScene.POINTS_RECAP);
            }
        });
    }

    /*
     * Set the behavior of the menu when a key is released.
     */
    private void setKeyReleased() {
        setOnKeyReleased(event -> {

            /* COMMANDS FOR PLAYER ONE */
            if (controller.getGameMode().equals(GameMode.ONE_PLAYER)
                    || controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
                // Up Arrow Key.
                if (event.getCode() == KeyCode.UP) {
                    controller.notifyKeyReleasedEvent(KeyInput.UP_PLAYER_ONE);
                }
                // Right Arrow Key.
                if (event.getCode() == KeyCode.RIGHT) {
                    controller.notifyKeyReleasedEvent(KeyInput.RIGHT_PLAYER_ONE);
                }
                // Down Arrow Key.
                if (event.getCode() == KeyCode.DOWN) {
                    controller.notifyKeyReleasedEvent(KeyInput.DOWN_PLAYER_ONE);
                }
                // Left Arrow Key.
                if (event.getCode() == KeyCode.LEFT) {
                    controller.notifyKeyReleasedEvent(KeyInput.LEFT_PLAYER_ONE);
                }
                // Space Bar Key.
                if (event.getCode() == KeyCode.SPACE) {
                    controller.notifyKeyReleasedEvent(KeyInput.FIRE_PLAYER_ONE);
                }
            }

            /* COMMANDS FOR PLAYER TWO */
            if (controller.getGameMode().equals(GameMode.TWO_PLAYER)) {
                // W Key.
                if (event.getCode() == KeyCode.W) {
                    controller.notifyKeyReleasedEvent(KeyInput.UP_PLAYER_TWO);
                }
                // D Key.
                if (event.getCode() == KeyCode.D) {
                    controller.notifyKeyReleasedEvent(KeyInput.RIGHT_PLAYER_TWO);
                }
                // S Key.
                if (event.getCode() == KeyCode.S) {
                    controller.notifyKeyReleasedEvent(KeyInput.DOWN_PLAYER_TWO);
                }
                // A Key.
                if (event.getCode() == KeyCode.A) {
                    controller.notifyKeyReleasedEvent(KeyInput.LEFT_PLAYER_TWO);
                }
                // F Key.
                if (event.getCode() == KeyCode.F) {
                    controller.notifyKeyReleasedEvent(KeyInput.FIRE_PLAYER_TWO);
                }
            }
        });
    }

}
