package view.alert;

import java.util.Optional;

import javafx.scene.control.ButtonType;

/**
 *
 * Chiara Tarantino.
 * Interface that allows to show an alert dialog.
 *
 */
public interface AlertWindow {
    /**
     *
     * @return the dialog and waits for the user response.
     */
    Optional<ButtonType> showAndWait();

}
