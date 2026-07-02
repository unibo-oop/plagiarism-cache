package javawulf;

import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import javawulf.view.gamemenu.GameMenuPanel;

/**
 * Launcher for the game application. 
 */
public final class JavaWulf {

    private JavaWulf() {
    }

    /**
     * Launches the application.
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GameMenuPanel();
            } catch (InterruptedException e) {
                Logger.getLogger(JavaWulf.class.getName()).fine(e.getMessage());
            }
        });
    }
}
