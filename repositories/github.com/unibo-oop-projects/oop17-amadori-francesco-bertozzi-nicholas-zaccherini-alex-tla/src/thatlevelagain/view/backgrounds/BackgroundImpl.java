package thatlevelagain.view.backgrounds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * abstract class for background.
 */
public class BackgroundImpl implements Background {

    private BufferedImage image;

    /**
     * set the Image from path.
     * 
     * @param image
     *   image to set.
     * 
     */
    public void setBackgroundImage(final BufferedImage image) {
        this.image = image;
    }
    /**
     * return image.
     * @return
     *   image loaded.
     */
    public final BufferedImage getBackgroundImage() {
        return image;
    }

    /**
     * 
     * @param g
     *   g where draw the image.
     */
    public final void draw(final Graphics2D g) {
        g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
    }
}
