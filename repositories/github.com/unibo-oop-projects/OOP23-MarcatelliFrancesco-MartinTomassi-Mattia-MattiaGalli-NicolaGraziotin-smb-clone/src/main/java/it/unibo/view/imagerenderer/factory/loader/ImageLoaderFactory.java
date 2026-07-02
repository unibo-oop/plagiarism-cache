package it.unibo.view.imagerenderer.factory.loader;

import it.unibo.view.imagerenderer.loader.ImageLoader;

/**
 * The ImageLoaderFactory interface defines a contract for creating instances of ImageLoader.
 */
public interface ImageLoaderFactory {

    /**
     * Creates a new instance of ImageLoader.
     *
     * @return a new instance of ImageLoader.
     */
    ImageLoader createImageLoader();

}

