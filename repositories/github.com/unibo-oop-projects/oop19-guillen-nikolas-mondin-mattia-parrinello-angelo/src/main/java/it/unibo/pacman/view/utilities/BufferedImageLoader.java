package it.unibo.pacman.view.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Buffers the image.
 */
public final class BufferedImageLoader {
    /**
     * Image needed to be buffered.
     */
    private static BufferedImage image;

    private BufferedImageLoader() {
    }
    /**
     * Loads an image.
     * 
     * @param path taken to load the image.
     * @return image.
     */
    public static BufferedImage loadImage(final String path) {
        try {
            image = ImageIO.read(ClassLoader.getSystemResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
