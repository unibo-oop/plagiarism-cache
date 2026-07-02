package application.view.resources;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Simple alert manager for easy way to show messages.
 */
public interface AlertManager {
    
    /**
     * Setup an alert with given information.
     * @param type Type of the alert
     * @param title Title of the alert
     * @param header Header of the alert
     * @param message Content of the alert
     * @return An alert ready to use
     */
    default Alert setupAlert(AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert;
    }
    
    /**
     * Create an error alert that will be showned and it will be wait for answer.
     * @param title Title of the alert
     * @param header Header of the alert
     * @param message Content of the alert
     */
    default void showErrorAlert(String title, String header, String message) {
        setupAlert(AlertType.ERROR, title, header, message).showAndWait();
    }
    
    /**
     * Create an confirm alert that will be showned and it will be wait for answer.
     * @param title Title of the alert
     * @param header Header of the alert
     * @param message Content of the alert
     */
    default void showInformationAlert(String title, String header, String message) {
        setupAlert(AlertType.INFORMATION, title, header, message).showAndWait();
    }
}
