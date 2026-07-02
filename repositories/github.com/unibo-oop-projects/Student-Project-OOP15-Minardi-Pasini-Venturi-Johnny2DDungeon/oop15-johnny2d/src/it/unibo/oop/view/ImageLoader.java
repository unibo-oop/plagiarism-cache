package it.unibo.oop.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A simple utility class to load an image.
 */
public final class ImageLoader {

    private ImageLoader() {
    }

    /**
     * Loads the image with the specified name.
     * 
     * @param name
     *            the name of the image to load
     * @return the resulting {@link java.awt.image.BufferedImage}
     * @throws IOException
     *             if the image name is invalid
     */
    public static BufferedImage load(final String name) throws IOException {
        return ImageIO.read(ImageLoader.class.getResourceAsStream(name));
    }
}