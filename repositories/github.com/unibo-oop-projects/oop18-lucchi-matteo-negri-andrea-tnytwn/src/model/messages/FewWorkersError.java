package model.messages;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class used to alert the user that he can't demolish the construction.
 */
public class FewWorkersError {

    /**
     * Constructor of the class.
     */
    public FewWorkersError() {
        final Alert notEnoughMoney = new Alert(AlertType.ERROR);
        notEnoughMoney.setContentText("Impossibile demolire la struttura, Alcuni degli abitanti lavorano in altri edifici.");
        notEnoughMoney.setHeaderText("Lavoratori non sufficienti");
        notEnoughMoney.setTitle("Demolizione impossibile");
        notEnoughMoney.showAndWait();
    }

}
