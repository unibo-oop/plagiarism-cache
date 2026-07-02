package view.board;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Dialog to manage error.
 */
public class DialogWindowError {

    private final JFrame frame = new JFrame();

    /**
     * Constructor of the class.
     * 
     * @param errorMessage
     *                         The error message to show on the dialog.
     */
    public DialogWindowError(final String errorMessage) {

        JOptionPane.showMessageDialog(frame, errorMessage);

    }

}
