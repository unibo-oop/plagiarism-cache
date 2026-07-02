package frogger.model.interfaces;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import frogger.common.Pair;
import frogger.common.Position;

/**
 * Interface representing a generic game object in the Frogger game.
 * A GameObject has a position, dimensions, a graphical representation (image),
 * and a hitbox for collision detection.
 */
public interface GameObject {

    /**
     * Gets the current position of the game object in the game world.
     *
     * @return the object's current {@link Position}
     */
    Position getPos();

    /**
     * Sets a new position for the game object.
     *
     * @param pos the new {@link Position} to assign
     */
    void setPos(Position pos);

    /**
     * Gets the dimensions (width and height) of the game object.
     *
     * @return a {@link Pair} representing the object's dimensions in blocks
     */
    Pair getDimension();

    /**
     * Returns the hitbox used for collision detection.
     *
     * @return a {@link Rectangle2D.Float} representing the object's hitbox
     */
    Rectangle2D.Float getHitBox();

    /**
     * Gets the image used to visually represent the game object.
     *
     * @return the {@link BufferedImage} associated with the object
     */
    BufferedImage getImage();

    /**
     * Sets the image used to represent the game object.
     * The image is loaded from a file name using the resource loader.
     *
     * @param fileName the name of the image file to load
     */
    void setImage(String fileName);
}
