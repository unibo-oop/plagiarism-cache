package controllers.movement.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 
 * Animation.
 *
 */
public interface AnimationInterface {

    /**
     * @param list list of Images
     * 
     * Method to initialize first frame.
     * 
     */
    void init(List<BufferedImage> list);

    /**
     * @param list list of Images
     * 
     * method to run animation with the next frame.
     * 
     */
    void runAnimation(List<BufferedImage> list);

    /**
     * @param list list of Images
     *
     * method to get next frame image.
     * 
     */
    void nextFrame(List<BufferedImage> list);

    /**
     * @param g where to draw the image
     * @param x coordinate x
     * @param y coordinate y
     * 
     * method to draw the animation.
     * 
     */
    void drawAnimation(Graphics g, int x, int y);

}
