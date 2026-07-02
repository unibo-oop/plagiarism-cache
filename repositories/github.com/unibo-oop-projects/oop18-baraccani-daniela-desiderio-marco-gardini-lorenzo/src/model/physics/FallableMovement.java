package model.physics;

import model.entitiesutil.Body;
import model.entitiesutil.MovementValue;

/**
 * class that manages fall and "unfall" movements, common to the characters and the power-ups.
 */
public abstract class FallableMovement extends AbstractMovement {
    /**
     * this variable indicates the acceleration multiplier of the y component of the object's position.
     */
    private int yVelocity;

    /**
     * Class constructor.
     * @param body {@link Body}
     */
    public FallableMovement(final Body body) {
        super(body);
        this.yVelocity = 0;
    }

    /**
     * method getyVelocity returns yVelocity.
     * @return yVelocity
     */
    protected int getyVelocity() {
        return yVelocity;
    }

    /**
     * method setyVelocity sets yVelocity.
     * @param yVelocity 
     */
    protected void setyVelocity(final int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public final void fall() {
        this.yVelocity++;
        this.getBody().getPosition().setY(this.getBody().getPosition().getY() + MovementValue.CHARACTER_FALL.getVelocityY() * this.yVelocity);
    }

    @Override
    public final void fallUndo(final int objectY) {
        this.getBody().getPosition().setY(objectY - this.getBody().getHeight() - 1);
        this.yVelocity = 0;
    }
}
