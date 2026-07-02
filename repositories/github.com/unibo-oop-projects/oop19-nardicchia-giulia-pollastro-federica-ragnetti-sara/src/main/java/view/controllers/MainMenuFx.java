package view.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.DimensionCalculatorImpl;
import view.SceneType;

/**
 * The fx controller for the main menu scene.
 *
 */
public class MainMenuFx extends AbstractSceneController implements FxNormalScene {

    private static final double IMG_PERCENTAGE = 0.0023;
    private static final double BUTTON_PERCENTAGE = 0.08;
    private DimensionCalculatorImpl calculator;

    @FXML
    private Button trainingBtn;

    @FXML
    private Button statisticsBtn;

    @FXML
    private Button howToPlayBtn;

    @FXML
    private GridPane menuGrid;

    @FXML
    private ImageView dailyImg;

    @FXML
    private ImageView statisticImg;

    @FXML
    private ImageView infoImg;

    @FXML
    private void startTraining() throws IOException { // NOPMD
        this.getView().switchScene(SceneType.TRAINING_AREA.getFilePath());
    }

    @FXML
    private void showStatistics() throws IOException { // NOPMD
        this.getView().loadProgress(SceneType.STATISTICS.getFilePath());
    }

    @FXML
    private void showInfo() throws IOException { // NOPMD
        this.getView().switchScene(SceneType.HOW_TO_PLAY.getFilePath());
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public void init() {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        this.calculator = new DimensionCalculatorImpl();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.calculator.setNodeFontStyle(menuGrid, BUTTON_PERCENTAGE, (double) newVal / 2);
            menuGrid.getChildren().forEach(c -> {
                if (c instanceof ImageView) {
                    this.calculator.setImageViewDimension((ImageView) c, IMG_PERCENTAGE, (double) newVal / 2);
                }
            });
        });

        final double halfStageSize = stage.getWidth() / 2;
        this.calculator.setNodeFontStyle(menuGrid, BUTTON_PERCENTAGE, halfStageSize);
        menuGrid.getChildren().forEach(c -> {
            if (c instanceof ImageView) {
                this.calculator.setImageViewDimension((ImageView) c, IMG_PERCENTAGE, halfStageSize);
            }
        });
    }
}

