package oop.raccoonhome.gui;

import com.jfoenix.controls.JFXDialog;

/**
 * 
 *
 */
public interface DialogInterface {
    /**
     * A method that loads FXML file into a Dialog.
     * 
     * @param resource
     *            FXML file Source
     * @param dialog
     *            Dialog that i want to load
     */
    void loadDialog(JFXDialog dialog, String resource);

}
