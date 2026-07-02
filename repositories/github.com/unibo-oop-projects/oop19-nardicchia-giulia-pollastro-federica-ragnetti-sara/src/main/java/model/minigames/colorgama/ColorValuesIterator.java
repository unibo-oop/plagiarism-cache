package model.minigames.colorgama;

import java.util.Iterator;

import org.apache.commons.lang3.tuple.Triple;

/**
 * This interface represents color values of the minigame to iterate.
 *
 */
public interface ColorValuesIterator extends Iterator<Triple<Number, Number, Number>> {

    /**
     * Get a index of the right color.
     * 
     * @return index
     *          right color's index
     */
    int getRightColorIndex();

    /**
     * Get the right color values.
     * 
     * @return values
     *          the component of the right color
     */
    Triple<Number, Number, Number> getRightColorValues();

    /**
     * Set the right color values.
     * 
     * @param rightValues
     *          a right color values
     */
    void setRightColorValues(Triple<Number, Number, Number> rightValues);
}
