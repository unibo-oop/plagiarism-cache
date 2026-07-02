package com.biaren.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Static various util methods for GUI
 * @author nbrunetti
 *
 */
public class BiarenUtil {

    /**
     * Shows a basic alert, set type and optional text.
     * Use empty string if you want to add no text.
     * @param t Type of alert of {@link AlertType}
     * @param context {@link String} content text 
     * @param title {@link String} title text
     * @param header {@link String} header text
     */
    public static void showBasicAlert(final AlertType t, final String context, final String title, final String header) {
        Alert alert = new Alert(t);
        alert.setContentText(context);
        alert.setHeaderText(title);
        alert.setTitle(header);
        alert.showAndWait();
    }
}
