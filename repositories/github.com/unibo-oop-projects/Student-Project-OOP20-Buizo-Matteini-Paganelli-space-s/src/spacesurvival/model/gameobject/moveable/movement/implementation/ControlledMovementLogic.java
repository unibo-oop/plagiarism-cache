package spacesurvival.model.gameobject.moveable.movement.implementation;

import java.awt.geom.AffineTransform;

import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.utilities.gameobject.VelocityUtils;

public class ControlledMovementLogic implements MovementLogic {

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final MoveableObject object) {
        if (object.isMoving()) {
            final V2d vel = object.getVelocity();
            final V2d newVel = vel.mul(object.getAcceleration());
            final AffineTransform at = object.getTransform();
            if (Math.abs(newVel.getY()) < VelocityUtils.SPACESHIP_MAX_VELOCITY) {
                object.setVelocity(newVel);
            }
            at.translate(object.getVelocity().getX(), object.getVelocity().getY());
            object.setTransform(object.getTransform());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ControlledMovement";
    }

}
