package view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.score.Progress;
import view.DimensionCalculatorImpl;
import view.SceneType;

/**
 * The fx controller for the end game scene.
 */
public class EndGameFx extends AbstractSceneController implements FxHandleDataScene {

    private static final double IMG_PERCENTAGE = 0.003;
    private static final double TEXT_PERCENTAGE = 0.08;
    private static final String WELL_DONE = "/images/award.png";
    private static final String WORK_HARD = "/images/hard-work.png";
    private static final double BORDER = 5;

    @FXML
    private GridPane grid;

    @FXML
    private Label score;

    @FXML
    private ImageView homeImg;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView resultImg;

    private void resizeNode() {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            calculator.setNodeFontStyle(grid, TEXT_PERCENTAGE, (double) newVal / 2);
            calculator.setImageViewDimension(homeImg, IMG_PERCENTAGE, (double) newVal / 2);
            calculator.setImageViewDimension(resultImg, IMG_PERCENTAGE, (double) newVal / 2);
        });

        final double halfStageSize = stage.getWidth() / 2;
        calculator.setNodeFontStyle(grid, TEXT_PERCENTAGE, halfStageSize);
        calculator.setImageViewDimension(homeImg, IMG_PERCENTAGE, halfStageSize);
        calculator.setImageViewDimension(resultImg, IMG_PERCENTAGE, halfStageSize);
    }

    @FXML
    private void backHome() { // NOPMD
        this.getView().switchScene(SceneType.MAIN_MENU.getFilePath());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Progress progress) {
        final int currentScore = progress.getCurrentScore();
        final int bestScore = progress.getCurrentBest();
        this.score.setText("Score: " + currentScore + "\nBest: " + bestScore);
        score.setBorder(new Border(
                new BorderStroke(Color.valueOf("#49618c"), BorderStrokeStyle.DASHED, null, new BorderWidths(BORDER))));
        this.messageLabel.setText(currentScore < bestScore ? "You can do better!" : "Keep it going!");
        this.resultImg.setImage(
                currentScore < bestScore ? new Image(WORK_HARD) : new Image(WELL_DONE));
        this.resizeNode();
    }
}
