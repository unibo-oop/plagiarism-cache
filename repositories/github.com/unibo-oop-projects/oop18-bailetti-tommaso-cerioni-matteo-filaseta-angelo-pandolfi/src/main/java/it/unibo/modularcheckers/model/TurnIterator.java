package it.unibo.modularcheckers.model;

import java.util.Iterator;

/**
 * Custom iterator for turn order.
 */
public interface TurnIterator extends Iterator<Color> {

    /**
     * Skip the passed color in the iteration.
     * @param color the color to skip in the iteration.
     */
    void skipColor(Color color);

    /**
     * Re-add the color to the iteration.
     * @param color the color to revive.
     */
    void reviveColor(Color color);

    /**
     * @return the actual turn.
     */
    Color getActualTurn();

}
