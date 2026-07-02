package fargoal.model.interactable.pickupable.inside_chest.trap.api;

import java.util.Random;

import fargoal.model.events.impl.FoundTrapEvent;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItem;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItemType;
import fargoal.model.manager.api.FloorManager;

/**
 * This is the abstract class for the traps. When a trap is found in a chest
 * there is a small chance that the player loses the map of the floor level he is 
 * in that moment. 
 */
public abstract class AbstractTrap implements ChestItem {

    private final Random random;

    /**
     * This constructor initializes the random.
     */
    public AbstractTrap() {
        this.random = new Random();
    }

    /** {@inheritDoc} */
    @Override 
    public final String getChestItemType() {
        return ChestItemType.TRAP.getName();
    }

    /** {@inheritDoc} */
    @Override
    public final void use(final FloorManager floorManager) {
        boolean mapLost = false;
        final int chanceOfMapLost = this.random.nextInt(4);
        if (chanceOfMapLost == 0) {
            mapLost = true;
            floorManager.getFloorMask().resetMask();
        }
        this.effect(floorManager);
        floorManager.notifyFloorEvent(new FoundTrapEvent(this, mapLost));
    }

    /**
     * This method damage or teleport the player.
     * @param floorManager - the manager with all the information about the floor where this method was called
     */
    public abstract void effect(FloorManager floorManager);

}
