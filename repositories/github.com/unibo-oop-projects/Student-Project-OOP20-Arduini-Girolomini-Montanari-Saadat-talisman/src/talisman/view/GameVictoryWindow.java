package talisman.view;

/**
 * Window that shows the game winner.
 * 
 * @author Alberto Arduini
 */
public interface GameVictoryWindow {
    /**
     * Show the window with the specified winner.
     * 
     * @param winner the index of the winning player
     */
    static void show(final int winner) {
        new GameVictoryWindowImpl(winner);
    }
}
