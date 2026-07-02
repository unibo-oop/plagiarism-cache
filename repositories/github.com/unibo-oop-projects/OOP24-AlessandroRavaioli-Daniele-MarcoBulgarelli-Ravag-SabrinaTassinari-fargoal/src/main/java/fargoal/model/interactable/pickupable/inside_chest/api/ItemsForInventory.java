package fargoal.model.interactable.pickupable.inside_chest.api;

/**
 * This class models the interface for the items that can be store in the inventory,
 * for later use.
 */
public interface ItemsForInventory extends ChestItem {

    /**
     * This method put the spell the player found in a chest in his inventory.
     */
    void store();
}
