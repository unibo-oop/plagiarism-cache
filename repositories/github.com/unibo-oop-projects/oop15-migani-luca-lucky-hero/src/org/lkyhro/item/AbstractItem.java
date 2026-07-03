package org.lkyhro.item;

/**
 * Created by Migani Luca on 23/01/2016.
 */
public abstract class AbstractItem implements Item {

    private final String name;
    private final String description;
    private final int value;
    private final ItemType type;
    private final int rarity;
    private final int id;

    /**
     * Constructor method for AbstractItem
     * @param name name of the item
     * @param description description of the item
     * @param value numeric value of the item in battle
     * @param type type of item
     * @param rarity rarity of the item
     * @param id identify number for the item
     */
    public AbstractItem(String name, String description, int value, ItemType type, int rarity, int id){
        this.name=name;
        this.description=description;
        this.value=value;
        this.type=type;
        this.rarity=rarity;
        this.id=id;
    }

    /**
     *
     * @return String name of the item
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return String description of the item
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return int numeric value of the item in battle
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     *
     * @return ItemType type of item
     */
    @Override
    public ItemType getType() {
        return type;
    }

    /**
     *
     * @return Int rarity of the item
     */
    @Override
    public int getRarity() {
        return rarity;
    }

    /**
     *
     * @return int identify number of the item
     */
    public int getId(){
        return id;
    }

    /**
     *
     * @return String representation of AbstractItem
     */
    @Override
    public String toString() {
        return "org.lkyhro.item.AbstractItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", type=" + type +
                ", rarity=" + rarity +
                '}';
    }
}
