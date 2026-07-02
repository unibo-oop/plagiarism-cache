package jwhale.model;

import jwhale.commons.ItemType;
/**
 * Item factory implementation.
 *
 */
public class ItemFactoryImpl implements ItemFactory {

    @Override
    public final Item specificItemCreate(final String itemName, final String feature, final ItemType type) {
        return type.toString().equals("Network") ? new Network(itemName, feature)
                : (type.toString().equals("Image") ? new Image(itemName, feature) 
                        : new Volume(itemName, feature));
    }

    @Override
    public final Item defaultItemCreate(final String itemName, final ItemType type)  {
        return type.toString().equals("Network") ? new Network(itemName) : new Image(itemName);
    }


}
