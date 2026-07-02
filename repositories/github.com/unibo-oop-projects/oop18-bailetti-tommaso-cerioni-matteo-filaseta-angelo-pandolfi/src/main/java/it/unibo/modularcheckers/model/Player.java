package it.unibo.modularcheckers.model;

import java.util.Optional;
import java.util.Set;

import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * The Players playing the game.
 */
public interface Player {

    /**
     * Get the name of the Player.
     * 
     * @return A string containing the name of the Player.
     */
    String getName();

    /**
     * Select a piece to move on the Chessboard.
     * 
     * @param movablePieces A set containing all the coordinates of the pieces thta
     *                      can move on the Chessboard.
     * @return the coordinate of the piece that will move.
     */
    Optional<Coordinate> selectedPiece(Set<Coordinate> movablePieces);

    /**
     * The player choose the step from all the possible pool of legal steps. Note: A
     * move is made of 2 or more steps, {
     * 
     * @param allSteps a tree containing all the possible steps.
     * @return A pair of step, the first is the actual position, the second is the
     *         step chosen.
     * 
     */
    Pair<Step, Step> chooseStep(Tree<Step> allSteps);
}
