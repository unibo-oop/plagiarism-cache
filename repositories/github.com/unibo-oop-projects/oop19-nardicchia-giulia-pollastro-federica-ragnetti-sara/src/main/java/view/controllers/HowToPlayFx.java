package view.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.DimensionCalculatorImpl;
import view.SceneType;

/**
 * The fx controller for the howToPlay scene.
 *
 */
public class HowToPlayFx extends AbstractSceneController implements FxNormalScene {

    private static final double IMG_PERCENTAGE = 0.003;
    private static final double BUTTON_PERCENTAGE = 0.07;
    private static final double FONT_PERCENTAGE = 0.05;

    @FXML
    private ImageView backImg;

    @FXML
    private Button gameInfoBtn;

    @FXML
    private TextArea infoTxt;

    @FXML
    private GridPane topGrid;

    @FXML
    private void showGameInfo() throws IOException { // NOPMD
        this.scanner("MiniGameInfo");
    }

    @FXML
    private void showIntroduction() throws IOException { // NOPMD
        this.scanner("Introduction");
    }

    @FXML
    private void goBack() { // NOPMD
        this.getView().switchScene(SceneType.MAIN_MENU.getFilePath());
    }

    private void scanner(final String fileTxt) throws IOException {
        infoTxt.clear();
        final BufferedReader input = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/info/" + fileTxt + ".txt")));
        try {
            String line;
            while ((line = input.readLine()) != null) { // NOPMD
                infoTxt.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public void init() {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.infoTxt.setStyle("-fx-font-size: " + FONT_PERCENTAGE * (double) newVal / 2);
            calculator.setNodeFontStyle(topGrid, BUTTON_PERCENTAGE, (double) newVal / 2);
            calculator.setImageViewDimension(backImg, IMG_PERCENTAGE, (double) newVal / 2);
        });

        final double halfStageSize = stage.getWidth() / 2;
        this.infoTxt.setStyle("-fx-font-size: " + FONT_PERCENTAGE * halfStageSize);
        calculator.setNodeFontStyle(topGrid, BUTTON_PERCENTAGE, halfStageSize);
        calculator.setImageViewDimension(backImg, IMG_PERCENTAGE, halfStageSize);
    }
}
