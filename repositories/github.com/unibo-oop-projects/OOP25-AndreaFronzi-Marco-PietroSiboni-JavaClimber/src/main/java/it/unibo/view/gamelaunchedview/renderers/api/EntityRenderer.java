package it.unibo.view.gamelaunchedview.renderers.api;

import java.awt.Graphics2D;
import java.util.List;

import it.unibo.model.gameobj.api.GameObject;

/**
 * Interface for rendering game entities.
 *
 * @param <T> the type of game entity to render, which must extend
 *            {@link GameObject}
 */
@FunctionalInterface
public interface EntityRenderer<T extends GameObject> {

    /**
     * Renders a list of entities of type T using the provided Graphics2D
     * object.
     *
     * @param entities the list of entities to render
     * @param g the Graphics2D object used for rendering
     */
    void render(List<T> entities, Graphics2D g);
}
