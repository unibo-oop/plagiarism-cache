package model.entities;

/**
 * Represent the health of the entity.
 *
 */

public interface Living extends Entity {

    /**
     * Get the health of this entity.
     * 
     * @return the health
     */
    double getLife();

    /**
     * Get the max health of this entity.
     * 
     * @return the max health
     */
    double getMaxLife();

    /**
     * The method called when this entity has been damaged.
     * 
     * @param decrease
     *            The quantity of life to decrease
     */
    void decreaseLife(double decrease);

    /**
     * The method called when this entity regenerate the health.
     * 
     * @param increment
     *            The quantity of life to increment
     */
    void incrementLife(double increment);

}
