package it.unibo.bmbman.view.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class to load an image from res folder.
 */
public final class ImageLoaderUtils {
    private static BufferedImage image;

    private ImageLoaderUtils() {
    }
    /**
     * Load an image and return it.
     * @param text the image path
     * @return the buffered image
     */
    public static BufferedImage loadImage(final String text) {
        try {
            image = ImageIO.read(Object.class.getResource(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
