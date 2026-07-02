package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utilities.EnumInt;

/**
 * 
 */
public class ScoresController extends BasicFXMLController {

    @FXML
    private TextFlow scoreText;

    /**
     * Constructor.
     *
     * @param sceneController
     * @throws IOException
     */
    public ScoresController(final SceneController sceneController) throws IOException {
        super(sceneController);
    }

    @FXML
    private void initialize() {
        this.setUpScoresScene();
    }

    private void setUpScoresScene() {
        this.refreshScoresData();
        this.scoreText.setVisible(true);
    }

    /**
     * refresh scores data.
     */
    public void refreshScoresData() {
        final Text rankText = new Text(this.getSceneController().getRanking().getFormattedRanking(5));
        rankText.setFill(Color.YELLOW);
        rankText.setFont(Font.font("Verdana", FontWeight.BOLD, EnumInt.TWENTYFIVE.getValue()));
        this.scoreText.getChildren().add(rankText);
    }

    @FXML
    void showMainMenu(final ActionEvent event) throws IOException {
        // super.buttonPressedSound();
        super.getSceneController().switchToMainMenu();
    }
}
