package fargoal.model.interactable.pickupable.inside_chest.trap.impl;

import fargoal.commons.api.Position;
import fargoal.model.interactable.pickupable.inside_chest.trap.api.AbstractTrap;
import fargoal.model.interactable.pickupable.inside_chest.trap.api.TrapType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a Teleport, a trap that can be found in a chest.
 * It teleport the player in a random position in the floor.
 */
public class Teleport extends AbstractTrap {

    /**
     * This is the constructor of the class. When the player finds the trap in a chest it teleport him immediately. 
     * @param floorManager - it contains all the element of the floor the trap was found.
     */
    public Teleport(final FloorManager floorManager) {
        this.use(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return TrapType.TELEPORT.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        final Position newPositionPlayer = floorManager.getFloorMap().getRandomTile();
        floorManager.getPlayer().move(newPositionPlayer);
    }

}
