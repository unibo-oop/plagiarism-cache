package user.game.ships;

/**
 * This class represents a generic IA ship.
 */
public abstract class ObjIAShip extends ObjShip {

    private static final double MAX_DISTANCE_FROM_TARGET = 400;

    /**
     * @return the maxDistance
     */
    public double getMaxDistanceFromTarget() {
        return MAX_DISTANCE_FROM_TARGET;
    }

    @Override
    protected boolean canAccelerate() {
        if (z().instanceExists(this.getTarget())) {
            return z().pointDistance(this.getX(), this.getY(), this.getTarget().getX(), this.getTarget().getY()) > this
                    .getMaxDistanceFromTarget();
        } else {
            return false;
        }
    }

    @Override
    protected void move() {
        if (z().instanceExists(this.getTarget())) {
            final double angle = z().pointDirection(this.getX(), this.getY(), getTarget().getX(), getTarget().getY());
            this.setImageAngle(z().angleRotate(this.getImageAngle(), angle, this.getRotationSpeed()));
        }

        this.calculateSpeed();

        moveX(z().lengthDirX(getCurrentSpeed(), getImageAngle()));
        moveY(z().lengthDirY(getCurrentSpeed(), getImageAngle()));

    }

}