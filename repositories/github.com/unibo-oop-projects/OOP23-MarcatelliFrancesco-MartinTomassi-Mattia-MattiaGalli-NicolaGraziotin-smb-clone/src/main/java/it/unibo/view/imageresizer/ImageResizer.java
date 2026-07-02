package it.unibo.view.imageresizer;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The ImageResizer interface provides method for resizing a list of BufferedImages.
 */
public interface ImageResizer {

    /**
     * Resizes a list of BufferedImages.
     *
     * @param images The list of BufferedImages to resize.
     * @return A list of resized BufferedImages.
     */
    List<BufferedImage> resize(List<BufferedImage> images);

}
