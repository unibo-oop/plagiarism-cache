package it.unibo.view.imagerenderer.factory.gameobjects;

import it.unibo.view.imagerenderer.gameobjects.ImageRendererStationary;

/**
 * The ImageRendererStationaryFactory interface defines a contract for creating instances of ImageRendererStationary.
 */
public interface ImageRendererStationaryFactory {

    /**
     * Creates a new instance of ImageRendererStationary with the specified number of rows and columns.
     *
     * @param numRows    the number of rows for the ImageRendererStationary.
     * @param numColumns the number of columns for the ImageRendererStationary.
     * @return a new instance of ImageRendererStationary.
     */
    ImageRendererStationary createImageRendererStationary(int numRows, int numColumns);

}

