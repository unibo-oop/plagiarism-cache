package fargoal.model.interactable.pickupable.inside_chest.spell.api;

import fargoal.model.events.impl.PlayerActionEvent;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItemType;
import fargoal.model.manager.api.FloorManager;

/**
 * This abstract class implements the interface Spell.
 */
public abstract class AbstractSpell implements Spell {

    private int numberInInventory;
    private int floorLevelSpellCasted;

    /**
     * Constructor that sets the value of the field {@link #numberInInventory} to 0.
     */
    public AbstractSpell() {
        this.numberInInventory = 0;
    }

    /** {@inheritDoc} */
    @Override
    public final String getChestItemType() {
        return ChestItemType.SPELL.getName();
    }

    /** {@inheritDoc} */
    @Override
    public final void store() {
        this.addSpell();
    }

    /**
     * This method add a spell in the player's inventory.
     */
    private void addSpell() {
        this.numberInInventory++;
    }

    /** {@inheritDoc} */
    @Override
    public final void removeSpell() {
        if (this.numberInInventory > 0) {
            this.numberInInventory--;
        }
    }

    /** {@inheritDoc} */
    @Override
    public final Integer getNumberInInventory() {
        return this.numberInInventory;
    }

    /** {@inheritDoc} */
    @Override
    public final void setNumberInInventory(final int numberInInventory) {
        this.numberInInventory = numberInInventory;
    }

    /** {@inheritDoc} */
    @Override
    public final void use(final FloorManager floorManager) {
        floorManager.notifyFloorEvent(new PlayerActionEvent(this));
        this.effect(floorManager);
        this.setFloorLevelSpellCast(floorManager.getFloorLevel());
    }

    /**
     * This method make the spell happen. It does what the spell should do.
     * @param floorManager - it contains all the element of the floor.
     */
    public abstract void effect(FloorManager floorManager);

    /**
     * Setter for the field floorLevelSpellCast, which indicates in which floor the spell has been cast.
     * @param floorLevelSpellCasted - the level in which the spell has been cast.
     */
    private void setFloorLevelSpellCast(final int floorLevelSpellCasted) {
        this.floorLevelSpellCasted = floorLevelSpellCasted;
    }

    /**
     * Getter for the field floorLevelCast, which rapresent the floor level where a spell hs been cast.
     * @return the floor level where a spell has been cast.
     */
    public final int getFloorLevelSpellCast() {
        return this.floorLevelSpellCasted;
    }
}
