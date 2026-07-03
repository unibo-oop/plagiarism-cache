package org.lkyhro.item;

/**
 * Created by Migani Luca on 08/01/2016.
 */
public class Equipment extends AbstractItem {
    /**
     * Constructor method for Equipment
     * @param name name of the equipment
     * @param description description of the equipment
     * @param value numeric value of the equipment once equipped
     * @param type type of equipment
     * @param rarity rarity of the equipment
     * @param id identify number of the equipment
     * @throws IllegalArgumentException if the type entered is not right for an Equipment
     */
    public Equipment (String name, String description, int value, ItemType type, int rarity, int id){
        super(name,description,value,type, rarity, id);
        if(type== ItemType.HEALING || type== ItemType.THROWABLE){
            throw new IllegalArgumentException();
        }
    }
}
