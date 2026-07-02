package fargoal.model.interactable.pickupable.inside_chest.api;

import fargoal.model.interactable.api.Usable;

/**
 * The interface for the items in the chest, such as spells, traps and utilities. 
 * They can be use onlly once.
 */
public interface ChestItem extends Usable {

    /**
     * With this method, the type of the chest item is known.
     * @return the type of the chest item (if it is a trap, a spell or an utility).
     */
    String getChestItemType();

    /**
     * with this method, the name of the chest item is known.
     * @return the name of the chest item.
     */
    String getChestItemName();

}
