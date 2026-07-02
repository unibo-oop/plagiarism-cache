package controller.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
//import model.game.GameImpl;

/**
 * Controller of the view PauseMenu.
 */
public class PauseMenuController {

//    private static long remainingTime;

    @FXML // fx:id="resume"
    private Button resume; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    /**
     * Resume the game.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void resumeGame(final ActionEvent event) {
//        GameImpl.getGameImpl().resumeTime(remainingTime);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Close the application.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void closeApplication(final ActionEvent event) {
        Runtime.getRuntime().exit(0);
    }

    /**
     * Set the remaining time to the end of the Game Day.
     * @param time
     *          time left to end the Game Day.
     */
    public static void setRemainingTime(final long time) {
//        remainingTime = time;
    }
}

