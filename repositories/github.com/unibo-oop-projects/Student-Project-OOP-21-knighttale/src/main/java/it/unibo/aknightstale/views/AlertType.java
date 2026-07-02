package it.unibo.aknightstale.views;

import javafx.scene.control.Alert;

public enum AlertType {
    /** An error alert. */
    ERROR(Alert.AlertType.ERROR),
    /** An information alert. */
    INFORMATION(Alert.AlertType.INFORMATION),
    /** A warning alert. */
    WARNING(Alert.AlertType.WARNING),
    /** A confirmation alert. */
    CONFIRMATION(Alert.AlertType.CONFIRMATION),
    /** A custom alert. */
    NONE(Alert.AlertType.NONE);

    private final Alert.AlertType alertType;

    AlertType(final Alert.AlertType alertType) {
        this.alertType = alertType;
    }

    public Alert.AlertType getAlertType() {
        return alertType;
    }
}
