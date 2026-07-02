package casim.ui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

/**
 * An interface that defines a builder of {@link Alert}.
 */
public interface AlertBuilder {
    /**
     * Set the default values for an info alert.
     *  - Type: AlertType.INFORMATION.
     *  - Title: Info
     * 
     * @param message the message of the alert.
     * @param owner the owner of the alert.
     * @return {@link Alert}.
     */
    Alert buildDefaultInfo(String message, Window owner);

    /**
     * Set the default values for an error alert.
     *  - Type: AlertType.ERROR.
     *  - Title: Error
     * 
     * @param message the message of the alert.
     * @param owner the owner of the alert.
     * @return {@link Alert}.
     */
    Alert buildDefaultError(String message, Window owner);

    /**
     * Sets the message and the owner of the {@link Alert}.
     * 
     * Throws if some of the parameters are not set.
     * 
     * @param type the type of the alert.
     * @param title the title of the alert.
     * @param message the message of the alert.
     * @param owner the owner of the alert.
     * @return the built {@link Alert}.
     */
    Alert buildCustom(AlertType type, String title, String message, Window owner);
}
