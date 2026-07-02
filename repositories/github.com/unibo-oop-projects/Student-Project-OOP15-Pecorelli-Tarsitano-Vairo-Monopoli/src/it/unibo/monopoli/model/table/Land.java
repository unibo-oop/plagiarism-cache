package it.unibo.monopoli.model.table;

import java.awt.Color;

/**
 * This interface represent all the lands. Lands are {@link Ownership}s on which
 * you can build {@link Building}s.
 *
 */
public interface Land extends Ownership {

    /**
     * Return the {@link Ownership}'s {@link Color}.
     * 
     * @return {@link Ownership}'s {@link Color}
     */
    Color getColor();

}
