package model.physics;

import model.entitiesutil.Body;
import model.entitiesutil.MovementValue;

/**
 * Class for management of character movements, that is the movements both the
 * hero and the enemies have at their disposal.
 */
public class CharacterMovement extends FallableMovement {

    private static final int JUMP_CYCLES = 13;
    private static final double MULTIPLIER = 0.5;
    private static final int FLOATING_CYCLES_SKIPPED = 1;
    private boolean isGoingUp;
    private int floatingCycleCounter;

    /**
     * character movement constructor.
     * 
     * @param body CharacterMovement is associated to a {@link Body} of which it
     *             changes the position.
     */
    public CharacterMovement(final Body body) {
        super(body);
        this.isGoingUp = true;
        this.floatingCycleCounter = new Double(this.getBody().getHeight() * MULTIPLIER * FLOATING_CYCLES_SKIPPED).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean characterJump() {
        if (this.getyVelocity() <= JUMP_CYCLES) {
            this.getBody().getPosition().setY(this.getBody().getPosition().getY()
                    - Math.abs(MovementValue.CHARACTER_JUMP.getVelocityY()) * (JUMP_CYCLES - this.getyVelocity()));
            this.setyVelocity(this.getyVelocity() + 1);
            return true;
        }
        this.setyVelocity(0);
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void characterMoveUp() {
        this.getBody().getPosition().setY(this.getBody().getPosition().getY() + MovementValue.CHARACTER_MOVE_UP.getVelocityY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void characterMoveLeft() {
        this.getBody().getPosition()
                .setX(this.getBody().getPosition().getX() + MovementValue.CHARACTER_MOVE_LEFT.getVelocityX());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void characterMoveRight() {
        this.getBody().getPosition()
                .setX(this.getBody().getPosition().getX() + MovementValue.CHARACTER_MOVE_RIGHT.getVelocityX());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void floatUpDown() {
        if (this.floatingCycleCounter % FLOATING_CYCLES_SKIPPED == 0) {
            if (this.isGoingUp) {
                this.getBody().getPosition().setY(this.getBody().getPosition().getY() + MovementValue.BUBBLE_FLOAT_UP.getVelocityY());
            } else {
                this.getBody().getPosition().setY(this.getBody().getPosition().getY() + MovementValue.BUBBLE_FLOAT_DOWN.getVelocityY());
            }
        }
        this.floatingCycleCounter--;
        if (this.floatingCycleCounter == 0) {
            this.floatingCycleCounter = new Double(this.getBody().getHeight() * MULTIPLIER * FLOATING_CYCLES_SKIPPED).intValue();
            this.isGoingUp = !this.isGoingUp;
        }
    }

}
