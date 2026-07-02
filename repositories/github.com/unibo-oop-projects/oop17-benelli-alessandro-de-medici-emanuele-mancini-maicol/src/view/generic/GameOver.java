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
import view.menu.StartMenu;

/**
 * Class of Game Over.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class GameOver {
    private static final GameOver SINGLETON = new GameOver();
    private final SoundManager soundManager;
    private final LevelManager levelManager;
    private final Score score;
    private final PlayerInput playerInput;
    private final Scene gameOverScene;
    private final FadeTransition buttonContainerFadeIn;

    /**
     * Constructor that creates all elements that appear on screen in Game Over.
     */
    private GameOver() {
        this.soundManager = SoundManagerImpl.get();
        this.levelManager = LevelManagerImpl.get();
        this.score = ScoreImpl.get();
        this.playerInput = PlayerInputImpl.get();
        final StackPane root = new StackPane();
        this.gameOverScene = new Scene(root);
        final ImageView backgroundImg = new ImageView(new Image("/images/gameOver.png"));
        final Button restartBtn = new Button("Restart");
        final Button menuBtn = new Button("Back to menu");
        final HBox buttonContainer = new HBox();
        this.buttonContainerFadeIn = new FadeTransition(Duration.millis(1500), buttonContainer);

        this.gameOverScene.getStylesheets().add(this.getClass().getResource("styleGameOver.css").toExternalForm());
        restartBtn.setId("restartBtn");
        menuBtn.setId("menuBtn");

        backgroundImg.fitWidthProperty().bind(root.widthProperty());
        backgroundImg.fitHeightProperty().bind(root.heightProperty());

        restartBtn.setPrefWidth(Utilities.W / 3);
        restartBtn.setPrefHeight(Utilities.H / 28);
        restartBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 28)));
        menuBtn.setPrefWidth(Utilities.W / 3);
        menuBtn.setPrefHeight(Utilities.H / 28);
        menuBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 28)));

        buttonContainer.getChildren().addAll(menuBtn, restartBtn);

        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(new Insets(0, Utilities.H / 84, Utilities.H / 84, Utilities.H / 84));
        buttonContainer.setSpacing(Utilities.H / 3);

        this.buttonContainerFadeIn.setFromValue(0.0);
        this.buttonContainerFadeIn.setToValue(1.0);
        this.buttonContainerFadeIn.play();

        root.getChildren().add(backgroundImg);
        root.getChildren().add(buttonContainer);

        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
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

        restartBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        restartBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });
    }

    /**
     * Restarts Game Over's buttons' fade.
     */
    public void fadeStart() {
        this.buttonContainerFadeIn.play();
    }

    /**
     * Restarts Game Over's music.
     */
    public void soundStart() {
        this.soundManager.startMusic("/songs/gameOverSound.mp3", false);
    }

    /**
     * Getter of the Game Over's scene.
     * 
     * @return gameOverScene
     */
    public Scene getScene() {
        return this.gameOverScene;
    }

    /**
     * Returns an instance of GameOver.
     * 
     * @return an instance of GameOver
     */
    public static GameOver get() {
        return SINGLETON;
    }
}
