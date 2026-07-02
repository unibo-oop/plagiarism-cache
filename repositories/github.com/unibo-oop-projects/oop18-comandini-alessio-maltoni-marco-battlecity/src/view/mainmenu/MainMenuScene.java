package view.mainmenu;

import java.util.List;

import controller.Controller;
import enums.GameMode;
import enums.GameScene;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.entities.GameEntity;
import view.GameSceneInterface;
import view.JavaFXView;

/**
 * Class for the Main Menu. Implemented in JavaFX. The class extends the
 * JavaFX's class Scene.
 */
public final class MainMenuScene extends Scene implements GameSceneInterface {

    // Scene Magic Numbers.
    private static final double SCENE_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double SCENE_HEIGHT = JavaFXView.STAGE_DIMESNION;
    private static final double ANIMATION_DURATION_SECONDS = 5.0;
    private static final double ANIMATION_RESCTRICTED_MS = 0.1;

    // Field that store the current item selected in the menu.
    private int currentItem;
    // Animation of the scene.
    private TranslateTransition translate;
    // The controller of the game.
    private final Controller controller;
    // The boolean for the animation.
    private final boolean animation;
    private boolean isOnAnimation;

    /**
     * Constructor of the class.
     * 
     * @param controller  the controller of the game.
     * @param animation   parameter for do or not the animation
     * @param currentItem the current item selected in the menu
     */
    public MainMenuScene(final Controller controller, final boolean animation, final int currentItem) {
        super(new MainMenuPanel(controller), SCENE_WIDTH, SCENE_HEIGHT);
        this.controller = controller;
        this.animation = animation;
        this.currentItem = currentItem;
        this.translate = new TranslateTransition(Duration.seconds(ANIMATION_DURATION_SECONDS), getRoot());
        this.isOnAnimation = false;
        init();
    }

    /*
     * Method that initialize the scene.
     */
    private void init() {
        if (animation) {
            getRoot().setLayoutY(SCENE_HEIGHT);
            animateRoot();
        } else {
            ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(true);
        }
        setFill(Color.BLACK);
        setKeyPressed();
    }

    /*
     * Method that animate the root.
     */
    private void animateRoot() {
        isOnAnimation = true;
        translate.setToY(-SCENE_HEIGHT);
        translate.play();
        translate.setOnFinished(event -> {
            isOnAnimation = false;
            ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(true);
        });
    }

    /*
     * Set the behavior of the menu when a key is pressed.
     */
    private void setKeyPressed() {
        setOnKeyPressed(event -> {
            // Up Arrow Key.
            if (!isOnAnimation && event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(false);
                    ((MainMenuPanel) getRoot()).getMenuItems().get(--currentItem).setActive(true);
                } else if (currentItem == 0) {
                    ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(false);
                    currentItem = ((MainMenuPanel) getRoot()).getMenuItems().size() - 1;
                    ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(true);
                }
            }
            // Down Arrow Key.
            if (!isOnAnimation && event.getCode() == KeyCode.DOWN) {
                if (currentItem < ((MainMenuPanel) getRoot()).getMenuItems().size() - 1) {
                    ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(false);
                    ((MainMenuPanel) getRoot()).getMenuItems().get(++currentItem).setActive(true);
                } else if (currentItem == ((MainMenuPanel) getRoot()).getMenuItems().size() - 1) {
                    ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(false);
                    currentItem = 0;
                    ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(true);
                }
            }
            // Enter Key.
            if (!isOnAnimation && event.getCode() == KeyCode.ENTER) {
                if (currentItem == 0) {
                    controller.setGameMode(GameMode.ONE_PLAYER);
                    controller.setGameScene(GameScene.TRANSITION_STAGE_1P);
                } else if (currentItem == 1) {
                    controller.setGameMode(GameMode.TWO_PLAYER);
                    controller.setGameScene(GameScene.TRANSITION_STAGE_2P);
                } else if (currentItem == 2) {
                    controller.setGameMode(GameMode.CONSTRUCTION);
                    controller.setGameScene(GameScene.TRANSITION_CONSTRUCTION);
                }
            } else if (isOnAnimation && event.getCode() == KeyCode.ENTER) {
                translate.stop();
                translate = new TranslateTransition(Duration.millis(ANIMATION_RESCTRICTED_MS), getRoot());
                translate.setToY(-SCENE_HEIGHT);
                translate.play();
                isOnAnimation = false;
                ((MainMenuPanel) getRoot()).getMenuItems().get(currentItem).setActive(true);
            }
        });
    }

    @Override
    public void render(final List<GameEntity> gameEntities) {
        // Nothing to render.
    }

}
