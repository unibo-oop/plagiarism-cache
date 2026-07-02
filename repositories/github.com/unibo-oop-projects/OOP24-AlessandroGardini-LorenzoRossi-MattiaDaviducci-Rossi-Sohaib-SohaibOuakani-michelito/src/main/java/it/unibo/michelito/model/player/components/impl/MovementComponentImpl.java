package it.unibo.michelito.model.player.components.impl;

import it.unibo.michelito.model.player.components.api.MovementComponent;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.player.api.Player;

import java.math.BigDecimal;

/**
 * Implementation of the {@link MovementComponent} interface that manages
 * entity movement, speed, and {@link Direction}.
 */
public class MovementComponentImpl implements MovementComponent {
    private static final double STANDARD_SPEED = 0.01;
    private Direction direction;
    private Position position;
    private double speed;

    /**
     * Constructs a new {@link MovementComponentImpl} with the given initial {@link Position}.
     *
     * @param position the initial {@link Position} of the {@link Player}
     */
    public MovementComponentImpl(final Position position) {
        this.position = position;
        this.speed = STANDARD_SPEED;
        this.direction = Direction.NONE;
    }

    /**
     * Moves the {@link Player} based on the elapsed time and current speed and {@link Direction}.
     * After moving, the direction is reset to {@link Direction} NONE.
     *
     * @param time the elapsed time in milliseconds
     */
    @Override
    public void move(final long time) {
        this.setPosition(calculateNextPosition(time));
        this.setDirection(Direction.NONE);
    }

    /**
     * Calculates the next {@link Position} based on the movement speed, {@link Direction}, and elapsed time.
     *
     * @param time the elapsed time in milliseconds
     * @return the new {@link Position} after movement
     */
    private Position calculateNextPosition(final long time) {
        final BigDecimal move = BigDecimal.valueOf(this.speed).multiply(BigDecimal.valueOf(time));
        final BigDecimal xDisplacement = move.multiply(BigDecimal.valueOf(this.direction.toPosition().x()));
        final BigDecimal yDisplacement = move.multiply(BigDecimal.valueOf(this.direction.toPosition().y()));

        final BigDecimal newX = BigDecimal.valueOf(this.position.x()).add(xDisplacement);
        final BigDecimal newY = BigDecimal.valueOf(this.position.y()).add(yDisplacement);

        return new Position(newX.doubleValue(), newY.doubleValue());
    }

    /**
     * Sets the movement direction of the {@link Player}.
     *
     * @param direction the new {@link Direction} of movement
     */
    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Sets the movement speed of the {@link Player}.
     *
     * @param speed the new speed value
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * Updates the position of the {@link Player}.
     *
     * @param position the new position
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * Retrieves the current position of the {@link Player}.
     *
     * @return the current {@link Position}
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * Retrieves the current speed of the {@link Player}.
     *
     * @return the current {@link Position}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }
}
