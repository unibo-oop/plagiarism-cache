package spacesurvival.model.gameobject.moveable.movement.implementation;

import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;

import java.awt.geom.AffineTransform;

public class FixedMovementLogic implements MovementLogic {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void move(final MoveableObject object) {
        if (object.isMoving()) {
            final AffineTransform at = object.getTransform();
            object.setTransform(object.getTransform());
            at.translate(object.getVelocity().getX(), object.getVelocity().getY());
        }
    }

    @Override
    public final String toString() {
        return "FixedMovement";
    }

}
