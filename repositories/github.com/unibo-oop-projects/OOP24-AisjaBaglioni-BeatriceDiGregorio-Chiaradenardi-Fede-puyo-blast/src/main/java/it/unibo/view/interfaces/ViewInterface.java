package it.unibo.view.interfaces;

import java.awt.Graphics;

/**
 * This interface is implemented by almost all views in the project.
 * It defines the method required for rendering the view onto the screen.
 */
public interface ViewInterface {
    /**
     * Draws the view on the provided graphics context.
     * Typically, this method uses {@link java.awt.Graphics#drawImage}, which
     * takes these parameters:
     * - The image to be drawn
     * - The upper-left and lower-right corners of the target area
     * - The upper-left and lower-right corners of the source image
     * - The observer of the image
     * 
     * The positioning of objects is based on the {@link Scale} as a reference,
     * with offsets calculated using the size of a single Puyo.
     * 
     * @param g The graphics context to draw the view.
     */
    void draw(final Graphics g);
}
