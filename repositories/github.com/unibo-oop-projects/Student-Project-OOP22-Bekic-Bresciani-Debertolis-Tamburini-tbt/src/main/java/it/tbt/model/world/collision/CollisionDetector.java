package it.tbt.model.world.collision;

import it.tbt.model.entities.SpatialEntity;

/**
 * CollisionDetector interface to define the Collision Logic between SpatialEntities.
 */
public interface CollisionDetector {
    /**
     * @param sp1
     * @param sp2
     * @return True if the collision between the two params is to be considered happened.
     * False otherwise.
     */
    Boolean checkCollision(SpatialEntity sp1, SpatialEntity sp2);
}
