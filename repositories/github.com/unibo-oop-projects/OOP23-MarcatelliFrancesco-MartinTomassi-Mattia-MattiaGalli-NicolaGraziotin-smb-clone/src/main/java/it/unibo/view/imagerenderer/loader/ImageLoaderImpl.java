package it.unibo.view.imagerenderer.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import it.unibo.view.imagerenderer.manager.ImageType;

/**
 * The ImageLoaderImpl class provides functionality to load images based on the specified ImageType.
 */
public class ImageLoaderImpl implements ImageLoader {

    /**
     * Loads the image associated with the specified ImageType.
     *
     * @param imageType The type of image to load.
     * @return The loaded BufferedImage.
     */
    @Override
    public BufferedImage load(final ImageType imageType) {
        try {
            return getImage(imageType);
        } catch (IOException e) {
            Logger.getLogger(ImageLoaderImpl.class.getName())
                .severe("An error occurred: " + e.getMessage());
        }
        return new BufferedImage(0, 0, 0);
    }

    /**
     * Loads the image file associated with the specified ImageType.
     *
     * @param imageType The type of image to load.
     * @return The loaded BufferedImage.
     * @throws IOException if an I/O error occurs.
     */
    private BufferedImage getImage(final ImageType imageType) throws IOException {
        return ImageIO.read(getClass()
            .getClassLoader()
            .getResourceAsStream(imageType.toString()));
    }

}
