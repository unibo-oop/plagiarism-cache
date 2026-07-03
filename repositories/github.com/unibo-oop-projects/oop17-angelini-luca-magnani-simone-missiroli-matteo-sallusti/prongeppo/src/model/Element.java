package model;

import java.awt.Point;
import java.awt.Shape;

/**
 * @author Missi
 *
 */
public interface Element {
    /**
     * @return **the position of the element in the modelled world**
     */
    Point getPosition();
    /**
     * @param p **the new position to be assumed by the element**
     */
    void setPosition(Point p);
    /**
     * @return the hittable shape of the element
     */
    Shape getHitbox();
}
