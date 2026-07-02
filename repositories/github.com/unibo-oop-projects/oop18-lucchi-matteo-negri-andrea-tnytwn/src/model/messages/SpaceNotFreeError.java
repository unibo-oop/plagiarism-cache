package model.messages;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utility.PositionControllerHelper;

/**
 * Class used to alert the user that the position is occupied.
 */
public class SpaceNotFreeError {

    /**
     * Constructor of the class.
     */
    public SpaceNotFreeError() {
        final Alert spaceNotFree = new Alert(AlertType.ERROR);
        spaceNotFree.setContentText("La posizione:\nX: " + (PositionControllerHelper.getPosition().getKey() + 1)
                + "\nY: " + (PositionControllerHelper.getPosition().getValue() + 1) + "\nè occupata.\nImposibile costruire una nuova struttura!");
        spaceNotFree.setHeaderText("Posizione occupata");
        spaceNotFree.setTitle("Costruzione impossibile");
        spaceNotFree.showAndWait();
    }
}
