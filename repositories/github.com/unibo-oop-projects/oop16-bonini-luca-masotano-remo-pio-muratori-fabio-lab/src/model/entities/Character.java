package model.entities;

import java.util.Collection;

/**
 * 
 * Represent all the playable and enemy characters.
 *
 */
public interface Character extends Movable {

    /**
     * 
     * @return The health of this character.
     */
    double getLife();

    /**
     * Get the value of the fire rate of this character.
     * 
     * @return The fire rate.
     */
    double getFireRate();

    /**
     * Get the knockback delay of this character. This delay makes the
     * character invulnerable for a certain amount of time after that it has
     * been damaged.
     * 
     * @return The knockback delay.
     */
    double getKnockbackDelay();

    /**
     * The method called when this character has been damaged. It subtracts the
     * damage, given as parameter, to the life.
     * 
     * @param dmg
     *            The damage taken.
     */
    void takeDamage(double dmg);

    /**
     * Modify a property of this character based on the item provided.
     * 
     * @param item
     *            The item picked up.
     */
    void pickUpItem(Item item);

    /**
     * Create a bullet shoot by this character.
     * 
     * @param delta
     *            The delta of time.
     * @return A Collection of bullet shot
     */
    Collection<Bullet> shoot(double delta);

}