package view;

import java.awt.Image;
import java.awt.Point;

/**
 * @author Missi
 *
 */
public interface PongElement {

    /**
     * @param p **the point where the elem have to be**
     */
    void setPosition(Point p);

    /**
     * @return the position of the elem
     */
    Point getPosition();

    /**
     * @return the image of the elem
     */
    Image getImage();

    /**
     * @return the width of the elem
     */
    int getWidth();

    /**
     * @return the height  of the elem
     */
    int getHeight();

    /**
     * @param b **is the visibility of the elem**
     */
    void setVisible(boolean b);

    /**
     * @return true if the elem is visible, false otherwise
     */
    boolean isVisible();

}
