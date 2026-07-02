package model.movement;

import javafx.util.Pair;
import utilities.Directions;

/**
 *
 * Rastaldi Martina.
 *
 */
public interface Movement {
    /**
     *
     * @param selected
     *            coordinates of the selected Element
     * @param direction
     *            direction which to move the Hero
     * @return true if the movement is successful, else otherwise
     */
    boolean move(Pair<Integer, Integer> selected, Directions direction);

    /**
     *
     * @param selected
     *            coordinates of the selected Element
     * @param newPosition
     *            newPosition of the selected Element
     */
    void moveTo(Pair<Integer, Integer> selected, Pair<Integer, Integer> newPosition);

    /**
     *
     * @param coord
     *            coordinates of Hero to remove
     */
    void resetHero(Pair<Integer, Integer> coord);

    /**
     *
     * @param selected
     *            coordinates of the selected Element
     * @return true if the rotation is successful, else otherwise
     */
    boolean rotate(Pair<Integer, Integer> selected);
}
