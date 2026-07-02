package it.unibo.modularcheckers.model.moveset;

import java.util.Map;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * Used to filter the trees of steps calculated by the class StepTreeBuilder.
 */
public interface StepTreeFilter {

    /**
     * Removes and/or trims all the illegal Trees of steps due to rules.
     * 
     * @param allStepTrees the Map containing all the stepTrees not filtered.
     * @param board        the board in the moment this is method is called.
     * @param turn         the actual turn color.
     * @return the filtered map.
     */
    Map<Coordinate, Tree<Step>> filter(Map<Coordinate, Tree<Step>> allStepTrees, Chessboard board, Color turn);

}
