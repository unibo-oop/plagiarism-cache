package view.generic;

import controller.LevelManager;
import controller.LevelManagerImpl;
import controller.SceneManager;
import controller.Score;
import controller.ScoreImpl;
import controller.SoundManager;
import controller.SoundManagerImpl;
import controller.player.PlayerInput;
import controller.player.PlayerInputImpl;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import utilities.Utilities;
import view.animations.FireworksAnimation;
import view.menu.StartMenu;

/**
 * Class of Level Complete.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class LevelComplete {

    private static final LevelComplete SINGLETON = new LevelComplete();
    private final SoundManager soundManager;
    private final LevelManager levelManager;
    private final Score score;
    private final PlayerInput playerInput;
    private final Scene levelCompleteScene;
    private final FadeTransition buttonContainerFadeIn;
    private String backgroundImgPath;
    private final ImageView backgroundImg;

    /**
     * Constructor that creates all elements that appear on screen in Level
     * Complete.
     */
    private LevelComplete() {
        this.soundManager = SoundManagerImpl.get();
        this.levelManager = LevelManagerImpl.get();
        this.score = ScoreImpl.get();
        this.playerInput = PlayerInputImpl.get();
        final StackPane root = new StackPane();
        this.levelCompleteScene = new Scene(root);
        this.backgroundImg = new ImageView(new Image("/levelOne/levelOne.png"));
        final ImageView logoLevelComplete = new ImageView(new Image("/images/levelCompleteLogo.png"));
        final Button continueBtn = new Button("Continue");
        final Button menuBtn = new Button("Back to menu");
        final HBox buttonContainer = new HBox();
        this.buttonContainerFadeIn = new FadeTransition(Duration.millis(1500), buttonContainer);
        final FireworksAnimation firework1 = new FireworksAnimation(5, 5, -Utilities.W / 4, -Utilities.H / 3, 1200);
        final FireworksAnimation firework2 = new FireworksAnimation(7, 7, -Utilities.W / 4, Utilities.H / 5, 1500);
        final FireworksAnimation firework3 = new FireworksAnimation(10, 10, Utilities.W / 4, -Utilities.H / 6, 1700);

        this.levelCompleteScene.getStylesheets()
                .add(this.getClass().getResource("styleLevelComplete.css").toExternalForm());
        continueBtn.setId("continueBtn");
        menuBtn.setId("menuBtn");

        this.backgroundImg.fitWidthProperty().bind(root.widthProperty());
        this.backgroundImg.fitHeightProperty().bind(root.heightProperty());

        logoLevelComplete.fitWidthProperty().bind(root.widthProperty());
        logoLevelComplete.fitHeightProperty().bind(root.heightProperty());

        continueBtn.setPrefWidth(Utilities.W / 3);
        continueBtn.setPrefHeight(Utilities.H / 28);
        continueBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 28)));
        menuBtn.setPrefWidth(Utilities.W / 3);
        menuBtn.setPrefHeight(Utilities.H / 28);
        menuBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 28)));

        buttonContainer.getChildren().addAll(menuBtn, continueBtn);

        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(new Insets(0, Utilities.H / 84, Utilities.H / 84, Utilities.H / 84));
        buttonContainer.setSpacing(Utilities.H / 3);

        this.buttonContainerFadeIn.setFromValue(0.0);
        this.buttonContainerFadeIn.setToValue(1.0);
        this.buttonContainerFadeIn.play();

        root.getChildren().add(this.backgroundImg);
        root.getChildren().add(logoLevelComplete);
        root.getChildren().add(buttonContainer);
        firework1.addImageToPane(root);
        firework2.addImageToPane(root);
        firework3.addImageToPane(root);

        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                score.resetTimer();
                levelManager.resetLevelNumber();
                soundManager.stopMusic();
                levelManager.resetLevel();
                playerInput.setRepeat(false);
                StartMenu.get().soundRestart();
                SceneManager.get().sceneSetter(StartMenu.get().getScene());
            }
        });

        menuBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });

        continueBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                soundManager.stopMusic();
                levelManager.resetLevel();
                playerInput.setRepeat(false);
                levelManager.chooseLevel().playMusic();
                score.startScore();
                SceneManager.get().sceneSetter(levelManager.chooseLevel().getGameScene());
            }
        });

        continueBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });
    }

    /**
     * Restarts Level Complete's buttons' fade.
     */
    public void fadeStart() {
        this.buttonContainerFadeIn.play();
    }

    /**
     * Sets right background image based on which level the player has completed.
     */
    public void setBackground() {
        this.backgroundImg.setImage(new Image(getBackgroundImgPath()));
    }

    /**
     * Restarts Level Complete's music.
     */
    public void soundStart() {
        this.soundManager.startMusic("/songs/levelComplete.mp3", false);
    }

    /**
     * Getter of the background image's path.
     * 
     * @return backgroundImgPath
     */
    public String getBackgroundImgPath() {
        return this.backgroundImgPath;
    }

    /**
     * Setter of backgroundImgPath.
     * 
     * @param backgroundImgPath
     *            the background image's path
     * 
     */
    public void setBackgroundImgPath(final String backgroundImgPath) {
        this.backgroundImgPath = backgroundImgPath;
    }

    /**
     * Getter of the Level Complete's scene.
     * 
     * @return levelCompleteScene
     */
    public Scene getScene() {
        return this.levelCompleteScene;
    }

    /**
     * Returns an instance of Level Complete.
     * 
     * @return an instance of Level Complete
     */
    public static LevelComplete get() {
        return SINGLETON;
    }
}
