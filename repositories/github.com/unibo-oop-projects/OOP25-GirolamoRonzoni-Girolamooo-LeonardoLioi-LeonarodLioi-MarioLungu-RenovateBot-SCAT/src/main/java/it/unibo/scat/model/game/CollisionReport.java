package it.unibo.scat.model.game;

import java.util.ArrayList;
import java.util.List;

import it.unibo.scat.model.game.entity.AbstractEntity;

/**
 * This class handles the reports of collisions.
 */
public class CollisionReport {
    private final List<AbstractEntity> entities;
    private final boolean collisions;

    /**
     * CollisionReport constructor.
     * 
     * @param entities entities that were part of a collision.
     */
    public CollisionReport(final List<AbstractEntity> entities) {
        this.entities = new ArrayList<>(entities);

        collisions = !entities.isEmpty();
    }

    /**
     * Entities getter.
     * 
     * @return the list of entities that were part of a collision.
     */
    public List<AbstractEntity> getEntities() {
        return List.copyOf(entities);
    }

    /**
     * Collisions boolean getter.
     * 
     * @return true if there were collisions, false otherwise.
     */
    public boolean hasCollisions() {
        return collisions;
    }

}
