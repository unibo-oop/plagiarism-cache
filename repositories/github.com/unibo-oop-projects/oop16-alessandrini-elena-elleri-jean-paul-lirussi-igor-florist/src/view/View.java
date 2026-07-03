package view;

import java.awt.Component;

import javax.swing.JOptionPane;
/**
 * interface representing a generic view and its basic methods.
 *
 */
public interface View {
    /**
     * initialize view.
     */
    void init();
    /**
     * shows a message.
     * @param view
     *              the view related to the message
     * @param msg
     *              the message to be shown
     */
    default void showMessage(Component view, String msg) {
        JOptionPane.showMessageDialog(view, msg);
    }
    /**
     * shows an error message.
     * @param view
     *              the view related to the message 
     * @param msg
     *              the message to be shown
     */
    default void showError(Component view, String msg) {
        JOptionPane.showMessageDialog(view, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}