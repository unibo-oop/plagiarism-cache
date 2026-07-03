package oop.lit.model;

import oop.lit.util.Vector2D;

/**
 * A game element on a board.
 * Observers will be notified when this element changes his position, size or rotation (and when this changes his image).
 * This interface is used to hide unnecessary methods to view and controller.
 */
public interface BoardElementModel extends GameElementModel {

    /**
     * @return  the position of the element
     */
    Vector2D getPosition();

    /**
     * @return  the scale of the element
     */
    double getScale();

    /**
     * @return  the (clockwise) rotation of the element in degrees
     */
    double getRotation();

}