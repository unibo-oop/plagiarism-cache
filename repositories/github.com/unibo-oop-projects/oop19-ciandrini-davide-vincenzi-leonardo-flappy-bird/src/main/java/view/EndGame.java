package view;

import javafx.scene.control.Button;

public interface EndGame {

    /**
     * Create and show the quit button that send the user to the FXML endgame menu.
     */
    void quitBtn();

    /**
     * 
     * @return quitBtn 
     */
    Button getButton();
}
