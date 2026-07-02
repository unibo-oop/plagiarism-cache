package talisman.view;

import javax.swing.JOptionPane;

/**
 * Implementation of {@code GameVictoryWindow} that shows a dialog to display
 * the winner.
 * 
 * @author Alberto Arduini
 */
public class GameVictoryWindowImpl implements GameVictoryWindow {
    private static final String MESSAGE_FORMAT = "Player %d wins the game!";

    /**
     * Creates a new victory window.
     * 
     * @param winner the winner of the game
     */
    public GameVictoryWindowImpl(final int winner) {
        JOptionPane.showMessageDialog(null, String.format(GameVictoryWindowImpl.MESSAGE_FORMAT, winner + 1));
    }
}
