package it.unibo.jtrs.model.api;

import java.util.List;
import java.util.Set;

/**
 * An interface modelling the game model. This model provides the Tetrominos placed
 * during the game and allow the current Tetromino to be moved in the grid according
 * to the game rules.
 */
public interface GameModel {

    /**
     * Game states.
     */
    enum GameState {

        /**
         * Game is ready to start.
         */
        START,

        /**
         * Game is running.
         */
        RUNNING,

        /**
         * Game is paused.
         */
        PAUSE,

        /**
         * Game is over.
         */
        OVER
    }

    /**
     * Possible interactions.
     */
    enum Interaction {

        /**
         * Translate downwards.
         */
        DOWN,

        /**
         * Translate leftwards.
         */
        LEFT,

        /**
         * Translate rightwards.
         */
        RIGHT,

        /**
         * Rotate clockwise.
         */
        ROTATE
    }

    /**
     * Returns a list of all the Tetrominoes placed in the grid.
     *
     * @return the list of Tetrominoes
     */
    List<Tetromino> getPieces();

    /**
     * Tries to add the next given Tetromino to the grid.
     *
     * @param next the next Tetromino
     * @return true if action succeeded, false otherwise
     */
    boolean nextPiece(Tetromino next);

    /**
     * Removes the completed grid's rows and translates downwards all the components
     * above them.
     * 
     * @return the number of rows deleted
     */
    int deleteRows();

    /**
     * Moves the current Tetromino as specified with the given interaction.
     *
     * @param i the interaction
     * @return true if the action succeeded, false otherwise
     */
    boolean advance(Interaction i);

    /**
     * Returns the deleted lines.
     * 
     * @return a set of lines
     */
    Set<Integer> getDeletedLines();

}
