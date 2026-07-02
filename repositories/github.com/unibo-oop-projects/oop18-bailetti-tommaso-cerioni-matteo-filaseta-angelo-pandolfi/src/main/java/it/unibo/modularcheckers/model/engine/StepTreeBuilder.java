package it.unibo.modularcheckers.model.engine;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * Engine component for build a Tree of step from a piece in the chessboard.
 */
public interface StepTreeBuilder {

    /**
     * Trasforms the relative moveSet from the piece in the chessboard, to an absolute Tree of Steps.
     * @param chessboard the chessboard where is stored the piece.
     * @param coordinate the coordinate the location of the piece. 
     * @return the absolute tree of steps
     */
    Tree<Step> build(Chessboard chessboard, Coordinate coordinate);

}
