package it.unibo.view.imageresizer.factory;

import it.unibo.view.imageresizer.ImageResizer;

/**
 * The ImageResizerFactory interface represents a factory for creating ImageResizer objects.
 * Implementations of this interface are responsible for creating instances of ImageResizer.
 */
public interface ImageResizerFactory {

    /**
     * Creates an ImageResizer object.
     *
     * @return an ImageResizer object.
     */
    ImageResizer createImageResizer();

}
