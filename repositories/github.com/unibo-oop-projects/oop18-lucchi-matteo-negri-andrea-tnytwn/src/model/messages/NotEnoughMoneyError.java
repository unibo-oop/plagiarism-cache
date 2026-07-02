package model.messages;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class used to alert the user that he does not have enough money.
 */
public class NotEnoughMoneyError {

    /**
     * Constructor of the class.
     * @param cost
     *          Cost of the new building
     * @param money
     *          Money available
     */
    public NotEnoughMoneyError(final int cost, final int money) {
        final Alert notEnoughMoney = new Alert(AlertType.ERROR);
        notEnoughMoney.setContentText("Impossibile costruire una nuova struttura, denaro insufficiente.\n Denaro richiesto: "
        + cost + "\nDenaro posseduto: " + money);
        notEnoughMoney.setHeaderText("Denaro insufficiente");
        notEnoughMoney.setTitle("Costruzione impossibile");
        notEnoughMoney.showAndWait();
    }

}
