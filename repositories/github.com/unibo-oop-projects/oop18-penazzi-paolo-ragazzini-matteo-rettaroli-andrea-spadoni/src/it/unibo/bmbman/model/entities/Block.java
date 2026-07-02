package it.unibo.bmbman.model.entities;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
/**
 *Class used to model the behavior of a block.
 *
 */
public class Block extends AbstractEntity {
    private boolean isBroken;
    /**
     * Crate a breakable wall.
     * @param position the block position
     * @param dimension the block dimension
     */
    public Block(final Position position, final Dimension dimension) {
        super(position, EntityType.BLOCK, dimension);
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
        return isBroken;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final Collision c) {
        if (c.getReceiver().getType() == EntityType.BOMB) {
            isBroken = true;
        }
    }
}
