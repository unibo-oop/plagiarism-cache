package main.java.com.view;

import java.awt.Graphics;

/**
 * Interface that models a drawable game entity.
 *
 */
public interface DrawableGameEntity {

    /**
     * Draws the game entity on the screen.
     * @param g the Graphics
     */
    void draw(Graphics g);
}
