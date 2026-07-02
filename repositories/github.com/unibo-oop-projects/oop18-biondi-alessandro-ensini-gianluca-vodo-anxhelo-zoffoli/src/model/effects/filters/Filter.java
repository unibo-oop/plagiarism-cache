package model.effects.filters;

import java.awt.Color;

import model.effects.Effect;

/**
 * Represents the contract for a generic filter. A filter change an image based
 * on a rule to change every single pixel.
 */
public interface Filter extends Effect {

    /**
     * Change a single pixel. This method is used by apply.
     * 
     * @param source pixel
     * @return modified pixel
     */
    Color changePixel(Color source);
}
