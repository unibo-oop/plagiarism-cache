package jwhale.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Utility class for dialogs.
 *
 */
public final class DialogUtils {

    private DialogUtils() {
    }
    /**
     * Network error dialog.
     * @param e
     *          exception to handle.
     * @param pStage
     *          primary stage.
     */
    public static void networkErrorDialog(final Exception e, final Stage pStage) {
        final Alert alert = getErrorAlertTemplate(pStage);
        alert.setTitle("Network Error");
        alert.setHeaderText("Network Error");
        alert.setContentText(e.getMessage());
        alert.show();
    }
    /**
     * File error dialog.
     * @param e
     *          exception to handle.
     * @param pStage
     *          primary stage.
     */
    public static void fileErrorDialog(final Exception e, final Stage pStage) {
        final Alert alert = getErrorAlertTemplate(pStage);
        alert.setTitle("File Error");
        alert.setHeaderText("File Error");
        alert.setContentText("Files may be damaged or inaccessible.");
        alert.show();
    }
    /**
     * Successful operation dialog.
     * @param pStage
     *          primary stage.
     */
    public static void successfulOperation(final Stage pStage) {
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(pStage);
        alert.setTitle("Operation Complete");
        alert.setResizable(false);
        alert.setHeaderText("Operation successful!");
        alert.show();
    }
    /**
     * Template error dialog.
     * @param pStage
     *          primary stage.
     */
    private static Alert getErrorAlertTemplate(final Stage pStage) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL); 
        alert.initOwner(pStage);
        alert.setResizable(false);
        final Stage window = (Stage) alert.getDialogPane().getScene().getWindow();
        window.getIcons().add(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.png")));
        return alert;
    }
}
