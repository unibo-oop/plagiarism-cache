package view;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * An interface representing a generic view, that can be easily controlled by
 * its observer through its basic methods.
 *
 *
 */
public interface View {
    /**
     * Initialize view.
     */
    void init();

    /**
     * Show error message.
     *
     * @param msg
     *            text to show
     * @param view
     *            the view where show the message
     */
    default  void logError(String msg, Component view) {
        JOptionPane.showMessageDialog(view, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show a message.
     *
     * @param msg
     *            text to show
     * @param view
     *            the view where show the message
     */
    default void showMessage(String msg, Component view) {
        JOptionPane.showMessageDialog(view, msg);
    }
}
