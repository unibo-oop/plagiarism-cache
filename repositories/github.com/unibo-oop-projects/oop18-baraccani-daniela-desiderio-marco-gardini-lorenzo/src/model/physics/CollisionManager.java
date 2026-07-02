package model.physics;

import model.entitiesutil.EntityMovable;

/**
 * Interface of the collision manager to check collisions between
 * {@link Entity}s.
 */
public interface CollisionManager {

    /**
     * Check collisions between all {@link Entity}s.
     */
    void collision();

    /**
     * Check collisions between a specific {@link Entity} and all other non {@link EntityMovable}.
     * 
     * @param entityMoved is the {@link Entity} to check
     */
    void collision(EntityMovable entityMoved);

}

