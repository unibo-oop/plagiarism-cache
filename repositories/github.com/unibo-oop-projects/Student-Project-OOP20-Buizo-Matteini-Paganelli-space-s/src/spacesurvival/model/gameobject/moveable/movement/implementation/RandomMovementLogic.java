package spacesurvival.model.gameobject.moveable.movement.implementation;

import java.awt.geom.AffineTransform;
import java.util.concurrent.ThreadLocalRandom;

import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.moveable.MoveableObject;
import spacesurvival.model.gameobject.moveable.movement.MovementLogic;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;

public class RandomMovementLogic implements MovementLogic {

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final MoveableObject moveableObject) {
        if (moveableObject.isMoving() && moveableObject.getTargetPosition() != null) {
            final P2d target = moveableObject.getTargetPosition();
            final double rightRotation = Math.toDegrees(Math.atan2(moveableObject.getPosition().getY() - target.getY(),
                    moveableObject.getPosition().getX() - target.getX()));
            final double complementary = 180 - (rightRotation * -1);
            final double newAngle = 90 + complementary;
            final AffineTransform newTransform = new AffineTransform();

            newTransform.translate(moveableObject.getTransform().getTranslateX(), moveableObject.getTransform().getTranslateY());
            newTransform.rotate(Math.toRadians(newAngle), 0, 0);
            newTransform.translate(moveableObject.getVelocity().getX(), moveableObject.getVelocity().getY());
            moveableObject.setTransform(newTransform);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMoving(final MoveableObject moveableObject) {
        moveableObject.setMoving(true);
        new Thread(() -> {
            while (moveableObject.isMoving()) {
                changeDirectionRandomly(moveableObject);
                ThreadUtils.sleep(Delay.CHANGING_DIRECTION);
            }
        }).start();
    }

    /**
     * Set a new random direction to the object velocity.
     * 
     * @param moveableObject object to change direction
     */
    public void changeDirectionRandomly(final MoveableObject moveableObject) {
        final double directionX = ThreadLocalRandom.current().nextInt(-1, 1);
        final double directionY = ThreadLocalRandom.current().nextInt(-1, 1);
        final V2d newVelocity = new V2d(directionX, directionY);
        moveableObject.setVelocity(newVelocity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DistantMovement";
    }
}
