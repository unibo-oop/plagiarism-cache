package view.dialog;

import java.awt.event.ActionListener; 

import javax.swing.JPanel;

/**
 * 
 * Interface responsible in the creation of the dialogs present in the program.
 *
 */

public interface IAddDeleteDialog {

    /**
     * Method which sets the content of the dialog's main panel.
     * @return A JPanel which contains the information of a determined dialog.
     */
    
    JPanel setFields();
    
    /**
     * Method which sets the listener of the ok button in the dialog.
     * @return The listener of the button.
     */
    
    ActionListener setOkListener();
}
