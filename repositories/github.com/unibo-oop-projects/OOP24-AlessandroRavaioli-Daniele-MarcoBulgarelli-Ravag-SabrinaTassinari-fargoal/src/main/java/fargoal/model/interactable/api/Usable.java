package fargoal.model.interactable.api;

import fargoal.model.manager.api.FloorManager;

/**
 * This interface rapresents the objects that are not on the ground. The player use this items
 * (or get damage from them) one time and they are gone. Most of this items are in the inventory.
 */
public interface Usable {

    /**
     * This method let the item be used.
     * @param floorManager - it contains all the element of the floor.
     */
    void use(FloorManager floorManager);
}
