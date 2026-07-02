package it.unibo.model.interfaces;

/**
 * An interface representing the scale, or the size of the game window,
 * serving as the unit of measurement for all its elements
 */
public interface ScaleInterface {
    /**
     * Retrieves the scale value, representing both the width and height of the
     * window.
     * 
     * @return the scale size
     */
    int getScale();
}
