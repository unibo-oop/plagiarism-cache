package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Enum containing exit status for the app, if a normal close or an error occured.
 * @author Marcin Pabich
 */
public enum ExitStatus {

    /**
     * When the app close normally, no errors.
     */
    NormalExit(0),
    
    /**
     * When the app is killed by FXML exception.
     */
    FXMLLoadingExcp(4),
    
    
    /**
     * When the app is killed by a null pointer exception.
     */
    NullPointerExcp(13),
    
    /**
     * When the app is killed by not specified exception.
     */
    GenericExcp(1);
        
    
    private final int exitValue;    
        
    /**
     * Internal costructor.
     * @param exitValue 
     */
    ExitStatus(final int eValue) {
        this.exitValue = eValue;
    }
    
    /**
     * Returns the exit value of the ExitStatus.
     * @return int value to pass direclty to exit method (0 no errors, 1 errors)
     */
    public int getExitValue() {
        return exitValue;
    }
    
    /**
     * Show an error message.
     * @param title Title of the alert 
     * @param header Header of the alert, near the icon
     * @param content Text of the alert (content)
     */
    public static void showErrorDialog(final String title, final String header, final String content) {
        final Alert alertDialog = new Alert(AlertType.ERROR);
        alertDialog.setTitle(title);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(content);
        alertDialog.showAndWait();
    }
}
