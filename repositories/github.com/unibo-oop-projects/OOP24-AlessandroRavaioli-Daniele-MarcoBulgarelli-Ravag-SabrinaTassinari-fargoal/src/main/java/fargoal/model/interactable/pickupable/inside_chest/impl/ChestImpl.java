package fargoal.model.interactable.pickupable.inside_chest.impl;

import java.util.Random;

import fargoal.commons.api.Position;
import fargoal.model.events.impl.PickUpNewItemEvent;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.Spell;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.Utility;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;

/**
 * The implementation of the interface Chest. Inside the chest the player can found
 * an item, which is generated casually when the chest is opening.
 */
public class ChestImpl implements Interactable {

    static final int N_CHEST_ITEM = 15;
    private static final int CASE_DRIFT_SPELL = 0;
    private static final int CASE_INVISIBILITY_SPELL = 1;
    private static final int CASE_LIGHT_SPELL = 2;
    private static final int CASE_REGENERATION_SPELL = 3;
    private static final int CASE_SHIELD_SPELL = 4;
    private static final int CASE_END_SPELL = 5;
    private static final int CASE_BEGIN_TRAP = 6;
    private static final int CASE_CEILING_TRAP = 6;
    private static final int CASE_EXPLOSION = 7;
    private static final int CASE_PIT = 8;
    private static final int CASE_END_TRAP = 9;
    private static final int CASE_BEGIN_UTILITY = 10;
    private static final int CASE_BEACONS = 10;
    private static final int CASE_ENCHANTED_WEAPONS = 11;
    private static final int CASE_HEALING_POTION = 12;
    private static final int CASE_MAP = 13;
    private final Position position;
    private boolean open;
    private Renderer renderer;
    private final Random random;

    /**
     * The constructor of thi class. It set all the field of the class.
     * @param position - the position of the chest
     * @param renderFactory - a factory to create the renderer of all the elements of the floor.
     */
    public ChestImpl(final Position position, final RenderFactory renderFactory) {
        this.random = new Random();
        this.position = position;
        this.open = false;
        this.setRender(renderFactory);
    }

    /**
     * With this method it can be checked if the chest is open or not. 
     * @return a boolean that indicate if the chest is open or not.
     */
    public final boolean isOpen() {
        return this.open;
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.position;
    }
    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "CHEST";
    }

    /** {@inheritDoc} */
    @Override
    public Interactable interact(final FloorManager floorManager) {
        if (this.position.equals(floorManager.getPlayer().getPosition())) {
            final int num = this.random.nextInt(N_CHEST_ITEM);
            if (num >= 0 && num <= CASE_END_SPELL) {
                final Spell spell;
                if (num == CASE_DRIFT_SPELL) {
                    spell = new ChestItemFactoryImpl().generateDriftSpell();
                    floorManager.getPlayer().getInventory().addDriftSpell();
                } else if (num == CASE_INVISIBILITY_SPELL) {
                    spell = new ChestItemFactoryImpl().generateInvisibilitySpell();
                    floorManager.getPlayer().getInventory().addInvisibilitySpell();
                } else if (num == CASE_LIGHT_SPELL) {
                    spell = new ChestItemFactoryImpl().generateLightSpell();
                    floorManager.getPlayer().getInventory().addLightSpell();
                } else if (num == CASE_REGENERATION_SPELL) {
                    spell = new ChestItemFactoryImpl().generateRegenerationSpell();
                    floorManager.getPlayer().getInventory().addRegenerationSpell();
                } else if (num == CASE_SHIELD_SPELL) {
                    spell = new ChestItemFactoryImpl().generateShieldSpell();
                    floorManager.getPlayer().getInventory().addShieldSpell();
                } else {
                    spell = new ChestItemFactoryImpl().generateTeleportSpell();
                    floorManager.getPlayer().getInventory().addTeleportSpell();
                }
                floorManager.notifyFloorEvent(new PickUpNewItemEvent(spell));
            } else if (num >= CASE_BEGIN_TRAP && num <= CASE_END_TRAP) {
                if (num == CASE_CEILING_TRAP) {
                    new ChestItemFactoryImpl().generateCeilingTrap(floorManager);
                } else if (num == CASE_EXPLOSION) {
                    new ChestItemFactoryImpl().generateExplosion(floorManager);
                } else if (num == CASE_PIT) {
                    new ChestItemFactoryImpl().generatePit(floorManager);
                } else {
                    new ChestItemFactoryImpl().generateTeleport(floorManager);
                } 
            } else if (num >= CASE_BEGIN_UTILITY) {
                final Utility item;
                if (num == CASE_BEACONS) {
                    item = new ChestItemFactoryImpl().generateBeacon();
                    floorManager.getPlayer().getInventory().addBeacon();
                } else if (num == CASE_ENCHANTED_WEAPONS) {
                    item = new ChestItemFactoryImpl().generateEnchantedWeapon(floorManager);
                    floorManager.getPlayer().getInventory().addEnchantedWeapon();
                } else if (num == CASE_HEALING_POTION) {
                    item = new ChestItemFactoryImpl().generateHealingPotion();
                    floorManager.getPlayer().getInventory().addHealingPotion();
                } else if (num == CASE_MAP) {
                    item = new ChestItemFactoryImpl().generateMap();
                    floorManager.getPlayer().getInventory().addMap();
                } else {
                    item = new ChestItemFactoryImpl().generateMagicSack(floorManager);
                    floorManager.getPlayer().getInventory().addMagicSack();
                }
                floorManager.notifyFloorEvent(new PickUpNewItemEvent(item));
            }
            this.open = true;
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        this.renderer.render();
    }

    private void setRender(final RenderFactory rf) {
        this.renderer = rf.chestRenderer(this);
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (this.isOpen()) {
            floorManager.getAllElements().remove(this);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean exists() {
        return !this.isOpen();
    }

}
