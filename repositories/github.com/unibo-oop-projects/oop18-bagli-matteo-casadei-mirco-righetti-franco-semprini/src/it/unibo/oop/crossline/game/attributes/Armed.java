package it.unibo.oop.crossline.game.attributes;

import it.unibo.oop.crossline.game.weapon.Weapon;

/**
 * This interface represents an entity that has a weapon.
 */
public interface Armed {

    /**
     * Get the equipped weapon.
     * @return the equipped weapon.
     */
    Weapon getWeapon();

}
