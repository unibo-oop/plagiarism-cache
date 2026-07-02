package it.unibo.view.imagerenderer.factory.gameobjects;

import it.unibo.view.imagerenderer.gameobjects.ImageRendererSaws;

/**
 * The ImageRendererSawsFactory interface defines a contract for creating instances of ImageRendererSaws.
 */
public interface ImageRendererSawsFactory {

    /**
     * Creates a new instance of ImageRendererSaws with the specified number of rows and columns.
     *
     * @param numRows    the number of rows for the ImageRendererSaws.
     * @param numColumns the number of columns for the ImageRendererSaws.
     * @return a new instance of ImageRendererSaws.
     */
    ImageRendererSaws createImageRendererSaws(int numRows, int numColumns);

}

