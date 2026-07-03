package com.geoquiz.view.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Public class for closing the program.
 * 
 */
public final class ExitProgram {

    private ExitProgram() {
    }

    /**
     * Method to exit the program.
     * 
     * @param mainWindow
     *            the main stage.
     */
    public static void exitProgram(final Stage mainWindow) {
        final Alert box = ConfirmBox.getBox().getConfirmBox("Sei sicuro di uscire?");
        final Boolean answer = box.showAndWait().get().equals(ButtonType.YES);
        if (answer) {
            mainWindow.close();
        }
    }
}
