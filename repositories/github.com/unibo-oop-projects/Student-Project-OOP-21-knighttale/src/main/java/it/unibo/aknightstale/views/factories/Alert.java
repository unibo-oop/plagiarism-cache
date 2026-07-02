package it.unibo.aknightstale.views.factories;

import it.unibo.aknightstale.views.AlertType;

import java.awt.HeadlessException;

public final class Alert {
    private Alert() {
    }

    /**
     * Shows an alert.
     *
     * @param type    Alert type.
     * @param title   Alert dialog title.
     * @param header  Alert header text.
     * @param content Alert message.
     */
    public static void showAlert(final AlertType type, final String title, final String header, final String content) {
        if (Boolean.getBoolean("headless") && type == AlertType.ERROR) {
            throw new HeadlessException(title + " - " + content);
        }
        final var alert = new javafx.scene.control.Alert(type.getAlertType(), content);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    /**
     * Shows an alert with the given type, title, header and content.
     *
     * @param type    Alert type.
     * @param content Alert message.
     */
    public static void showAlert(final AlertType type, final String content) {
        showAlert(type, "", "", content);
    }

    /**
     * Shows an alert with the given type, title, header and content.
     *
     * @param type    Alert type.
     * @param title   Alert dialog title.
     * @param content Alert text.
     */
    public static void showAlert(final AlertType type, final String title, final String content) {
        showAlert(type, title, "", content);
    }
}
