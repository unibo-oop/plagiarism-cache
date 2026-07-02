package model.components;

import org.jbox2d.common.Vec2;

import model.entities.EntityModel;

/**
 * Allow the entity to move when requested.
 */
public class ComandableMovement extends AbstractMovement {

    /**
     * @param owner the entity model
     * @param speed the characteristic speed of the entity
     */
    public ComandableMovement(final EntityModel owner, final float speed) {
        super(owner, speed);
    }

    /**
     * Makes the entity move.
     * @param movement the movement value
     */
    public final void move(final Vec2 movement) {
        final Vec2 moVec2 = movement.mul(this.getSpeed());
        this.getOwner().getPhysicEntity().applyImpulse(moVec2);
    }
}
