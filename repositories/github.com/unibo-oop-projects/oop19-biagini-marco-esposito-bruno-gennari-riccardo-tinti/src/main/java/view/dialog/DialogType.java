package view.dialog;

import javafx.scene.control.Alert.AlertType;

/**
 * This enum represents the Dialog types that DialogBuilder can use. 
 */
public enum DialogType {

    /**
     * Ask confirmation from user.
     */
    CONFIRMATION(AlertType.CONFIRMATION, new MessageDialog()),
    /**
     * Give the user information. 
     */
    INFORMATION(AlertType.INFORMATION, new MessageDialog()),
    /**
     * Warn the user about a non-critical issue.
     */
    WARNING(AlertType.WARNING, new MessageDialog()),
    /**
     * Warn the user about a critical issue.
     */
    ERROR(AlertType.ERROR, new MessageDialog()),
    /**
     * Ask the user for a password.
     */
    LOGIN(AlertType.NONE, new LoginDialog());

    private final AlertType concreteType;
    private final AbstractDialog concreteClass;

    DialogType(final AlertType concreteType, final AbstractDialog concreteClass) {
        this.concreteType = concreteType;
        this.concreteClass = concreteClass;
    }

 // package-private
    AlertType getAlertType() {
        return concreteType;
    }

 // package-private
    AbstractDialog getConcreteClass() {
        return concreteClass;
    }
}
