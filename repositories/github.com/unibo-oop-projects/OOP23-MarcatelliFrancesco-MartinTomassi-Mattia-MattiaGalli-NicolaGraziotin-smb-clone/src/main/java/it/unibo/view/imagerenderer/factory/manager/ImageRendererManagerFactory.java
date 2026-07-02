package it.unibo.view.imagerenderer.factory.manager;

import it.unibo.controller.GameController;
import it.unibo.view.imagerenderer.manager.ImageRendererManager;

/**
 * The ImageRendererManagerFactory interface defines a contract for creating instances of ImageRendererManager.
 */
public interface ImageRendererManagerFactory {

    /**
     * Creates a new instance of ImageRendererManager with the specified GameController.
     *
     * @param controller the GameController instance to be associated with the ImageRendererManager.
     * @return a new instance of ImageRendererManager.
     */
    ImageRendererManager createImageRendererManager(GameController controller);

}

