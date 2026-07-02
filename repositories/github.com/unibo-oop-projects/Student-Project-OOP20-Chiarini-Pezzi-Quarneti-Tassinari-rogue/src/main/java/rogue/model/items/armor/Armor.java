package rogue.model.items.armor;

import rogue.model.items.Item;

/**
 * An interface modeling a game armor.
 */
public interface Armor extends Item {

    /**
     * @return the armor's AC
     */
    int getAC();

    /**
     * Increases the armor's AC of the given value.
     * @param amount
     *          the value to add to the armor's AC
     */
    void increaseAC(int amount);

    /**
     * Decreases the armor's AC of the given value.
     * @param amount
     *          the value to add to the armor's AC
     */
    void decreaseAC(int amount);

    /**
     * @return the armor type
     */
    ArmorType getArmorType();

}
