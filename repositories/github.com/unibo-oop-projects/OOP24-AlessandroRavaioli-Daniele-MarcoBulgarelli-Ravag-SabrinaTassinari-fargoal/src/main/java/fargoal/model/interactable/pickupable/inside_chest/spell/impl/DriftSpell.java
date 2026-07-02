package fargoal.model.interactable.pickupable.inside_chest.spell.impl;

import fargoal.model.interactable.pickupable.inside_chest.spell.api.AbstractSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements the Drift Spell. It is extended from the abstract class AbstractSpell.
 * When the player cast this spell if he falls in a pit he does not damage himself.
 */
public class DriftSpell extends AbstractSpell {

    /**
     * The constructor of the class. When The spell is found in a chest 
     * it is stored immediately in the player's inventory.
     * If the spell was cast and player did not fall in a pit when he change the floor
     * the spell ends.
     */
    public DriftSpell() {
        this.setNumberInInventory(0);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return SpellType.DRIFT.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.DRIFT.getName()) 
                && this.getFloorLevelSpellCast() != floorManager.getFloorLevel()) {
            floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.DRIFT.getName(), false);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.DRIFT.getName(), true);
        this.removeSpell();
    }

}
