package it.unibo.view.imagerenderer.gameobjects;

import java.awt.image.BufferedImage;
import java.util.List;

import it.unibo.model.entity.obstacles.CircularSaw;

/**
 * The ImageRendererSaws interface defines methods for rendering circular saws in a game as a BufferedImage.
 */
public interface ImageRendererSaws {

    /**
     * Renders the circular saws represented by the provided List of CircularSaw objects
     * into a BufferedImage.
     *
     * @param saws The List of CircularSaw objects representing the circular saws.
     * @return A BufferedImage representing the circular saws.
     */
    BufferedImage render(List<CircularSaw> saws);

}
