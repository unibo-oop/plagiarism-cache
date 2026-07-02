package controller;

import java.io.IOException;
import model.SudokuLogic;
import utilities.Difficulty;

/**
 * 
 * Iterface for main controller of the game. 
 *
 */

public interface SudokuGameHandler {

    /**
     * Start a new game.
     * @param diff Difficulty of the game
     * @throws IOException
     * @throws URISyntaxException 
     */
    void newGame(Difficulty diff) throws IOException;

    /**
     * Restart a previous game.
     * @throws IOException
     */
    void resumeGame() throws IOException;

    /**
     * Save the actual game.
     * @throws IOException
     */
    void saveGame() throws IOException;

    /**
     * Check if previouse game exist.
     * @return if exist 
     */
    boolean previousGameExist();

    /**
     * Call model.hit.
     * @param x of the cell
     * @param y of the cell
     * @param value of the cell
     */
    void hitCell(int x, int y, int value);

    /**
     * Call model.remove.
     * @param x of the cell
     * @param y of the cell
     */
    void removeCell(int x, int y);

    /**
     * 
     * @return current Settings
     */
    Settings getSettings();

    /**
     * @return current model
     */
    SudokuLogic getLogic();
}
