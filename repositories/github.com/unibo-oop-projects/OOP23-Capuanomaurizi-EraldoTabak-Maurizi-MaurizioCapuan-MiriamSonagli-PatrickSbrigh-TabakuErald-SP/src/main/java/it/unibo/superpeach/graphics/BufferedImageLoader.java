package it.unibo.superpeach.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * Resource image loading class.
 * 
 * @author Maurizio Capuano
 */
public final class BufferedImageLoader {

    /**
     * @param imgPath ClassLoader resource path
     * @return BufferedImage containing the retrieved image or null if method fails
     */
    public BufferedImage loadImage(final String imgPath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemResource(imgPath));
        } catch (IOException e) {
            Logger.getLogger("IOException in loadImage()");
        }
        return img;
    }

}
