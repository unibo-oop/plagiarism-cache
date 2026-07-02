package view.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.TrainingArea;
import view.DimensionCalculatorImpl;
import view.SceneType;

import java.util.Arrays;

/**
 * The fx controller for the Training Area selector.
 * 
 */
public class TrainingAreaFx extends AbstractSceneController implements FxNormalScene {

    private static final double IMG_PERCENT = 0.003;
    private static final double AREAS_PERCENT = 0.07;

    @FXML
    private ImageView backImg;

    @FXML
    private VBox areasBox;

    @FXML
    private GridPane areasGrid;

    @FXML
    private GridPane bottomGrid;

    @FXML
    private void areaClicked(final MouseEvent event) throws IOException { // NOPMD
        final String selectedArea = ((Button) event.getSource()).getText();
        final TrainingArea area = Arrays.asList(TrainingArea.values()).stream()
                .filter(e -> e.getName().equals(selectedArea)).findAny().get();
        this.getController().setArea(area);
        populateList(area);
        this.getView().switchScene(SceneType.GAME_SELECTOR.getFilePath());
    }

    @FXML
    private void goBack() throws IOException { // NOPMD
        this.getView().switchScene(SceneType.MAIN_MENU.getFilePath());
    }

    private void populateList(final TrainingArea selectedArea) {
        this.getGameList().clear();
        this.getGameList().addAll(selectedArea.getMiniGameSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            calculator.setNodeFontStyle(areasGrid, AREAS_PERCENT, (double) newVal / 2);
            calculator.setImageViewDimension(backImg, IMG_PERCENT, (double) newVal / 2);
        });

        final double halfStageSize = stage.getWidth() / 2;
        calculator.setNodeFontStyle(areasGrid, AREAS_PERCENT, halfStageSize);
        calculator.setImageViewDimension(backImg, IMG_PERCENT, halfStageSize);
    }
}
