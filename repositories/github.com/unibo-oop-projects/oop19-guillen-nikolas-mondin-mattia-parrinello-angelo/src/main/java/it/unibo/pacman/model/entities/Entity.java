package it.unibo.pacman.model.entities;

import java.awt.Rectangle;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
/**
 * An interface for {@link Entity} model.
 */
public interface Entity {
    /**
     * Used to know the space occupied by an Entity.
     * 
     * @return the Rectangle that represents the occupied space.
     */
    Rectangle getBounds();
    /**
     * Used to know the position of an Entity in the game world.
     * 
     * @return the Position that keeps the (x,y) coordinates.
     */
    Position getPosition();
    /**
     * Used to know what kind of Entity is.
     * 
     * @return the type of the Entity.
     */
    EntityType getType();
}
