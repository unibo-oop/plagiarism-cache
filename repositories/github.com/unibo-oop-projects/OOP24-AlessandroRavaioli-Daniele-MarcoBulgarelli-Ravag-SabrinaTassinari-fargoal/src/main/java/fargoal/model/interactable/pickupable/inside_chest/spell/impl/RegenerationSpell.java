package fargoal.model.interactable.pickupable.inside_chest.spell.impl;

import fargoal.model.interactable.pickupable.inside_chest.spell.api.AbstractSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements the Regeneration Spell. It extends the abstract class AbstractSpell.
 * When the player cast this spell his hit points increases faster. 
 * This spell finishes when the player changes the floor.
 */
public class RegenerationSpell extends AbstractSpell {

    /**
     * The constructor of the class. When The spell is found in a chest 
     * it is stored immediately in the player's inventory.
     */
    public RegenerationSpell() {
        this.setNumberInInventory(0);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return SpellType.REGENERATION.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.REGENERATION.getName())
                && this.getFloorLevelSpellCast() != floorManager.getFloorLevel()) {
            floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.REGENERATION.getName(), false);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.REGENERATION.getName(), true);
        this.removeSpell();
    }

}
