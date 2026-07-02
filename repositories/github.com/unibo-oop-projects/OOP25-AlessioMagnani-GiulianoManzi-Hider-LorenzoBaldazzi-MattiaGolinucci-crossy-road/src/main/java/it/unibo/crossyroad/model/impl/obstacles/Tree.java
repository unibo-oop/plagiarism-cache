package it.unibo.crossyroad.model.impl.obstacles;

import it.unibo.crossyroad.model.api.AbstractPositionable;
import it.unibo.crossyroad.model.api.obstacles.CollisionType;
import it.unibo.crossyroad.model.api.Dimension;
import it.unibo.crossyroad.model.api.EntityType;
import it.unibo.crossyroad.model.api.obstacles.Obstacle;
import it.unibo.crossyroad.model.api.Position;
import it.unibo.crossyroad.model.impl.chunks.Grass;

/**
 * Passive obstacle, spawns on Grass.
 * 
 * @see Grass
 */
public final class Tree extends AbstractPositionable implements Obstacle {

    /**
     * Initializes the Obstacle.
     * 
     * @param initialPosition the Obstacle's initial position.
     * 
     * @param dimension the Obstacle's dimension.
     */
    public Tree(final Position initialPosition, final Dimension dimension) {
        super(initialPosition, dimension);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.TREE;
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.SOLID;
    }
}
