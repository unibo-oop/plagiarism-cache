package frogger.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frogger.common.Constants;

/**
 * Represents the main game window of the Frogger game.
 * Manages the JFrame and allows setting and switching of game panels.
 */
public class GameScene {
    private final JFrame frame;

    /**
     * Constructs the game window (JFrame) with predefined dimensions and settings.
     * The window is titled "Ranocchietta", not resizable by default, and centered on screen.
     */
    public GameScene() {
        frame = new JFrame("Ranocchietta");
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Sets the current content pane of the JFrame to the given JPanel.
     * It also makes the window visible, requests focus for user input, 
     * and refreshes the frame to reflect changes.
     *
     * @param panel the new {@link JPanel} to display in the game window
     */
    public void setPanel(final JPanel panel) {
        this.frame.setContentPane(panel);
        this.frame.revalidate();
        this.frame.repaint();
        panel.requestFocus();
        this.frame.pack();
        this.frame.setVisible(true);
    }
}
