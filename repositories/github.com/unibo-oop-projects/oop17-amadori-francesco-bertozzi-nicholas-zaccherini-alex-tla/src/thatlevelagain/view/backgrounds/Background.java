package thatlevelagain.view.backgrounds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 * Background interface.
 *
 */
public interface Background {

    /**
     * set the Image from path.
     * 
     * @param image
     *   image to set.
     * 
     */
    void setBackgroundImage(BufferedImage image);
    /**
     * return image.
     * @return
     *   image loaded.
     */
    BufferedImage getBackgroundImage();

    /**
     * 
     * @param g
     *   g where draw the image.
     */
    void draw(Graphics2D g);
}
