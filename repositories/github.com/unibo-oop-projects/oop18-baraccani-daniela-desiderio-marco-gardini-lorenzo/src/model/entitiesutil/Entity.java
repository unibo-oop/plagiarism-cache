package model.entitiesutil;

import model.Model;
import model.physics.CollisionDirection;
import util.Pair;

/**
 * Interface that represents any object inside the game.
 */
public interface Entity {

    /**
     * Set the visibility of the {@link Entity} to false.
     */
    void hide();

    /**
     * @return true if the {@link Entity} is visible, false otherwise
     */
    boolean isVisible();

    /**
     * @return a string containing the class name of the {@link Entity}
     */
    String getType();

    /**
     * @return the current width of the {@link Entity}
     */
    int getWidth();

    /**
     * @return the current height of the {@link Entity}
     */
    int getHeight();

    /**
     * @return the current x position (from left) of the {@link Entity}
     */
    int getX();

    /**
     * @return the current y position (from top) of the {@link Entity}
     */
    int getY();

    /**
     * @return the current Integer {@link Pair} position (from top left) of the
     *         {@link Entity}
     */
    Pair<Integer, Integer> getPosition();

    /**
     * @return the world {@link Model} the {@link Entity} is playing inside
     */
    Model getModel();

    /**
     * Executes the actions caused by the collision with one other {@link Entity}.
     * 
     * @param entity             the other {@link Entity} this {@link Entity} is
     *                           touched by
     * @param collisionDirection is the side of the collision between
     *                           {@link Entity}s.
     */
    void isTouchedBy(Entity entity, CollisionDirection collisionDirection);
}
