package model.components;

import java.util.function.BiConsumer;

import model.CollisionHandler.CollisionSide;
import model.entities.EntityModel;

/**
 * Defines the behavior to apply when a collision occurs.
 */
public class CollisionImpl implements Collision {

    private final BiConsumer<EntityModel, CollisionSide> consumer;

    /**
     * @param consumer the function to apply on the collided body
     */
    public CollisionImpl(final BiConsumer<EntityModel, CollisionSide> consumer) {
        this.consumer = consumer;
    }

    @Override
    public final void applyCollisionEffect(final EntityModel entity, final CollisionSide collisionSide) {
        consumer.accept(entity, collisionSide);
    }

}
