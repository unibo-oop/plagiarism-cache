package it.unibo.the100dayswar.view.quit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Utility class that implements the Window to exit the game from
 * the pause menu.
 */
public final class ExitWindowFromPauseMenu {

    /**
     * A private constructor to hide the implicit public one.
     */
    private ExitWindowFromPauseMenu() {
    }

    /**
     * Display a confirmation dialog to be sure that the user really
     * wants to exit the game.
     * 
     * @param parent the parent frame or null if no parent is available
     * 
     * @return true if the user really want to exit the game, false otherwise
     */
    public static boolean exitDialog(final JDialog parent) {
        return JOptionPane.showConfirmDialog(
            parent,
            "Are you sure you want to exit?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }
}
