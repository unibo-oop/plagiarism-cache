package model.messages;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class used to alert the user that he has lost the game.
 */
public class GameLost {

    /**
     * Constructor of the class.
     */
    public GameLost() {
        final Alert gameLost = new Alert(AlertType.ERROR);
        gameLost.setContentText("Peccato! Non hai saputo gestire la tua città!\n I tuoi cittadini non hanno abbastanza risorse."
        + "\nLa tua avventura finisce qui!");
        gameLost.setHeaderText("Game Over!");
        gameLost.setTitle("Hai perso!");
        gameLost.showAndWait();
    }

}
