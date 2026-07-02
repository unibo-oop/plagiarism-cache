package fargoal.model.interactable.pickupable.inside_chest.utility.api;

import fargoal.model.interactable.pickupable.inside_chest.api.ItemsForInventory;

/**
 * This is the interface fot the items that the player can found in a chest. 
 * This type of item are utilities, that the player can put in his inventory.
 */
public interface Utility extends ItemsForInventory {

    /**
     * Method to check how many utility of
     * this type are present.
     * 
     * @return the number of utility the type is called on
     */
    int getNumberInInventory();

    /**
     * Remove a single utility of the type is called on
     * from the Inventory.
     */
    void removeUtility();

}
