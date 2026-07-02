package it.unibo.javacrush.model.api;

import java.util.List;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;

/**
 * Interface representing a game session.
 */
public interface Session {

    /**
     * Return the number of remaining moves.
     * 
     * @return the number of moves
     */
    int getMovesLeft();

    /**
     * Decrease the number of moves.
     */
    void decreaseMoves();

    /**
     * Return the updated goals of the game.
     * 
     * @return an unmodifiable list with the updated goals of the game
     */
    List<Goal> getGoals();

    /**
     * Update the goals based on the collected cells.
     * 
     * @param type  the type of cells collected
     * @param count the number of cells collected
     */
    void updateGoals(CellType type, int count);

    /**
     * Return the state of the game, whether if it is won, lost,
     * or in progress.
     * 
     * @return the game state
     */
    GameState getGameState();

}
