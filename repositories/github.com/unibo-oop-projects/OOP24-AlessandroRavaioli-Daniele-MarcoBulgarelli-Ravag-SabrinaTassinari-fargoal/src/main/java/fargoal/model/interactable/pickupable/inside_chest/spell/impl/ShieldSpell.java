package fargoal.model.interactable.pickupable.inside_chest.spell.impl;

import fargoal.model.interactable.pickupable.inside_chest.spell.api.AbstractSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements the Shield Spell. It extends the abstract class AbstractSpell.
 * When the player casts this spell he does not damage himself in the next fight.
 * If the player is not damaged and when he change floor level the spell is still 
 * casted, the spell ends.
 */
public class ShieldSpell extends AbstractSpell {

    /**
     * The constructor of the class. When The spell is found in a chest 
     * it is stored immediately in the player's inventory.
     */
    public ShieldSpell() {
        this.setNumberInInventory(0);
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return SpellType.SHIELD.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.SHIELD.getName()) 
                && this.getFloorLevelSpellCast() != floorManager.getFloorLevel()) {
            floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.SHIELD.getName(), false);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
        floorManager.getPlayer().getInventory().getSpellCasted().replace(SpellType.SHIELD.getName(), true);
        this.removeSpell();
    }

}
