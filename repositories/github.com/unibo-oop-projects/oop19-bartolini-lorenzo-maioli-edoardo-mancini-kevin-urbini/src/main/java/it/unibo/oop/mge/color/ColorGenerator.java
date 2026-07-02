package it.unibo.oop.mge.color;

import java.awt.Color;

/**
 * The Interface ColorGenerator.
 */
public interface ColorGenerator {

    /**
     * Gets the color from double.
     *
     * @param value the value
     * @return the color associated to the given value, if the ColorGenerator has
     *         been initialize without specifying the range the color will be always
     *         the same no matter the value.
     */
    Color getColorFromDouble(Double value);

}
