package fargoal.model.interactable.pickupable.inside_chest.trap.impl;

import java.util.Random;

import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.interactable.pickupable.inside_chest.trap.api.AbstractTrap;
import fargoal.model.interactable.pickupable.inside_chest.trap.api.TrapType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a Pit, a trap which can be found in a chest.
 * It damages the player when he find it.
 */
public class Pit extends AbstractTrap {

    private static final int MAX_DAMAGE = 9;
    private final Random random;

    /**
     * This is the constructor of the class. When the player finds the trap in a chest it damages him immediately. 
     * @param floorManager - it contains all the element of the floor the trap was found.
     */
    public Pit(final FloorManager floorManager) {
        this.random = new Random();
        this.use(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return TrapType.PIT.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        final int damage = this.random.nextInt(MAX_DAMAGE) + floorManager.getFloorLevel();
        if (!floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.DRIFT.getName())) {
            floorManager.getPlayer().decreaseHealth(damage);
        } else {
            floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.DRIFT.getName(), false);
        }
    }

}
