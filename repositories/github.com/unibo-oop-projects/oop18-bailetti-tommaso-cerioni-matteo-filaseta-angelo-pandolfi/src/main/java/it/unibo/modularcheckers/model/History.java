package it.unibo.modularcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.modularcheckers.model.move.Move;

/**
 * Tracks all of the movements in the game.
 */
public class History {

    private final List<Move> actions;
    private int currentMove;

    /**
     * Constructs an empty list of Moves.
     */
    public History() {
        this.actions = new ArrayList<>();
        this.currentMove = 0;
    }

    /**
     * Returns the iterator for the moves list.
     *
     * @return Iterator of the list
     */
    public Iterator<Move> getIterator() {
        return this.actions.iterator();
    }

    /**
     * Returns the last move in the List.
     *
     * @return last element of the List
     */
    public Move getLastMove() {
        return getMove(this.currentMove);
    }

    /**
     * Returns the move in the current position.
     *
     * @param index index of the element to return
     * @return the element specified by the index
     */
    public Move getMove(final int index) {
        return this.actions.get(index);
    }

    /**
     * Sets the next move to the bottom of the List.
     *
     * @param move the move to be added to bottom
     */
    public void setMove(final Move move) {
        this.actions.add(move);
        this.currentMove++;
    }

    /**
     * Inserts the move in the index, destroying every Move done afterwards.
     *
     * @param move move to be added to the list
     * @param index index of the list where the move should be inserted
     */
    public void setMove(final Move move, final int index) {
        this.actions.subList(index, this.currentMove).clear();
        setMove(move);
    }

    /**
     * Deletes from list and returns the last move removed.
     *
     * @return the last move that has been removed

    public Move undoLastMove() {
        final Move m = new MoveImpl(this.actions.remove(this.actions.size() - 1).getSteps());
        this.currentMove--;
        return m;
    }
    */
}
