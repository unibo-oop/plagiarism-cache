package view.controllers.minigames.colorgama;

import java.util.Objects;

import controller.minigames.colorgama.ColorGamaController;
import controller.minigames.colorgama.ColorGamaControllerImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.minigames.colorgama.ColorMethod;
import model.minigames.colorgama.ColorValuesIterator;
import view.SceneType;
import view.controllers.minigames.AbstractMiniGameControllerFx;

/**
 * This class implements {@link ColorGamaFx}.
 *
 */
public class ColorGamaFxImpl extends AbstractMiniGameControllerFx implements ColorGamaFx {

    private static final String NULL_ARG = " passed is null";
    private static final String RIGHT_IMAGE_PATH = "/images/tick.png";
    private static final String WRONG_IMAGE_PATH = "/images/cross.png";
    private static final int VALUE = 300;

    @FXML
    private GridPane buttonsGrid;

    @FXML
    private ImageView image;

    @FXML
    private Label questionTitle;

    @FXML
    private Label questionColor;

    private ColorGamaController colorGamaController;
    private ColorConverter converter;
    private ColorValuesIterator iterator;

    private final EventHandler<MouseEvent> bntClicked = (l) -> {
        final Button answer = (Button) l.getSource();
        this.colorGamaController.checkAnswer(this.buttonsGrid.getChildren().indexOf(answer));
    };

    private final EventHandler<ActionEvent> rightEvent = (l) -> {
        this.image.setVisible(false);
        this.colorGamaController.resetGame();
    };

    private final EventHandler<ActionEvent> wrongEvent = (l) -> this.image.setVisible(false);

    private void showAnimation(final boolean isRight) {
        final Timeline timeline = new Timeline();
        final KeyFrame frame = isRight ? new KeyFrame(Duration.millis(VALUE), rightEvent)
                : new KeyFrame(Duration.millis(VALUE), wrongEvent);
        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final DifficultyLevel difficultyLevel) {
        Objects.requireNonNull(difficultyLevel, "DifficultyLevel" + NULL_ARG);
        this.getProgressBar().setStyle("-fx-accent:  #1897f6");
        this.colorGamaController = new ColorGamaControllerImpl(this, difficultyLevel);
        this.colorGamaController.startTimer();
        this.colorGamaController.startGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGrid(final int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Button bt = new Button();
                bt.setPrefSize(VALUE, VALUE);
                bt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                bt.addEventHandler(MouseEvent.MOUSE_CLICKED, bntClicked);
                buttonsGrid.add(bt, i, j);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void setColorIterator(final ColorValuesIterator iterator, final ColorMethod method) {
        Objects.requireNonNull(iterator, "ColorValuesIterator" + NULL_ARG);
        Objects.requireNonNull(method, "ColorMethod" + NULL_ARG);
        this.iterator = iterator;
        this.converter = new ColorConverterImpl(method);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void renderNext(final String questionSentence) {
        Objects.requireNonNull(questionSentence, "QuestionSentence" + NULL_ARG);
        this.buttonsGrid.getChildren().forEach((b) -> {
            final Color color = this.converter.convertToColor(this.iterator.next()).get();
            ((Button) b).setBackground(new Background(new BackgroundFill(color, new CornerRadii(2), new Insets(2))));
        });
        this.questionTitle.setText(questionSentence);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showQuestionColor() {
        final Color color = this.converter.convertToColor(this.iterator.getRightColorValues()).get();
        this.questionColor.setBackground(new Background(new BackgroundFill(color, new CornerRadii(2), new Insets(2))));
        this.questionColor.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideQuestionColor() {
        this.questionColor.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showEndGame(final int finalScore) {
        Platform.runLater(() -> {
            this.getController().updateProgress(finalScore);
            this.getView().loadProgress(SceneType.END_GAME.getFilePath());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopTimer() {
        this.colorGamaController.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showImage(final boolean check) {
        final Image image = check ? new Image(RIGHT_IMAGE_PATH) : new Image(WRONG_IMAGE_PATH);
        this.image.setVisible(true);
        this.image.setImage(image);
        this.showAnimation(check);
    }
}
