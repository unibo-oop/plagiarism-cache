package model.board.manager;

import model.board.Board;

/**
 * Interface of a class used to save the current state of the game.
 * Andrea Serafini.
 *
 */
public interface GameManagerInterface {

    /**
     * Delete the game file.
     */
    void deleteSavedGame();

    /**
     *
     * @return the game data
     */
    Board getGame();

    /**
     *
     * @return true if the game file is present
     */
    boolean isPresent();

    /**
     * Load saved game from a previous match.
     */
    void loadSavedGame();

    /**
     * Save the current game on a file.
     */
    void saveGame();

}
