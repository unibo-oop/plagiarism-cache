package it.unibo.view.imagerenderer.loader;

import java.awt.image.BufferedImage;

import it.unibo.view.imagerenderer.manager.ImageType;

/**
 * The ImageLoader interface provides functionality to load images based on the specified ImageType.
 */
public interface ImageLoader {

    /**
     * Loads the image associated with the specified ImageType.
     *
     * @param imageType The type of image to load.
     * @return The loaded BufferedImage.
     */
    BufferedImage load(ImageType imageType);

}
