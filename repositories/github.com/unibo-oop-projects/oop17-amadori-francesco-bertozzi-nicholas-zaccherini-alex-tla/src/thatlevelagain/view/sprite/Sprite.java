package thatlevelagain.view.sprite;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * 
 * Sprite interface.
 *
 */
public interface Sprite {
    /**
     * 
     * @param g
     *         where draw img
     */
    void disegna(Graphics2D g);
    /**
     * 
     * @return
     *         img location.
     */
    Dimension getLocation();

    /**
     * 
     * @return
     *      x position
     */
    int getX();
    /**
     * 
     * @return
     *      y position
     */
    int getY();
    /**
     * 
     * @return
     *        shape's width
     */
    int getWidth();
    /**
     * 
     * @return
     *         shape's height
     */
    int getHeight();

    /**
     * 
     * @param x
     *         set x value.
     */
    void setX(int x);
    /**
     * 
     * @param y
     *         set y value.
     */
    void setY(int y);
    /**
     * 
     * @param width
     *         set width value.
     */
    void setWidth(int width);
    /**
     * 
     * @param height
     *         set height value.
     */
    void setHeight(int height);

    /**
     * 
     * @return
     *         image.
     */
    BufferedImage getImage();

    /**
     * 
     * @param image
     *         set image.
     */
    void setImage(BufferedImage image);
    /**
     * 
     * @return
     *         Shape's rectangle dimension.
     */
    Rectangle getBounds(); //per il collisiondetection
    /**
     * 
     * @return
     *         Shape's up rectangle dimension.
     */
    Rectangle getRectUp();
    /**
     * 
     * @return
     *         Shape's Bottom rectangle dimension.
     */
    Rectangle getRectBottom();
    /**
     * 
     * @return
     *         Shape's left rectangle dimension.
     */
    Rectangle getRectLeft();
    /**
     * 
     * @return
     *         Shape's right rectangle dimension.
     */
    Rectangle getRectRight();
}
