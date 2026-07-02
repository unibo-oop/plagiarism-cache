package jwhale.model;

import jwhale.commons.ItemType;
/**
 * Item factory for generation with default parameters or specifics one.
 */
public interface ItemFactory {
    /**
     * Specific item type.
     * @param itemName
     *          item name.
     * @param feature
     *          specific parameter.
     * @param type
     *          Item type according to ItemType enumeration.
     * @return
     *          item instance.
     */
    Item specificItemCreate(String itemName, String feature, ItemType type);
    /**
     * Default item type.
     * @param itemName
     *          item name.
     * @param type
     *          Item type according to ItemType enumeration.
     * @return
     *          item instance.
     */
    Item defaultItemCreate(String itemName, ItemType type);
}
