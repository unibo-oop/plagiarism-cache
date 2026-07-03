package oop.lit.controller.geometry;

import oop.lit.util.Vector2D;

/**
 * Interface for geometry transforms.
 */
public interface Transforms {

    /**
     * If possible move all selected elements.
     * 
     * @param distance
     *            vector of the transform.
     */
    void moveSelected(Vector2D distance);

    /**
     * If possible rotate all selected elements.
     * 
     * @param angle
     *            of the transform.
     */
    void rotateSelected(double angle);

    /**
     * If possible resize all selected elements.
     * 
     * @param scalar
     *            of the transform.
     */
    void resizeSelected(double scalar);
}
