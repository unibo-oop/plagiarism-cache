package view.menu;

import controller.Controller;
import controller.ControllerImpl;
import controller.SceneManager;
import controller.SoundManager;
import controller.SoundManagerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import utilities.Utilities;
import view.animations.CharAnimation;
import view.animations.FishAnimation;
import view.animations.OneDirectionAnimation;
import view.generic.Tutorial;

/**
 * Class of Start Menu.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class StartMenu {

    private static final StartMenu SINGLETON = new StartMenu();
    private final Controller controller;
    private final SoundManager soundManager;
    private final Scene menuScene;

    /**
     * Constructor that creates all elements that appear on screen in Start Menu.
     */
    private StartMenu() {
        this.controller = new ControllerImpl();
        this.soundManager = SoundManagerImpl.get();
        final StackPane root = new StackPane();
        this.menuScene = new Scene(root);
        final Button startBtn = new Button("Play!");
        final Button settingsBtn = new Button("Settings");
        final Button quitBtn = new Button("Quit");
        final VBox buttonContainer = new VBox();
        final FadeTransition buttonContainerFadeIn = new FadeTransition(Duration.millis(1500), buttonContainer);
        final ImageView backgroundStartMenu = new ImageView(new Image("/images/backgroundStartMenu.png"));
        final FishAnimation movingFish = new FishAnimation(42, 42, Utilities.H / 4, -Utilities.H / 6, Utilities.W / 3,
                -Utilities.W / 3);
        final FishAnimation noMovingFish = new FishAnimation(42, 42, (Utilities.W / 42) * 17, Utilities.H / 9);
        final OneDirectionAnimation movingLeaf = new OneDirectionAnimation("/images/leaf.png", 84, 84,
                -(Utilities.W / 42) * 17, Utilities.H / 3, 215, 40000000);
        final CharAnimation noMovingChar = new CharAnimation(42, 21, (Utilities.W / 42) * 20, Utilities.H / 10,
                "/charAnimationFrame/char.png");

        this.soundManager.startMusic("/songs/menuMusic.mp3", true);

        this.menuScene.getStylesheets().add(this.getClass().getResource("styleStartMenu.css").toExternalForm());
        startBtn.setId("startBtn");
        settingsBtn.setId("settingsBtn");
        quitBtn.setId("quitBtn");

        backgroundStartMenu.fitWidthProperty().bind(root.widthProperty());
        backgroundStartMenu.fitHeightProperty().bind(root.heightProperty());

        startBtn.setPrefWidth(Utilities.W / 2);
        startBtn.setPrefHeight(Utilities.H / 6);
        startBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 12)));
        settingsBtn.setPrefWidth(Utilities.W / 4);
        settingsBtn.setPrefHeight(Utilities.H / 12);
        settingsBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 24)));
        quitBtn.setPrefWidth(Utilities.W / 4);
        quitBtn.setPrefHeight(Utilities.H / 12);
        quitBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 24)));

        buttonContainer.getChildren().addAll(startBtn, settingsBtn, quitBtn);

        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(new Insets(0, 0, Utilities.H / 5, 0));
        buttonContainer.setSpacing(Utilities.H / 50);

        buttonContainerFadeIn.setFromValue(0.0);
        buttonContainerFadeIn.setToValue(1.0);
        buttonContainerFadeIn.play();

        root.getChildren().add(backgroundStartMenu);
        noMovingFish.addImageToPane(root);
        movingFish.addImageToPane(root);
        noMovingChar.addImageToPane(root);
        movingLeaf.addImageToPane(root);
        root.getChildren().add(buttonContainer);

        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                soundManager.stopMusic();
                SceneManager.get().sceneSetter(Tutorial.get().getScene());
            }
        });

        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                SettingsMenu.get().updateVolumeSlider();
                SceneManager.get().sceneSetter(SettingsMenu.get().getScene());
            }
        });

        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                controller.quit();
            }
        });

        startBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });

        settingsBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });

        quitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });
    }

    /**
     * Restarts Start Menu's music.
     */
    public void soundRestart() {
        this.soundManager.startMusic("/songs/menuMusic.mp3", true);
    }

    /**
     * Getter of the Start Menu's scene.
     * 
     * @return menuScene
     */
    public Scene getScene() {
        return this.menuScene;
    }

    /**
     * Returns an instance of StartMenu.
     * 
     * @return an instance of StartMenu
     */
    public static StartMenu get() {
        return SINGLETON;
    }
}
