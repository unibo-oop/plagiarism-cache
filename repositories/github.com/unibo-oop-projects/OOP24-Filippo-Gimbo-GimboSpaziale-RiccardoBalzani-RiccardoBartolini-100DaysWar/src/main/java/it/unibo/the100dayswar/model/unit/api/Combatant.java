package it.unibo.the100dayswar.model.unit.api;

import java.io.Serializable;

/** 
 * An interface for the objects that can fight with other ones.
 */
public interface Combatant extends Serializable {
    /**
     * Getter for the current health of the object.
     * 
     * @return the health of the object.
     */
    int currentHealth();
    /**
     * Change the health of the object.
     * 
     * @param health the new health of the object
      */
    void setHealth(int health);
    /** 
     * Make the object take damage.
     * 
     * @param damage the amount of damage to take
     */
    void takeDamage(int damage);
    /** 
     * Perform the attack of the object.
     * 
     * @param target the target of the attack
     * 
     * @throws IllegalArgumentException if the attack is not allowed
     */
    void performAttack(Combatant target);
}
