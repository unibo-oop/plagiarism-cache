package it.unibo.isaccoop.model.common;

import it.unibo.isaccoop.graphics.Graphics;
import it.unibo.isaccoop.model.boundingbox.BoundingBox;

/**
 * Interface to model elements.
 */
public interface MapElement {

    /**
     * Get element coords.
     * @return the coordinates of a map element
     */
    Point2D getCoords();

    /**
     *  Set new coords to map element.
     *
     *  @param coords new coords
     * */
    void setCoords(Point2D coords);
    /**
     * Getter for bounding box.
     * @return bounding box refers to this mapElement.
     */
    BoundingBox getBox();

    /**
     * Update element graphics.
     * @param g
     * */
    void updateGraphics(Graphics g);
}
