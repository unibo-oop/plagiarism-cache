package it.unibo.modularcheckers.model.move;

import java.util.List;

/**
 * Interface for the Move Class. The first move contains the starting coordinate of the piece moving.
 * The following contains all the coordinate where the piece is going.
 */
public interface Move extends Iterable<Step> {

    /**
     * Get a list containing all the steps done in a move.
     * @return an unmodifiable list containing all the steps of the move.
     */
    List<Step> getSteps();
}
