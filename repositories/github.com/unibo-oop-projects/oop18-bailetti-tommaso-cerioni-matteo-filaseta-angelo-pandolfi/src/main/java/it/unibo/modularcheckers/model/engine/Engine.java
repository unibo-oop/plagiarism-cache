package it.unibo.modularcheckers.model.engine;

import java.util.Map;

import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.Player;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * Engine is an Interface that model a sort of "referee" for every game played.
 * The rule-set is chosen based on the game, and any move can be executed only
 * if the Engine decide it is legal.
 */
public interface Engine {

    /**
     * This method is called to return the player that needs to choose the move to
     * do.
     * 
     * @return the Player whose turn is now.
     */
    Player getActualPlayer();

    /**
     * Calculate the Tree of Steps for every piece on the board of the actualTurn
     * color.
     * 
     * @return A map where every piece in that coordinate have its relative
     *         Step-Tree.
     */
    Map<Coordinate, Tree<Step>> calculateAllSteps();

    /**
     * Return the specific Tree of steps of a Piece.
     * 
     * @param coordSelected The Coordinate of the piece chosen.
     * @return A tree containing the steps of the piece chosen.
     */
    Tree<Step> getStepTreeFromCoord(Coordinate coordSelected);

    /**
     * Execute the steps chosen by the player. (Method ChooseStep)
     * 
     * @param steps X: the step containing the coordinate where the piece is. Y: The
     *              step containing the coordinate where to move.
     */
    void executeStep(Pair<Step, Step> steps);

    /**
     * Called when the Tree of step is over. Adds the move to the History, skips to
     * the next Turn and calculates all the new StepTrees.
     */
    void moveFinished();

    /**
     * Start the game.
     */
    void start();

    /**
     * Removes all the pieces of the player that decide to surrender.
     */
    void surrender();

}
