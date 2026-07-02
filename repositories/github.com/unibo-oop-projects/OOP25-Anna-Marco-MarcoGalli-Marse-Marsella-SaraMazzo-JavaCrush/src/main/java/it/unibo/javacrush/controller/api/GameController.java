package it.unibo.javacrush.controller.api;

import java.util.Map;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;

/**
 * Interface representing the controller of a game level, responsible for 
 * managing the interactions between the view and the model of the game.
 */
public interface GameController {

    /**
     * Handle the click on the board.
     *  - normally it will select the cell and then deselect it if it's already selected
     *  - if a powerUp was selected, it applies that on the clicked cell
     *
     * @param pos the position of the cell clicked
     * @return true if the cells are swapped or the powerUp was applied, false otherwise
     */
    boolean hit(Position pos);

    /**
     * Update the goals and removes the matched from the board.
     */
    void handleMatches();

    /**
     * Apply the gravity to the board, updating eventual matches,
     * and solve the eventual stall.
     * 
     * @return true if the board state changed, false if it is stable
     */
    boolean applyGravity();

    /**
     * Select a powerUp based on its id.
     * 
     * @param id id of the powerUp
     * @return true if the specified powerUp exists, false otherwise
     */
    boolean selectPowerUp(int id);

    /**
     * Reset the PowerUp selection.
     * 
     * @return true if the reset went well, false otherwise
     */
    boolean resetPowerUpSelection();

    /**
     * Update the game state.
     *
     * @return the updated game state
     */
    GameState updateGameState();

    /**
     * Get the CellType for a specific cell in the board.
     * 
     * @param pos position of the cell
     * @return type of the cell
     */
    CellType getCellTypeAtPos(Position pos);

    /**
     * Get the remaining moves.
     * 
     * @return the number of remaining moves
     */
    int getMovesLeft();

    /**
     * Get the goals of the level.
     * 
     * @return an unmodifiable map of goals
     */
    Map<CellType, Integer> getGoals();

    /**
     * Get the current progress for each goal.
     * 
     * @return a map with the current collected amount for each cell type
     */
    Map<CellType, Integer> getGoalsProgress();

    /**
     * Get the number of columns of the board.
     * 
     * @return the number of columns of the board
     */
    int getBoardCols();

    /**
     * Get the number of rows of the board.
     * 
     * @return the number of rows of the board
     */
    int getBoardRows();

    /**
     * Check if there is stall in the current board.
     * 
     * @return true if there is stall, false otherwise
     */
    boolean isStall();

    /**
     * Get a Set of Position of the cells of a possible match.
     * 
     * @return a Set with the positions of the cells that could 
     *      form a possible match
     */
    Set<Position> getHint();

    /**
     * Quit the level and go back to the main menu.
     */
    void quitLevel();

}
