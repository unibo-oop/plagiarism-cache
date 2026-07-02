package it.unibo.modularcheckers.model.moveset;

import java.util.Map;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;

/**
 * Abstract class for StepTreeFilter.
 */
public abstract class AbstractStepTreeFilter implements StepTreeFilter {

    private Map<Coordinate, Tree<Step>> stepTrees;
    private Chessboard board;
    private Color actualTurn;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Coordinate, Tree<Step>> filter(final Map<Coordinate, Tree<Step>> allStepTrees, final Chessboard board,
            final Color color) {
        setStepTrees(allStepTrees);
        setBoard(board);
        setActualTurn(color);
        applyProperFilters();
        return getStepTrees();
    }

    /**
     * @return the StepTree.
     */
    protected Map<Coordinate, Tree<Step>> getStepTrees() {
        return stepTrees;
    }

    /**
     * @param stepTrees the new stepTrees to filter.
     */
    protected void setStepTrees(final Map<Coordinate, Tree<Step>> stepTrees) {
        this.stepTrees = stepTrees;
    }

    /**
     * Apply to the not-filtered map some filters depending on the implementation.
     */
    protected abstract void applyProperFilters();

    /**
     * 
     * @return the board situation.
     */
    protected Chessboard getBoard() {
        return board;
    }

    /**
     * 
     * @param board set the board situation to this new one.
     */
    protected void setBoard(final Chessboard board) {
        this.board = board;
    }

    /**
     * 
     * @return the color of the actual Turn
     */
    protected Color getActualTurn() {
        return actualTurn;
    }

    /**
     * @param actualTurn set the actualTurn to this color.
     */
    protected void setActualTurn(final Color actualTurn) {
        this.actualTurn = actualTurn;
    }

}
