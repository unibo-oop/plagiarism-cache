 package main.java.com.model;

/**
 * A game entity that can be eaten by the snake (e.g. the apple)
 *
 */
public interface EatableEntity extends GameEntity {

    /**
     * 
     * @return the number of times this entity has been eaten;
     */
    int getTimesEaten();

    /**
     * Resets the times the apple has been eaten.
     */
    void resetPoints();

    /**
     * Increment the number of times this entity has been eaten.
     */
    void incrementEatenCounter();

    /**
     * 
     * @return how many points the apple values.
     */
    int getPointsValue();
}
