package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import utilities.Position;

/**
 * Interface for all the objects in the game.
 */
public interface GameObject {

    /**
     * Getter for the position of the object.
     * @return object's position
     */
    Position getPosition();

    /**
     * Getter for the rectangle of the body.
     * @return body's rectangle
     */
    Rectangle2D getBounds();

    /**
     * Check if the object is solid.
     * @return true if the object is solid, otherwise false
     */
    boolean isSolid();

    /**
     * Check if the object is breakable.
     * @return true if the object is breakable, otherwise false
     */
    boolean isBreakable();

    /**
     * Sprite object rendering.
     * @param g : component that draw the sprite
     */
    void render(Graphics2D g);
}
