package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * 
 *
 */
public class EndGameController extends BasicFXMLController {

    @FXML
    private Text score;

    private int scores;

    /**
     * Constructor.
     *
     * @param sceneController
     * @param scores
     * @throws IOException
     */
    public EndGameController(SceneController sceneController, final int scores) throws IOException {
        super(sceneController);
        this.scores = scores;
    }

    /**
     * Initialize the GUI.
     */
    @FXML
    private void initialize() {
        // this.score.setText(Integer.valueOf(this.scores).toString());
        this.score.setText(Integer.toString(this.scores));
        this.score.setVisible(true);
    }

    /**
     * Ends the game.
     *
     * @param event
     */
    @FXML
    void quit(final ActionEvent event) {
        // super.buttonPressedSound();
        super.getSceneController().quit();
    }

    /**
     * Returns to main menu.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void showMainMenu(final ActionEvent event) throws IOException {
        // super.buttonPressedSound();
        super.getSceneController().switchToMainMenu();
    }
}
