package org.lkyhro.item;

/**
 * Created by Migani Luca on 11/01/2016.
 */
public class ConsumableItem extends AbstractItem {
    /**
     * Constructor method for ConsumableItem
     * @param name name of the item
     * @param description description of the item
     * @param value numeric value of the item in battle
     * @param type type of item
     * @param rarity rarity of the item
     * @param id identify number of the item
     * @throws IllegalArgumentException if the type entered is not of a ConsumableItem
     */
    public ConsumableItem(String name, String description, int value, ItemType type, int rarity, int id) {
        super(name, description, value, type, rarity, id);
        if(type!= ItemType.HEALING && type!= ItemType.THROWABLE){
            throw new IllegalArgumentException();
        }
    }
}
