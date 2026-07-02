package fargoal.model.interactable.pickupable.inside_chest.utility.impl;

import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.AbstractUtility;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.UtilityType;
import fargoal.model.manager.api.FloorManager;

/**
 * This is the class that implements an Enchanted Weapon, which the player found in a chest.
 * It increase the combat skill of the player.
 */
public class EnchantedWeapon extends AbstractUtility {

    private static final int MAX_SKILL_TO_ADD = 9;
    private static final int SKILL_TO_ADD = 5;
    private final Random random;
    private final FloorManager floorManager;

    /**
     * This is the constructor of the class. It store right away the item the player found in a chest.
     * @param floorManager - it contains all the element of the floor the item is.
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class needs to work on the same floormanager as the one given"
        + " so if the one given changes, the reference also needs to change"
    )
    public EnchantedWeapon(final FloorManager floorManager) {
        this.random = new Random();
        this.setNumberInInventory(0);
        this.floorManager = floorManager;
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return UtilityType.ENCHANTED_WEAPON.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
    }

    /** {@inheritDoc} */
    @Override
    public void addToPlayer() {
        final int skillToAdd = this.random.nextInt(MAX_SKILL_TO_ADD) + SKILL_TO_ADD;
        this.floorManager.getPlayer().increasePlayerSkill(skillToAdd);
    }

}
