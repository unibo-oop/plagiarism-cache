package fargoal.model.interactable.pickupable.inside_chest.trap.impl;

import java.util.Random;

import fargoal.model.interactable.pickupable.inside_chest.trap.api.AbstractTrap;
import fargoal.model.interactable.pickupable.inside_chest.trap.api.TrapType;
import fargoal.model.manager.api.FloorManager;

/**
 * This is the implementation of the Ceiling Trap, which can be found in a chest.
 * It damages the player when he find it.
 */
public class CeilingTrap extends AbstractTrap {

    private static final int MAX_DAMAGE = 9;
    private final Random random;

    /**
     * This is the constructor of the class. When the player finds the trap in the chest it damages him immediately.
     * @param floorManager - it contains all the element of the floor the trap was found.
     */
    public CeilingTrap(final FloorManager floorManager) {
        this.random = new Random();
        this.use(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return TrapType.CEILING_TRAP.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        final int damage = this.random.nextInt(MAX_DAMAGE) + floorManager.getFloorLevel();
        floorManager.getPlayer().decreaseHealth(damage);
    } 

}
