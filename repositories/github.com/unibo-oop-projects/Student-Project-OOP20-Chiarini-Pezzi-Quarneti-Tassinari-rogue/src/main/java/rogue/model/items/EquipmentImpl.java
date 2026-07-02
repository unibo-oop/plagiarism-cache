package rogue.model.items;

import java.util.Optional;

import rogue.model.creature.Player;
import rogue.model.events.AbstractEventPublisher;
import rogue.model.events.EquipmentEvent;
import rogue.model.events.EventSubscriber;
import rogue.model.items.armor.Armor;
import rogue.model.items.armor.ArmorImpl;
import rogue.model.items.armor.ArmorType;
import rogue.model.items.rings.Ring;
import rogue.model.items.weapons.BaseWeapon;
import rogue.model.items.weapons.Weapon;
import rogue.model.items.weapons.WeaponType;

/**
 * An implementation for an {@link Equipment}.
 */
public final class EquipmentImpl extends AbstractEventPublisher implements Equipment {

    private final Player owner;
    private Weapon weapon;
    private Armor armor;
    private Optional<Ring> ring;

    private interface Operation {
        void doOperation();
    }

    /**
     * Memento pattern: used to save and restore the previous state after detaching a ring.
     */
    public static final class Memento {
        private final Weapon weapon;
        private final Armor armor;

        private Memento(final Weapon weapon, final Armor armor) {
            this.weapon = weapon;
            this.armor  = armor;
        }

    }

    /**
     * Creates a new EquipmentImpl.
     * @param player
     *          the player of the game
     */
    public EquipmentImpl(final Player player) {
        this.owner = player;
        this.weapon = new BaseWeapon(WeaponType.MACE);
        this.armor = new ArmorImpl(ArmorType.LEATHER);
        this.ring = Optional.empty();
    }

    /**
     * Registers the given subscriber on {@link EventBus} and 
     * post the initial status!
     */
    @Override
    public void register(final EventSubscriber subscriber) {
        super.register(subscriber);
        this.post(new EquipmentEvent(this));
    }

    private void set(final Operation op) {
        if (this.ring.isPresent()) {
            final Ring ring = this.ring.get();
            this.ring.get().stopUsing(this.owner);
            op.doOperation();
            ring.use(owner);
        } else {
            op.doOperation();
        }
        this.post(new EquipmentEvent(this));
    }

    @Override
    public void setArmor(final Armor armor) {
        this.set(() -> this.armor = armor);
    }

    @Override
    public void setWeapon(final Weapon weapon) {
        this.set(() -> this.weapon = weapon);
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public Armor getArmor() {
        return this.armor;
    }

    @Override
    public Optional<Ring> getRing() {
        return this.ring;
    }

    @Override
    public Memento save() {
        return new Memento(this.weapon, this.armor);
    }

    @Override
    public void attachRing(final Ring ring) {
        if (this.ring.isPresent()) {
            throw new IllegalStateException("One ring per time could be worn!");
        }
        this.ring = Optional.of(ring);
        this.post(new EquipmentEvent(this));
    }

    private void restore(final Memento m) {
        this.weapon = m.weapon;
        this.armor = m.armor;
    }

    @Override
    public boolean detachRing(final Memento memento) {
        if (this.ring.isPresent()) {
            this.ring = Optional.empty();
            this.restore(memento);
            this.post(new EquipmentEvent(this));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "EquipmentImpl [weapon=" + weapon + ", armor=" + armor + ", ring=" + ring + "]";
    }

}
