package view.controllers.minigames.truecolor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import controller.minigames.truecolor.TrueColorController;
import controller.minigames.truecolor.TrueColorControllerImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.minigames.truecolor.Colors;
import model.minigames.truecolor.StatusColor;
import utility.Pair;
import view.DimensionCalculatorImpl;
import view.SceneType;
import view.controllers.minigames.AbstractMiniGameControllerFx;

/**
 * Represents the True Color View.
 */
public class TrueColorFxImpl extends AbstractMiniGameControllerFx implements TrueColorFx {

    private static final String TXTFILL = "-fx-text-fill: ";
    private static final String BACKGROUND = "-fx-background-color: ";
    private static final String TEXT_COLOR = "-fx-text-fill: ";
    private static final String RADIUS = "-fx-background-radius: ";
    private static final String FONT = "-fx-font-size: %dpx;";
    private static final double TEXT_PERCENTAGE = 0.07;
    private static final double LBL_PERCENTAGE = 0.06;
    private static final double PERCENTAGE = 0.05;
    private static final double BTN_WIDTH = 0.4;
    private static final double BTN_HEIGHT = 0.15;
    private static final int SHADOWX = 4;
    private static final int SHADOWY = 6;
    private final DropShadow shadow = new DropShadow();
    private TrueColorController trueColorController;
    private List<Button> meaningColorBtnList;
    private List<Button> trueColorBtnList;
    private int meanColorSize;
    private int trueColorSize;
    private Button currentAnswer;

    private Stage stage;

    @FXML
    private GridPane topGrid;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane gridLbl;
    @FXML
    private GridPane bottomGrid;
    @FXML
    private VBox vBoxId;

    private final EventHandler<ActionEvent> event = (e) -> {
        this.currentAnswer.setStyle(TEXT_COLOR  + "#000000;"
                + String.format(FONT, (int) (TEXT_PERCENTAGE * this.stage.getWidth() / 2)));
    };

    @FXML
    private void buttonClicked(final MouseEvent event) throws IOException { // NOPMD
        final String selectedAnswer = ((Button) event.getSource()).getText();
        this.currentAnswer = (Button) event.getSource();
        this.trueColorController.check(selectedAnswer);
    }

    private void setDropShadow() {
        this.shadow.setOffsetX(SHADOWX);
        this.shadow.setOffsetY(SHADOWY);
    }

    private void setStyleButton(final List<Pair<Colors, Colors>> listColor, final List<Button> listBtn) {
        Objects.requireNonNull(listColor);
        Objects.requireNonNull(listBtn);
        final int size = listBtn.size();
        for (int i = 0; i < size; i++) {
            listBtn.get(i).setText(listColor.get(i).getX().toString());
            listBtn.get(i).setStyle(TXTFILL + listColor.get(i).getY().getName() + ";" + RADIUS + "5em;"
                    + String.format(FONT, (int) (PERCENTAGE * this.stage.getWidth())));
            listBtn.get(i).setPrefSize(stage.getWidth() * BTN_WIDTH, stage.getHeight() * BTN_HEIGHT);
            if (this.meaningColorBtnList == listBtn) {
                listBtn.get(i).setEffect(this.shadow);
            }
        }
    }

    private void prepareButtons() {
        this.meaningColorBtnList = new LinkedList<>();
        this.trueColorBtnList = new LinkedList<>();
        this.addButton(this.meaningColorBtnList, this.meanColorSize);
        this.addButton(this.trueColorBtnList, this.trueColorSize);
    }

    private void addButton(final List<Button> btnList, final Integer size) {
        for (int i = 0; i < size; i++) {
            final Button button = new Button();
            btnList.add(button);
            this.vBoxId.getChildren().add(button);
        }
    }
 
    private void animation() {
        final Timeline timeline = new Timeline();
        final KeyFrame kf = new KeyFrame(Duration.millis(300), event);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void resizeNode() {
        this.stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            calculator.setNodeFontStyle(topGrid, LBL_PERCENTAGE, (double) newVal / 2);
            calculator.setNodeFontStyle(gridLbl, LBL_PERCENTAGE, (double) newVal / 2);
            calculator.setNodeFontStyle(bottomGrid, TEXT_PERCENTAGE, (double) newVal / 2);
        });
        final double halfStageSize = stage.getWidth() / 2;
        calculator.setNodeFontStyle(topGrid, LBL_PERCENTAGE, halfStageSize);
        calculator.setNodeFontStyle(gridLbl, LBL_PERCENTAGE, halfStageSize);
        calculator.setNodeFontStyle(bottomGrid, TEXT_PERCENTAGE, halfStageSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final DifficultyLevel difficultyLevel) {
        this.getProgressBar().setStyle("-fx-accent: #49618C");
        this.resizeNode();
        this.trueColorController = new TrueColorControllerImpl(this, difficultyLevel);
        this.setDropShadow();
        this.prepareButtons();
        this.trueColorController.startTimer();
        this.trueColorController.startGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void next() {
        this.trueColorController.resetGame();
        this.trueColorController.startGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setButtons(final Map<StatusColor, List<Pair<Colors, Colors>>> colorMap) {
        Objects.requireNonNull(colorMap, "Map of Random Color is null");
        this.setStyleButton(colorMap.get(StatusColor.MEANCOLOR), this.meaningColorBtnList);
        this.setStyleButton(colorMap.get(StatusColor.TRUECOLOR), this.trueColorBtnList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void alternativeSetButton(final List<Pair<Colors, Colors>> list) {
        Objects.requireNonNull(list);
        final int size = this.trueColorBtnList.size();
        for (int i = 0; i < size; i++) {
            this.trueColorBtnList.get(i).setText("");
            this.trueColorBtnList.get(i).setStyle(BACKGROUND + list.get(i).getY().getName() + ";" 
                + String.format(FONT, (int) (PERCENTAGE * this.stage.getWidth())));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSizeBtnList(final Map<StatusColor, List<Pair<Colors, Colors>>> colorMap) {
        this.meanColorSize = colorMap.get(StatusColor.MEANCOLOR).size();
        this.trueColorSize = colorMap.get(StatusColor.TRUECOLOR).size();
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
        this.trueColorController.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showAnswer(final boolean bool) {
        this.currentAnswer.setStyle(String.format(FONT, (int) (TEXT_PERCENTAGE * this.stage.getWidth() / 2)) + TEXT_COLOR + (bool ? "#66FF66" : "#FF0000"));
        this.animation();
    }
}
