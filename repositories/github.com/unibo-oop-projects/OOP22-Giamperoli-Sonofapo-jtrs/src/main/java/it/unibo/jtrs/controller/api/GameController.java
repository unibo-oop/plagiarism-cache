package it.unibo.jtrs.controller.api;

import java.util.List;
import java.util.Set;

import it.unibo.jtrs.model.api.GameModel.Interaction;
import it.unibo.jtrs.model.api.Tetromino;

/** An interface modelling a game controller. This controller provides methods
 * to access model data and modify it accordingly to game mechanics. Il also
 * make possible to its related view to gather the necessary data to display
 * to the user.
 */
public interface GameController {

    /**
     * Returns a list of Tetrominoes in the grid.
     *
     * @return the list of Tetrominoes
     */
    List<Tetromino> getPieces();

    /**
     * Returns the outcome of the given interaction.
     *
     * @param i the interaction
     * @return true on success, false otherwise
     */
    boolean advance(Interaction i);

    /**
     * Returns if the given Tetromino has been addedd to the grid.
     *
     * @param next the Tetromino to be added
     * @return true on success, false otherwise
     */
    boolean changePiece(Tetromino next);

    /**
     * Returns the number of lines deleted.
     *
     * @return the number of lines
     */
    int deleteRows();

    /**
     * Returns the deleted lines.
     *
     * @return a set of lines
     */
    Set<Integer> getDeletedLines();

}
