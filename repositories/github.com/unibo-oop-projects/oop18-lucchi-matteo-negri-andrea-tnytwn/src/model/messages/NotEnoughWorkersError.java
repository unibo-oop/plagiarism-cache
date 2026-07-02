package model.messages;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class used to alert the user that he does not have enough free workers.
 */
public class NotEnoughWorkersError {

    /**
     * Constructor of the class.
     * @param workers
     *          necessary workers
     * @param freeWorkers
     *          available workers
     */
    public NotEnoughWorkersError(final int workers, final int freeWorkers) {
        final Alert notEnoughWorkers = new Alert(AlertType.ERROR);
        notEnoughWorkers.setContentText("Impossibile costruire una nuova struttura, lavoratori insufficienti.\n Lavoratori richiesti: "
        + workers + "\nLavoratori liberi: " + freeWorkers);
        notEnoughWorkers.setHeaderText("Lavoratori insufficienti");
        notEnoughWorkers.setTitle("Costruzione impossibile");
        notEnoughWorkers.showAndWait();
    }
}
