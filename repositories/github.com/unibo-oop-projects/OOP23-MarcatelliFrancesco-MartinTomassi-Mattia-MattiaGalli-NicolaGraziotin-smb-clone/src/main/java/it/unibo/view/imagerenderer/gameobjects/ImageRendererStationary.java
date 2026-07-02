package it.unibo.view.imagerenderer.gameobjects;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import it.unibo.model.tiles.Tile;

/**
 * The ImageRendererStationary interface defines methods for rendering stationary tiles in a game as a BufferedImage.
 */
public interface ImageRendererStationary {

    /**
     * Renders the stationary tiles represented by the provided 2D List of Optional Tile objects
     * into a BufferedImage.
     *
     * @param stationary A 2D List of Optional Tile objects representing the stationary tiles.
     * @return A BufferedImage representing the stationary tiles.
     */
    BufferedImage render(List<List<Optional<Tile>>> stationary);

}
