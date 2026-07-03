package model.entities;

/**
 * Represent the player and enemy characters.
 * 
 *
 */

public interface Character extends Living {

    /**
     * 
     * @return the damage issued from the collision with other entity.
     */
    double getCollisionDamage();

    /**
     * 
     * @return the sum of the elapsed.
     */
    int getCounterElapsed();

    /**
     * Set the variable 'counterElapsed'.
     * @param counterElapsed 
     *                  to set
     */
    void setCounterElapsed(int counterElapsed);
}
