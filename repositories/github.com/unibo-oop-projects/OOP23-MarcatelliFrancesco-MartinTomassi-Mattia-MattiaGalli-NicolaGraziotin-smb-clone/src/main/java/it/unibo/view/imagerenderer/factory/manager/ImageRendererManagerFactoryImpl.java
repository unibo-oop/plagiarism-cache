package it.unibo.view.imagerenderer.factory.manager;

import it.unibo.controller.GameController;
import it.unibo.view.imagerenderer.manager.ImageRendererManager;
import it.unibo.view.imagerenderer.manager.ImageRendererManagerImpl;

/**
 * The ImageRendererManagerFactoryImpl class is an implementation of the ImageRendererManagerFactory interface.
 * It provides a concrete implementation of the factory method createImageRendererManager(), instantiating
 * ImageRendererManager objects.
 */
public class ImageRendererManagerFactoryImpl implements ImageRendererManagerFactory {

    @Override
    public final ImageRendererManager createImageRendererManager(final GameController controller) {
        return new ImageRendererManagerImpl(controller);
    }

}
