package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Startable;

/**
 * Represents an Eagle in the game.
 * The Eagle is a special moving object that only moves when started.
 * It is typically triggered based on specific gameplay conditions, such as the player's position.
 */
public class Eagle extends MovingObjectImpl implements Startable<Integer> {
    /** The row at which the Eagle should be triggered to start moving.*/
    private boolean started; 
    private int triggerRow;

    /**
     * Constructs an Eagle object.
     * The image is selected based on its initial direction (up or down).
     *
     * @param pos       the initial position of the Eagle
     * @param dimension the size (width and height) of the Eagle
     * @param speed     the speed at which the Eagle moves
     * @param direction the initial movement direction of the Eagle
     */
    public Eagle(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(super.getDirection().equals(Direction.UP) ? "eagleUp.png" : "eagleDown.png");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        started = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        started = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void step() {
        if (started) {
            super.step();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTrigger() {
        return this.triggerRow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrigger(final Integer trigger) {
        this.triggerRow = trigger;
    }
}
