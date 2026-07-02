package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * 
 */
public class MainMenuController extends BasicFXMLController {

    /**
     * Constructor.
     *
     * @param sceneController
     * @throws IOException
     */
    public MainMenuController(SceneController sceneController) throws IOException {
        super(sceneController);
    }

    /**
     * Show nickname GUI.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void chooseNickname(final ActionEvent event) throws IOException {
        super.getSceneController().switchToNickname();
    }

    /**
     * Show scores GUI.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void showScores(final ActionEvent event) throws IOException {
        super.getSceneController().switchToScores();
    }

    /**
     * Show controls GUI.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToControls(final ActionEvent event) throws IOException {
        super.getSceneController().switchToControls();
    }

    /**
     * Ends the game.
     * 
     * @param event
     */
    @FXML
    void quit(final ActionEvent event) {
        super.getSceneController().quit();
    }

}
