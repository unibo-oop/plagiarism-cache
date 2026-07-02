package main.java.com.model;

import main.java.com.utility.Position;

/**
 * This class represents the apple game entity, it is characterized by a
 * position on the game map, a points value and the ability to be eaten.
 * Implements {@link EatableEntity} interface.
 */
public class Apple implements EatableEntity {

    private static final int INITIAL_POINTS = 50;
    private static final int POINTS_CHANGE = 50;
    private static final int APPLES_MULT = 20;

    private Position position;
    private int eatenCounter;
    private int pointsValue;

    /**
     * Create the apple in the given position.
     * @param pos the position
     */
    public Apple(final Position pos) {
        position = pos;
        eatenCounter = 0;
        pointsValue = INITIAL_POINTS;
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(final Position pos) {
        this.position = pos;
    }

    /** {@inheritDoc} */
    @Override
    public int getTimesEaten() {
        return this.eatenCounter;
    }

    /** {@inheritDoc} */
    public void resetPoints() {
        this.eatenCounter = 0;
        this.pointsValue = INITIAL_POINTS;
    }

    /** {@inheritDoc} */
    @Override
    public void incrementEatenCounter() {
        this.eatenCounter++;
        if (this.eatenCounter % APPLES_MULT == 0) {
            this.pointsValue += POINTS_CHANGE; // The points value for each apple eaten increments by 50 every twenty
                                               // apples eaten.
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getPointsValue() {
        return this.pointsValue;
    }

}
