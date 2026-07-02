package it.unibo.view.imagerenderer.factory.gameobjects;

import it.unibo.view.imagerenderer.gameobjects.ImageRendererStationary;
import it.unibo.view.imagerenderer.gameobjects.ImageRendererStationaryImpl;

/**
 * The ImageRendererStationaryFactoryImpl class is an implementation of the ImageRendererStationaryFactory interface.
 * It provides a concrete implementation of the factory method createImageRendererStationary(), instantiating
 * ImageRendererStationary objects.
 */
public class ImageRendererStationaryFactoryImpl implements ImageRendererStationaryFactory {

    @Override
    public final ImageRendererStationary createImageRendererStationary(final int numRows, final int numColumns) {
        return new ImageRendererStationaryImpl(numRows, numColumns);
    }

}
