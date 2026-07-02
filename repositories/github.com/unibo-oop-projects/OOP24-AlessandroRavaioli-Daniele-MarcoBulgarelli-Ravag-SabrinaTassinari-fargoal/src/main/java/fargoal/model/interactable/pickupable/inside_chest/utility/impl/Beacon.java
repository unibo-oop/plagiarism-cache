package fargoal.model.interactable.pickupable.inside_chest.utility.impl;

import fargoal.model.events.impl.PlacedABeacon;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.AbstractUtility;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.UtilityType;
import fargoal.model.interactable.pickupable.on_ground.BeaconOnGround;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a Beacon, when it is in the player's inventory. 
 * The player found the beacon in a chest.
 */
public class Beacon extends AbstractUtility {

    /**
     * This is the constructor of the class. It store right away the beacon in the player's inventory.
     */
    public Beacon() {
        this.setNumberInInventory(0);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return UtilityType.BEACON.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        this.removeUtility();
        final BeaconOnGround beaconOnGround = new BeaconOnGround(floorManager.getPlayer().getPosition(), floorManager);
        floorManager.addInteractable(beaconOnGround);
        floorManager.notifyFloorEvent(new PlacedABeacon(beaconOnGround));
    }

    /** {@inheritDoc} */
    @Override
    public void addToPlayer() {
    }

}
