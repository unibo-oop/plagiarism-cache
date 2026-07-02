package view.game;

/**
 * GameView allows to show the game interface and to update it with an infinite loop.
 */
public interface GameView {

    /**
     * Loads the map and creates the world.
     */
    void initiateGame();

    /**
     * Returns to the main menu.
     */
    void terminateGame();
}
