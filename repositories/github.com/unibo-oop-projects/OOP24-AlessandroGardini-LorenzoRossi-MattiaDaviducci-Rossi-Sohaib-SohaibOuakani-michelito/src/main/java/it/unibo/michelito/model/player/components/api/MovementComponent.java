package it.unibo.michelito.model.player.components.api;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

/**
 * Interface representing the movement component of a {@link Player}.
 * This component manages movement, speed, direction, and position.
 */
public interface MovementComponent {

    /**
     * Moves the {@link Player} based on the elapsed time and current speed/direction.
     *
     * @param time the elapsed time in milliseconds
     */
    void move(long time);

    /**
     * Sets the movement direction of the {@link Player}.
     *
     * @param direction the new {@link Direction} of movement
     */
    void setDirection(Direction direction);

    /**
     * Sets the speed of the {@link Player}.
     *
     * @param speed the new speed value
     */
    void setSpeed(double speed);

    /**
     * Sets the {@link Position} of the {@link Player}.
     *
     * @param position the new {@link Position} of the {@link Player}
     */
    void setPosition(Position position);

    /**
     * Retrieves the current {@link Position} of the {@link Player}.
     *
     * @return the current {@link Position}
     */
    Position getPosition();

    /**
     * Retrieves the current speed of the {@link Player}.
     *
     * @return the current speed
     */
    double getSpeed();
}
