package it.unibo.view.imagerenderer.manager;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The ImageRendererManager interface is responsible for managing the rendering of the game map.
 */
public interface ImageRendererManager {

    /**
     * Manage the rendering of circular Saws, Stationary Tiles, Background and MeatBoy into a list of BufferedImages.
     *
     * @return A list of BufferedImages representing the rendered game elements.
     */
    List<BufferedImage> render();

}
