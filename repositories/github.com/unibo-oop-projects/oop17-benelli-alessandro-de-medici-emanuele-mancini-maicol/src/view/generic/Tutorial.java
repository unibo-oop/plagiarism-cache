package view.generic;

import controller.LevelManager;
import controller.LevelManagerImpl;
import controller.SceneManager;
import controller.Score;
import controller.ScoreImpl;
import controller.SoundManager;
import controller.SoundManagerImpl;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utilities.Utilities;

/**
 * Class of Tutorial.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class Tutorial {

    private static final Tutorial SINGLETON = new Tutorial();
    private final LevelManager levelManager;
    private final Score score;
    private final SoundManager soundManager;
    private final Scene tutorialScene;

    /**
     * Constructor that creates all elements that appear on screen in Tutorial.
     */
    private Tutorial() {
        this.levelManager = LevelManagerImpl.get();
        this.soundManager = SoundManagerImpl.get();
        this.score = ScoreImpl.get();
        final StackPane root = new StackPane();
        this.tutorialScene = new Scene(root);
        final ImageView backgroundImg = new ImageView(new Image("/images/tutorial.png"));
        final Button continueBtn = new Button("Continue");
        final VBox buttonContainer = new VBox();
        final FadeTransition buttonContainerFadeIn = new FadeTransition(Duration.millis(1500), buttonContainer);

        this.tutorialScene.getStylesheets().add(this.getClass().getResource("styleTutorial.css").toExternalForm());
        continueBtn.setId("continueBtn");

        backgroundImg.fitWidthProperty().bind(root.widthProperty());
        backgroundImg.fitHeightProperty().bind(root.heightProperty());

        continueBtn.setPrefWidth(Utilities.W / 4);
        continueBtn.setPrefHeight(Utilities.H / 28);
        continueBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 28)));

        buttonContainer.getChildren().add(continueBtn);

        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        buttonContainer.setPadding(new Insets(0, Utilities.H / 84, Utilities.H / 84, 0));
        buttonContainer.setSpacing(Utilities.H / 50);

        buttonContainerFadeIn.setFromValue(0.0);
        buttonContainerFadeIn.setToValue(1.0);
        buttonContainerFadeIn.play();

        root.getChildren().add(backgroundImg);
        root.getChildren().add(buttonContainer);

        continueBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
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
     * Getter of the Tutorial's scene.
     * 
     * @return tutorialScene
     */
    public Scene getScene() {
        return this.tutorialScene;
    }

    /**
     * Returns an instance of Tutorial.
     * 
     * @return an instance of Tutorial
     */
    public static Tutorial get() {
        return SINGLETON;
    }
}
