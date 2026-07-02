package it.unibo.geometrybash.view.renderer;

import java.awt.Graphics2D;
import it.unibo.geometrybash.view.core.RenderContext;

/**
 * A generic interface for objects capable of rendering a specific type of game entitie.
 *
 * @param <T> the type of data transfer objects (DTO) to be rendered
 */
@FunctionalInterface
public interface Drawable<T> {

    /**
     * Renders the given data into the graphics context.
     *
     * @param g2d the graphics context
     * @param renderContext the rendering context like camera and viewport
     * @param data tha data, which can be DTO or a list, to render
     */
    void draw(Graphics2D g2d, RenderContext renderContext, T data);

}
