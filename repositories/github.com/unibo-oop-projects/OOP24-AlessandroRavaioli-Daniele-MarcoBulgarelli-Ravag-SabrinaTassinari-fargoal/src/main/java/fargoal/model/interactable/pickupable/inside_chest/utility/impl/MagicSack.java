package fargoal.model.interactable.pickupable.inside_chest.utility.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.AbstractUtility;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.UtilityType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a Magic Sack, which the player found in a chest.
 */
public class MagicSack extends AbstractUtility {

    private static final int GOLD_CARRIED_BY_MAGIC_SACK = 100;
    private static final int N_MAGICK_SACK_TO_START = 1;
    private final FloorManager floorManager;

    /**
     * This is the constructor of the class. It stores right away the item in the player's inventory.
     * @param floorManager - it contains all the element of the floor in which the item was found.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class needs to work on the same manager as the one given"
            + "so if the one given changes the reference also needs to change"
    )
    public MagicSack(final FloorManager floorManager) {
        this.setNumberInInventory(N_MAGICK_SACK_TO_START);
        this.floorManager = floorManager;
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return UtilityType.MAGIC_SACK.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
    }

    /** {@inheritDoc} */
    @Override
    public void addToPlayer() {
        this.floorManager.getPlayer().getPlayerGold().setMaxCapacity(this.floorManager.getPlayer().getMaxGoldCapacity() 
            + GOLD_CARRIED_BY_MAGIC_SACK);
    }

}
