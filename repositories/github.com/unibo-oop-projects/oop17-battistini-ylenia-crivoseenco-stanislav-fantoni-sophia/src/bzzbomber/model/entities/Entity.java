package bzzbomber.model.entities;

import java.awt.Point;
import java.awt.Rectangle;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Model of a game entity.
 * 
 * 
 */
public interface Entity {

    /**
     * Get the position of entity.
     * 
     * @return The current position
     */
    Point getPosition();

    /**
     * Set the entity's position.
     * 
     * @param position
     *            The entity's position.
     */
    void setPosition(Point position);

    /**
     * Get rectangle for collision.
     * 
     * @return the collision box for each entity.
     */
    Rectangle getCollisionBox();

    /**
     * Set the position of collision box.
     * 
     * @param point
     *            The point to set the box.
     */
    void setCollisionBox(Point point);

    /**
     * Get the tile of entity.
     * 
     * @return the @Tile of entity.
     */

    TileImpl getTile();
}
