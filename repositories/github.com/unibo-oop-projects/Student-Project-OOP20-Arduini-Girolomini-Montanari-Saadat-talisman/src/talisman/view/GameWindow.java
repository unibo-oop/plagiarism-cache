package talisman.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import talisman.util.GameSetupUtil;

import talisman.view.board.TalismanBoardView;

/**
 * A window for showing an ongoing the game.
 * 
 * @author Alberto Arduini
 *
 */
public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WINDOW_SIZE_X = 1280;
    private static final int WINDOW_SIZE_Y = 720;
    private static final String QUIT_PANEL_TITLE = "Confirm Exit";
    private static final String QUIT_PANEL_TEXT = "Are you sure you want to quit the current game?";

    /**
     * Creates a new game window.
     * 
     * @param board the game board to display
     */
    public GameWindow(final TalismanBoardView board) {
        final LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(GameWindow.WINDOW_SIZE_X, GameWindow.WINDOW_SIZE_Y);
        this.setResizable(false);

        final GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.NONE;
        this.add((Component) board, constraint);

        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                GameWindow.this.askQuitConfirm();
            }
        });
    }

    /**
     * Closes the game window.
     */
    public void close() {
        this.setVisible(false);
        this.dispose();
    }

    private void askQuitConfirm() {
        final int chosenOption = JOptionPane.showConfirmDialog(this, GameWindow.QUIT_PANEL_TEXT,
                GameWindow.QUIT_PANEL_TITLE, JOptionPane.YES_NO_OPTION);
        if (chosenOption == JOptionPane.YES_OPTION) {
            GameSetupUtil.getSingleton().endGame();
        }
    }
}
