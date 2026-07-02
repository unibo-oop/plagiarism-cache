package view.controllers;

import java.io.IOException;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DifficultyLevel;
import view.DimensionCalculatorImpl;
import view.SceneType;

/**
 * The fx controller for the minigame selector and the game start scene.
 * 
 */
public class MinigameSelectorFx extends AbstractSceneController implements FxNormalScene {

    private static final double IMG_PERCENTAGE = 0.003;
    private static final double DIFFICULTY_PERCENTAGE = 0.08;
    private static final double TEXT_PERCENTAGE = 0.07;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private ImageView homeImg;

    @FXML
    private ImageView backImg;

    @FXML
    private Label errorLbl;

    @FXML
    private ComboBox<String> gameComboBox;

    @FXML
    private GridPane grid;

    @FXML
    private GridPane gridToggle;

    @FXML
    private void initializeCombo() { // NOPMD
        if (this.gameComboBox.getItems().isEmpty()) {
            final ObservableList<String> observable = FXCollections.observableArrayList(this.getGameList());
            gameComboBox.getItems().addAll(observable);
        }
    }

    @FXML
    private void startMiniGame() { // NOPMD
        if (checkFieldsNotNull()) {
            final RadioButton selectedRadioBtn = (RadioButton) difficulty.getSelectedToggle();
            final DifficultyLevel difficulty = Arrays.asList(DifficultyLevel.values()).stream()
                    .filter(e -> e.name().equals(selectedRadioBtn.getText())).findAny().get();

            this.getController().setMiniGame(gameComboBox.getValue(), difficulty);
            this.getView().setMiniGameScene("/layouts/minigames/" + gameComboBox.getValue().replace(" ", "") + ".fxml");
        } else {
            this.errorLbl.setText("* Select all fields to start playing!");
        }
    }

    private boolean checkFieldsNotNull() {
        return this.difficulty.getSelectedToggle() != null && this.gameComboBox.getValue() != null;
    }

    @FXML
    private void goBack() throws IOException { // NOPMD
        this.getView().switchScene(SceneType.TRAINING_AREA.getFilePath());
    }

    @FXML
    private void backHome() throws IOException { // NOPMD
        this.getView().switchScene(SceneType.MAIN_MENU.getFilePath());
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public void init() {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            calculator.setNodeFontStyle(grid, TEXT_PERCENTAGE, (double) newVal / 2);
            calculator.setNodeFontStyle(gridToggle, DIFFICULTY_PERCENTAGE, (double) newVal / 2);
            calculator.setImageViewDimension(backImg, IMG_PERCENTAGE, (double) newVal / 2);
            calculator.setImageViewDimension(homeImg, IMG_PERCENTAGE, (double) newVal / 2);
        });

        final double halfStageSize = stage.getWidth() / 2;
        calculator.setNodeFontStyle(grid, TEXT_PERCENTAGE, halfStageSize);
        calculator.setNodeFontStyle(gridToggle, DIFFICULTY_PERCENTAGE, halfStageSize);
        calculator.setImageViewDimension(backImg, IMG_PERCENTAGE, halfStageSize);
        calculator.setImageViewDimension(homeImg, IMG_PERCENTAGE, halfStageSize);
    }

}
