package it.unibo.oop18.cfc.Graphics;

import java.awt.Graphics2D;

/**
 * This interface declares the graphic component of any GameObject.
 */
public interface GraphicsComponent {

    /**
     * Renders method for any component.
     *
     * @param g graphics to draw.
     */
    void draw(Graphics2D g);
}
