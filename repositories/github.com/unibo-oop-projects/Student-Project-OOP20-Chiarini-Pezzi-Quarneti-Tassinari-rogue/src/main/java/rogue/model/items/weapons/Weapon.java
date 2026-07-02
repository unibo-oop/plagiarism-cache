package rogue.model.items.weapons;

import rogue.model.items.Item;

/**
 * An interface for modeling a game weapon.
 */
public interface Weapon extends Item {

    /** 
     * Represents an enumeration for declaring weapon use.
     */
    enum Use {
        HANDLED, 
        THROWN;
    }

    /**
     * @param use 
     *          how the Weapon is used (by hand or thrown)
     * @return the weapon's damage
     */
    int getDamage(Use use);

    /**
     * 
     * @return the weapon's accuracy
     */
    int getAccuracy();

}
