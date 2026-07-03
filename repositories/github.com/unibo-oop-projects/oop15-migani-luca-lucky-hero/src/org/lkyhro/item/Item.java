package org.lkyhro.item;

/**
 * Created by Migani Luca on 08/01/2016.
 */
public interface Item {
    /**
     *
     * @return String name of the item
     */
    String getName();

    /**
     *
     * @return String description of the item
     */
    String getDescription();

    /**
     *
     * @return int numeric value of the item in battle
     */
    int getValue();

    /**
     *
     * @return ItemType type of item
     */
    ItemType getType();

    /**
     *
     * @return int rarity of the item
     */
    int getRarity();

    /**
     *
     * @return int identify number of the item
     */
    int getId();
}
