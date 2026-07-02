package view.graphics_util;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Interface for importing images.
 */
public interface ImportImage {

    /**
     * Loads an image from the given path.
     *
     * @param imgPath the path of the image to load
     * @return an Optional containing the loaded image, or empty if loading failed
     */
    public Optional<BufferedImage> imp(final String imgPath);
}
