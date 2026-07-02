package it.unibo.view.imagerenderer.manager;

import java.awt.image.BufferedImage;
import java.util.List;

import it.unibo.controller.GameController;
import it.unibo.view.imagerenderer.factory.gameobjects.ImageRendererSawsFactoryImpl;
import it.unibo.view.imagerenderer.factory.gameobjects.ImageRendererStationaryFactoryImpl;
import it.unibo.view.imagerenderer.factory.loader.ImageLoaderFactoryImpl;
import it.unibo.view.imagerenderer.gameobjects.ImageRendererSaws;
import it.unibo.view.imagerenderer.gameobjects.ImageRendererStationary;
import it.unibo.view.imagerenderer.loader.ImageLoader;

/**
 * The ImageRendererManager class is responsible for managing the rendering of the game map.
 */
public class ImageRendererManagerImpl implements ImageRendererManager {

    private final GameController gameController;
    private final ImageRendererSaws imageRendererSaws;
    private final ImageRendererStationary imageRendererStationary;
    private final ImageLoader imageLoader;

    /**
     * Constructs an ImageRendererManager with the specified GameController.
     *
     * @param gameController The GameController associated with the game map.
     */
    public ImageRendererManagerImpl(final GameController gameController) {
        this.gameController = gameController;
        this.imageRendererSaws = new ImageRendererSawsFactoryImpl()
            .createImageRendererSaws(
                this.gameController.getNumRows(),
                this.gameController.getNumCols());
        this.imageRendererStationary = new ImageRendererStationaryFactoryImpl()
            .createImageRendererStationary(
                this.gameController.getNumRows(),
                this.gameController.getNumCols());
        this.imageLoader = new ImageLoaderFactoryImpl()
            .createImageLoader();
    }

    /**
     * Manage the rendering of circular Saws, Stationary Tiles, Background and MeatBoy into a list of BufferedImages.
     *
     * @return A list of BufferedImages representing the rendered game elements.
     */
    @Override
    public List<BufferedImage> render() {
        return List.of(
            this.imageLoader.load(ImageType.BACKGROUND),
            this.imageRendererStationary.render(this.gameController.getStationary()),
            this.imageRendererSaws.render(this.gameController.getSaws()),
            this.imageLoader.load(ImageType.MEAT_BOY));
    }

}
