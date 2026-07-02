package it.unibo.bmbman.model.entities;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
/**
 * Class used to model the behavior of a wall.
 */
public class Wall extends AbstractEntity {
    /**
     * Create a wall.
     * @param position position of the wall
     * @param dimension dimension of the wall
     */
    public Wall(final Position position, final Dimension dimension) {
        super(position, EntityType.WALL, dimension);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final Collision c) {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove() {
        return false;
    }
}

