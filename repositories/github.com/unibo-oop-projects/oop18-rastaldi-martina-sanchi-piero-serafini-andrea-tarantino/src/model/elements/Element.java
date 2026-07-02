package model.elements;

import java.io.Serializable;

import javafx.util.Pair;
import utilities.Colors;
import utilities.Directions;
import utilities.Elements;

/**
 * Interface to be implemented to create movable board objects.
 * Andrea Serafini.
 *
 */
public interface Element extends Serializable {

    /**
     * @return the actual position of the element
     */
    Pair<Integer, Integer> getActualPosition();

    /**
     *
     * @return the color of the element
     */
    Colors getColor();

    /**
     * @return the string of the element
     */
    default String getElementString() {
        return "(" + this.getType() + "->" + this.getActualPosition() + ")";
    }

    /**
     * @return the type of the element
     */
    Elements getType();

    /**
     *
     * @param direction
     *            of the movement
     * @return true if the element has moved
     */
    boolean move(Directions direction);

    /**
     * @param next
     *            the new position of this element
     */
    void setNewPosition(Pair<Integer, Integer> next);

}
