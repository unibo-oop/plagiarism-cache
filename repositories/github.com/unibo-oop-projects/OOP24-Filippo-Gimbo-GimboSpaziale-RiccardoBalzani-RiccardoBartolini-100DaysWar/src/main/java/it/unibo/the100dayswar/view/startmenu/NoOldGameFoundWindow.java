package it.unibo.the100dayswar.view.startmenu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Utility class that implements the Window that displays 
 * "No old game was found".
 */
public final class  NoOldGameFoundWindow {

    /**
     * A private constructor to hide the implicit public one.
     */
    private NoOldGameFoundWindow() {
    }

    /**
     * Displays an error dialog with the message "No old game was found".
     * 
     * @param parent the parent frame or null if no parent is available
     */
    public static void show(final JFrame parent) {
        JOptionPane.showMessageDialog(
            parent,
            "No old game was found",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
