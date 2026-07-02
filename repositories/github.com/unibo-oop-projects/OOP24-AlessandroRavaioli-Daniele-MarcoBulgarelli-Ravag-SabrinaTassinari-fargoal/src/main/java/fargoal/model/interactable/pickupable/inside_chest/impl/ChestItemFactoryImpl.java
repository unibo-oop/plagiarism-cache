package fargoal.model.interactable.pickupable.inside_chest.impl;

import fargoal.model.interactable.pickupable.inside_chest.api.ChestItem;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItemFactory;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.Spell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.DriftSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.InvisibilitySpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.LightSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.RegenerationSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.ShieldSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.TeleportSpell;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.CeilingTrap;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.Explosion;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.Pit;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.Teleport;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.Utility;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.Beacon;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.EnchantedWeapon;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.HealingPotion;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.MagicSack;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.Map;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements the interface ChestItemFactory. It generate the chest items.
 */
public class ChestItemFactoryImpl implements ChestItemFactory {

    /** {@inheritDoc} */
    @Override
    public Spell generateDriftSpell() {
        return new DriftSpell();
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateInvisibilitySpell() {
        return new InvisibilitySpell();
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateLightSpell() {
        return new LightSpell();
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateRegenerationSpell() {
        return new RegenerationSpell();
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateShieldSpell() {
        return new ShieldSpell();
    }

    /** {@inheritDoc} */
    @Override
    public Spell generateTeleportSpell() {
        return new TeleportSpell();
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generateCeilingTrap(final FloorManager floorManager) {
        return new CeilingTrap(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generateExplosion(final FloorManager floorManager) {
        return new Explosion(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generatePit(final FloorManager floorManager) {
        return new Pit(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public ChestItem generateTeleport(final FloorManager floorManager) {
        return new Teleport(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateBeacon() {
        return new Beacon();
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateEnchantedWeapon(final FloorManager floorManager) {
        return new EnchantedWeapon(floorManager);
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateHealingPotion() {
        return new HealingPotion();
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateMap() {
        return new Map();
    }

    /** {@inheritDoc} */
    @Override
    public Utility generateMagicSack(final FloorManager floorManager) {
        return new MagicSack(floorManager);
    }

}
