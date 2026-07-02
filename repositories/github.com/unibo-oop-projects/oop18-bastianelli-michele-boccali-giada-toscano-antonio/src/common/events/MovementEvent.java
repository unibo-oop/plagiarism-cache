package common.events;

import org.jbox2d.common.Vec2;

/**
 * Represent a MovementEvent.
 */
public class MovementEvent implements Event {

    private final Vec2 movementValue;

    /**
     * Constructor of MovementEvent.
     * 
     * @param movementValue : the movement value
     */
    public MovementEvent(final Vec2 movementValue) {
        this.movementValue = movementValue;
    }

    /**
     * Get the movement value requested.
     * 
     * @return the movement value represented as a Point2D
     */
    public final Vec2 getMovementValue() {
        return movementValue;
    }
}
