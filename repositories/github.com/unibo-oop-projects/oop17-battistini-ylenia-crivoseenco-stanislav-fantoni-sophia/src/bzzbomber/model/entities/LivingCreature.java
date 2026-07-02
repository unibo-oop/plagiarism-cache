package bzzbomber.model.entities;

import java.awt.Point;

import bzzbomber.model.utilities.Direction;

/**
 * Model a living creature. Interface that extend an @Entity .
 */
public interface LivingCreature extends Entity {

    /**
     * Allows the entity to move.
     * 
     * @param dir
     *            The direction where to move
     */
    void move(Direction dir);

    /**
     * Modifies the current value of lives.
     * 
     */
    void addLife();

    /**
     * Remove lives from LivingCreature.
     * 
     */
    void removeLife();

    /**
     * Get the new position where the hero would be.
     *
     * @param pos
     *            The point whose coordinates are to be added
     * @return The position where the hero would move
     */
    Point getNextPosition(Point pos);

    /**
     * Get remaining lives.
     * 
     * @return The remaining lives
     */
    int getRemainingLives();

    /**
     * Checks if the entity is alive.
     * 
     * @return True if the entity is alive.
     */
    boolean isAlive();

    /**
     * Setter for the life of creature.
     * 
     * @param life
     *            The life to set.
     */
    void setLife(int life);

}
