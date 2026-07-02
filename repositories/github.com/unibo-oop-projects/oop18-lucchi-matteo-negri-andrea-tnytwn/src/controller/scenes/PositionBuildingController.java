package controller.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Pair;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.GamePropertiesHelper;
import utility.PositionControllerHelper;

/**
 * Controller of the view PositionBuilding.
 */
public class PositionBuildingController {

    @FXML // fx:id="confirm"
    private Button confirm; // Value injected by FXMLLoader

    @FXML // fx:id="cancel"
    private Button cancel; // Value injected by FXMLLoader

    @FXML // fx:id="cost"
    private Label cost; // Value injected by FXMLLoader

    @FXML // fx:id="column"
    private TextField column; // Value injected by FXMLLoader

    @FXML // fx:id="row"
    private TextField row; // Value injected by FXMLLoader

    @FXML // fx:id="type"
    private Label type; // Value injected by FXMLLoader

    @FXML // fx:id="info"
    private Label info; // Value injected by FXMLLoader

    /**
     * Set the position where the new building will be built.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void confirmCreation(final ActionEvent event) {
        if (isNumber(row.getText(), GamePropertiesHelper.ROW_NUMBER)
                && isNumber(column.getText(), GamePropertiesHelper.COLUMN_NUMBER)) {
            final Pair<Integer, Integer> position = new Pair<>(Integer.parseInt(row.getText()) - 1, Integer.parseInt(column.getText()) - 1);
            PositionControllerHelper.setPosition(position);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            final Alert error = new Alert(AlertType.ERROR);
            error.setTitle("Errore!");
            error.setHeaderText("Errore nell'inserimento delle coordinate.");
            error.setContentText("Il numero di riga deve essere compreso tra 1 e " + GamePropertiesHelper.ROW_NUMBER 
                    + "\nIl numero di colonna deve essere compreso tra 1 e " + GamePropertiesHelper.COLUMN_NUMBER);
            error.showAndWait();
        }
    }

    /**
     * Close the window.
     * @param event
     *          the click of the mouse
     */
    @FXML
    public void closeWindow(final ActionEvent event) {
        PositionControllerHelper.setDefault();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Check if the entered value is a permissible number or not.
     * @param value
     *          the entered value
     * @param max
     *          the maximum allowed value
     * @return
     *          true:   if the number is permissible
     *          false:  in all other case
     */
    private boolean isNumber(final String value, final int max) {
        if (value.length() > 2 || value.isEmpty()) {
            return false;
        } else {
            try {
                if (Integer.parseInt(value) < 1 || Integer.parseInt(value) > max) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            } 
        }
        return true;
    }
}
