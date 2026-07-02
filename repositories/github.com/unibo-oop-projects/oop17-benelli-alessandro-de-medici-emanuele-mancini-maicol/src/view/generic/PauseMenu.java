package view.generic;

import controller.GameStatus;
import controller.GameStatusImpl;
import controller.LevelManager;
import controller.LevelManagerImpl;
import controller.SceneManager;
import controller.Score;
import controller.ScoreImpl;
import controller.SoundManager;
import controller.SoundManagerImpl;
import controller.player.PlayerInput;
import controller.player.PlayerInputImpl;
import controller.player.PlayerPath;
import controller.player.PlayerPathImpl;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import utilities.Utilities;
import view.menu.StartMenu;

/**
 * Class of Pause Menu.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class PauseMenu {

    private static final PauseMenu SINGLETON = new PauseMenu();
    private final SoundManager soundManager;
    private final GameStatus gameStatus;
    private final LevelManager levelManager;
    private final PlayerPath playerPath;
    private final Score score;
    private final Obstacle obstacle;
    private final PlayerInput playerInput;
    private final Scene pauseScene;
    private final Slider volumeSlider;

    /**
     * Constructor that creates all elements that appear on screen in Pause Menu.
     */
    private PauseMenu() {
        this.soundManager = SoundManagerImpl.get();
        this.gameStatus = GameStatusImpl.get();
        this.levelManager = LevelManagerImpl.get();
        this.playerPath = PlayerPathImpl.get();
        this.score = ScoreImpl.get();
        this.obstacle = ObstacleImpl.get();
        this.playerInput = PlayerInputImpl.get();
        final StackPane root = new StackPane();
        this.pauseScene = new Scene(root);
        final Button gameBtn = new Button("Back to game");
        final Button menuBtn = new Button("Back to menu");
        final VBox buttonContainer = new VBox();
        final ImageView backgroundPauseMenu = new ImageView(new Image("/images/backgroundPauseMenu.png"));
        this.volumeSlider = new Slider();
        final FadeTransition buttonContainerFadeIn = new FadeTransition(Duration.millis(1000), buttonContainer);

        this.pauseScene.getStylesheets().add(this.getClass().getResource("stylePauseMenu.css").toExternalForm());
        menuBtn.setId("menuBtn");
        gameBtn.setId("gameBtn");

        backgroundPauseMenu.fitWidthProperty().bind(root.widthProperty());
        backgroundPauseMenu.fitHeightProperty().bind(root.heightProperty());

        menuBtn.setPrefWidth(Utilities.W / 3);
        menuBtn.setPrefHeight(Utilities.H / 12);
        menuBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 24)));
        gameBtn.setPrefWidth(Utilities.W / 3);
        gameBtn.setPrefHeight(Utilities.H / 12);
        gameBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 24)));

        buttonContainer.getChildren().add(gameBtn);
        buttonContainer.getChildren().add(menuBtn);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(new Insets(0, 0, Utilities.H / 3.5, 0));
        buttonContainer.setSpacing(Utilities.H / 40);

        buttonContainerFadeIn.setFromValue(0.0);
        buttonContainerFadeIn.setToValue(1.0);
        buttonContainerFadeIn.play();

        this.volumeSlider.setMin(0);
        this.volumeSlider.setMax(1);
        this.volumeSlider.setMaxWidth(Utilities.W / 5);
        this.volumeSlider.setTranslateY(-Utilities.H / 13.2);

        this.volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> ov, final Number oldVal, final Number newVal) {
                soundManager.updateMusicVolume(newVal.doubleValue());
            }
        });

        root.getChildren().add(backgroundPauseMenu);
        root.getChildren().add(buttonContainer);
        root.getChildren().add(this.volumeSlider);

        menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                gameStatus.setGamePause(false);
                playerPath.clearPath();
                obstacle.clearObstaclePositions();
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

        gameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                levelManager.chooseLevel().playMusic();
                score.startScore();
                gameStatus.setGamePause(false);
                SceneManager.get().sceneSetter(levelManager.chooseLevel().getGameScene());
            }
        });

        gameBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });
    }

    /**
     * Updates the value of the volume. It allows the player to set his/her
     * preferred volume.
     */
    public void updateVolumeSlider() {
        this.volumeSlider.setValue(this.soundManager.getMusicVolume());
    }

    /**
     * Getter of the Pause Menu's scene.
     * 
     * @return pauseScene
     */
    public Scene getScene() {
        return this.pauseScene;
    }

    /**
     * Returns an instance of PauseMenu.
     * 
     * @return an instance of PauseMenu
     */
    public static PauseMenu get() {
        return SINGLETON;
    }
}
