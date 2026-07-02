package it.unibo.scat.model.api;

import it.unibo.scat.common.EntityType;
import it.unibo.scat.model.game.entity.AbstractEntity;

/**
 * Functional interface for creating game entities.
 */
@FunctionalInterface
public interface EntityFactory {

    /**
     * Creates a new entity instance of the specified position.
     * 
     * @param type the type of entity to create.
     * @param x    the initial x coordinate.
     * @param y    the initial y coordinate.
     * @return a new instance of {@link AbstractEntity}.
     */
    AbstractEntity createEntity(EntityType type, int x, int y);
}
