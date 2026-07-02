package rogue.model.items.potion;

import rogue.model.items.Item;

/**
 * An interface modeling a game's Potion.
 *
 */
public interface Potion extends Item {

    /**
     * Enumeration that represents the effect of a potion.
     *
     */
    enum PotionEffect {
        HEAL, HURT;
    }

    /**
     * Get the potion's Health points.
     * @return the potion's health points.
     */
    int getHpValue();

}
