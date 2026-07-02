package it.unibo.bmbman.model.entities;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.collision.CollisionComponent;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
/**
 * 
 * models general aspects of entities.
 *
 */
public interface Entity {
    /**
     *
     * @return entity's position in the world
     */
    Position getPosition();
    /**
     * Used to set entity's position.
     * @param position new entity's position
     */
    void setPosition(Position position);
    /**
     * Used to know if the entity must be removed.
     * @return boolean value
     */
    boolean remove();
    /**
     * Getter for {@link Dimension}.
     * @return entity's dimension
     */
    Dimension getDimension();
    /**
     * Getter for {@link EntityType}.
     * @return entity's type
     */
    EntityType getType();
    /**
     * Used to get {@link CollisionComponent}.
     * @return the {@link CollisionComponent} associated with this entity
     */
    CollisionComponent getCollisionComponent();
    /**
     * Used to modify entity behavior after collision.
     * @param c the {@link Collision}
     */
    void onCollision(Collision c);
    /**
     * Used to update entity status during the game.
     */
    void update();
}
