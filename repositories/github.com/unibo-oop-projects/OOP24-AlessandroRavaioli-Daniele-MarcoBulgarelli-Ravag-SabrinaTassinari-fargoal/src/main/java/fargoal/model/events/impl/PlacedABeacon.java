package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;

/**
 * PlacedABeacon is a class that is called everytime that
 * the player puts a beacon to the ground to sets the place
 * of the next teleport.
 */
public class PlacedABeacon implements FloorEvent {
    private final FloorElement beaconOnGround;

    /**
     * Constructor that assigns to the local field beaconOnGround the
     * corresponding FloorElement that has been placed.
     * 
     * @param beaconOnGround - the beacon that has been placed
     */
    public PlacedABeacon(final FloorElement beaconOnGround) {
        this.beaconOnGround = beaconOnGround;
    }

    /**
     * Method that returns the FloorElement Beacon that
     * has been placed on the ground by the player.
     * 
     * @return -  the FloorElement Beacon that has been placed
     */
    public FloorElement getWhatPlaced() {
        return this.beaconOnGround;
    }
}
