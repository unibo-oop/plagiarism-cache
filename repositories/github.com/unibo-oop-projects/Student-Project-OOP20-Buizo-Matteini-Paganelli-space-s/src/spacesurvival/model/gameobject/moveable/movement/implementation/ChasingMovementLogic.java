package spacesurvival.model.gameobject.moveable.movement.implementation;

import java.awt.geom.AffineTransform;

import spacesurvival.model.common.P2d;
import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;

public class ChasingMovementLogic implements MovementLogic {

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final MoveableObject object) {
        if (object.isMoving() && object.getTargetPosition() != null) {
            final double distanceFromTarget = object.getPosition().distanceFrom(object.getTargetPosition());
            if (distanceFromTarget >= Math.abs(object.getVelocity().module())) {
                final P2d target = object.getTargetPosition();
                final double rightRotation = Math.toDegrees(Math.atan2(object.getPosition().getY() - target.getY(), object.getPosition().getX() - target.getX()));
                final double complementary = 180 - (rightRotation * -1);
                final double newAngle = 90 + complementary;
                final AffineTransform newTransform = new AffineTransform();

                newTransform.translate(object.getTransform().getTranslateX(), object.getTransform().getTranslateY());
                newTransform.rotate(Math.toRadians(newAngle), 0, 0);
                newTransform.translate(object.getVelocity().getX(), object.getVelocity().getY());
                object.setTransform(newTransform);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ChasingMovement";
    }

}
