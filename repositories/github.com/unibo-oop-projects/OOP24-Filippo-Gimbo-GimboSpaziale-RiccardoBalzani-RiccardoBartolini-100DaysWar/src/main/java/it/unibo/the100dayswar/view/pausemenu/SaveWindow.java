package it.unibo.the100dayswar.view.pausemenu;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import it.unibo.the100dayswar.application.The100DaysWar;

/**
 * Utility class that implements the dialog to save or not save
 * the current game.
 */
public final class SaveWindow {

    /** 
     * A private constructor to hide the implicit public one.
     */
    private SaveWindow() {
    }

    /**
     * Dialog displayed before quitting the game.
     * 
     * @param parent the JDialog that launch this window
     * @param savingPath the location of the saving file
     * 
     * @return true if the game is saved false otherwise
     */
    public static boolean saveDialog(final JDialog parent, final String savingPath) {
        final int save = JOptionPane.showConfirmDialog(
            parent,
            "Do you want to save the game?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (save == JOptionPane.YES_OPTION) {
            if (The100DaysWar.CONTROLLER.saveGame(savingPath)) {
                JOptionPane.showMessageDialog(
                    parent,
                    "Game saved successfully!",
                    "Save Status",
                    JOptionPane.INFORMATION_MESSAGE
                );
                return true;

            } else {
                JOptionPane.showMessageDialog(
                    parent,
                    "Failed to save the game. Please try again.",
                    "Save Status",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;

            }
        }

        return false;
    }
}
