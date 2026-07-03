package model.entities;

import model.hitbox.HitboxRectangle;

/**
 * 
 * Represents all the items of the game.
 *
 */
public interface Item extends Entity<HitboxRectangle> {

    /**
     * Get the value stored in this Item.
     * 
     * @return The value.
     */
    double getValue();

    /**
     * Get the value of ItemType representing this item.
     * 
     * @return A value of ItemType representing this item.
     */
    ItemType getItemType();

}