package it.unibo.bmbman.model.collision;

import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.Position;
/**
 * the implementation of {@link Collision}.
 *
 */
public class CollisionImpl implements Collision {

    private final Entity receiver;
    private final Position newPosition;
    /**
     * Construct a {@link Collision}.
     * @param receiver the other entity.
     * @param newPosition new {@link Position} to avoid continuos collision
     */
    public CollisionImpl(final Entity receiver, final Position newPosition) {
        this.receiver = receiver;
        this.newPosition = newPosition;
    }
    /**
     * {@inheritDoc}.
     */
    @Override
    public Entity getReceiver() {
        return this.receiver;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.newPosition;
    }

}
