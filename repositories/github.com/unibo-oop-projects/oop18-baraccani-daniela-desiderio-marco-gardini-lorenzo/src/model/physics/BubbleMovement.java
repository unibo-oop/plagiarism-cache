package model.physics;

import model.entitiesutil.Body;
import model.entitiesutil.MovementValue;

/**
 * 
 * class that manages Bubble movements.
 */
public class BubbleMovement extends AbstractMovement {

    private static final double MULTIPLIER = 0.5;
    private static final int CYCLES_SKIPPED = 2;
    private boolean isGoingUp;
    private int cycleCounter;

    /**
     * bubble movement constructor.
     * 
     * @param body BubbleMovement is associated to a {@link Body} of which it
     *             changes the position.
     */
    public BubbleMovement(final Body body) {
        super(body);
        this.isGoingUp = true;
        this.initializeCycleCounter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void bubbleMoveLeft() {
        this.getBody().getPosition()
                .setX(this.getBody().getPosition().getX() + MovementValue.BUBBLE_MOVE_LEFT.getVelocityX());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void bubbleMoveRight() {
        this.getBody().getPosition()
                .setX(this.getBody().getPosition().getX() + MovementValue.BUBBLE_MOVE_RIGHT.getVelocityX());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void bubbleMoveUp() {
        this.getBody().getPosition()
                .setY(this.getBody().getPosition().getY() + MovementValue.BUBBLE_MOVE_UP.getVelocityY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void floatUpDown() {
        if (this.cycleCounter % CYCLES_SKIPPED == 0) {
            if (this.isGoingUp) {
                this.getBody().getPosition()
                        .setY(this.getBody().getPosition().getY() + MovementValue.BUBBLE_FLOAT_UP.getVelocityY());
            } else {
                this.getBody().getPosition()
                        .setY(this.getBody().getPosition().getY() + MovementValue.BUBBLE_FLOAT_DOWN.getVelocityY());
            }
        }
        this.cycleCounter--;
        if (this.cycleCounter == 0) {
            this.initializeCycleCounter();
            this.isGoingUp = !this.isGoingUp;
        }
    }

    private void initializeCycleCounter() {
        this.cycleCounter = new Double(this.getBody().getHeight() * MULTIPLIER * CYCLES_SKIPPED).intValue();
    }
}
