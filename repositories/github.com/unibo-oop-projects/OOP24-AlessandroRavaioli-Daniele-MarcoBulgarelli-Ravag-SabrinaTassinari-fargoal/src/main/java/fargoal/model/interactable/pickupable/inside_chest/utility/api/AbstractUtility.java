package fargoal.model.interactable.pickupable.inside_chest.utility.api;

import fargoal.model.interactable.pickupable.inside_chest.api.ChestItemType;
import fargoal.model.manager.api.FloorManager;

/**
 * This abstract class helps to implement an utility.
 */
public abstract class AbstractUtility implements Utility {

    private int numberInInventory;

    /**
     * Constructor that sets the value of the local field {@link #numberInInventory} to 0.
     */
    public AbstractUtility() {
        this.numberInInventory = 0;
    }

    /** {@inheritDoc} */
    @Override
    public final String getChestItemType() {
        return ChestItemType.UTILITY.getName();
    }

    /** {@inheritDoc} */
    @Override
    public final void use(final FloorManager floorManager) {
        this.effect(floorManager);
    }

    /**
     * This method is the effect the utility has when it is used.
     * @param floorManager - it contains all the element of the floor.
     */
    public abstract void effect(FloorManager floorManager);

    /** {@inheritDoc} */
    @Override
    public void store() {
        this.addUtility();
        this.addToPlayer();
    }

    /**
     * This method add a determined statistics to the player, when the utility 
     * is stored.
     */
    public abstract void addToPlayer();

    /**
     * This method add an utility in the player's inventory.
     */
    private void addUtility() {
        this.numberInInventory++;
    }

    /** {@inheritDoc} */
    @Override
    public void removeUtility() {
        this.numberInInventory--;
    }

    /** {@inheritDoc} */
    @Override
    public int getNumberInInventory() {
        return this.numberInInventory;
    }

    /**
     * Setter for the field numberInInventory.
     * @param numberInInventory - number of this item in inventory.
     */
    public final void setNumberInInventory(final int numberInInventory) {
        this.numberInInventory = numberInInventory;
    }
}
