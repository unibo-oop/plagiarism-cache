package rogue.model.items;

import java.util.Optional;

import rogue.model.events.EventPublisher;
import rogue.model.items.EquipmentImpl.Memento;
import rogue.model.items.armor.Armor;
import rogue.model.items.rings.Ring;
import rogue.model.items.weapons.Weapon;

/**
 * An interface modeling an equipment.
 */
public interface Equipment extends EventPublisher {

    /**
     * @return the armor currently in use
     */
    Armor getArmor();

    /**
     * Replaces the current armor with the given one.
     * @param armor
     *          the armor to be placed instead of the one currently in use
     */
    void setArmor(Armor armor);

    /**
     * @return the weapon currently in use
     */
    Weapon getWeapon();

    /**
     * Replaces the current weapon with the given one.
     * @param weapon
     *          the armor to be placed instead of the one currently in use
     */
    void setWeapon(Weapon weapon);

    /**
     * @return the ring currently in use
     */
    Optional<Ring> getRing();

    /**
     * Wear a ring. Note that only one ring per time could be worn.
     * @param ring
     *          the ring to put on
     * @throws IllegalStateException, if the equipment has already a ring
     */
    void attachRing(Ring ring);

    /**
     * Removes the current ring and restore the state before 
     * wearing the ring.
     * @param memento
     *          the state to restore
     * @return true if had been removed, false otherwise
     */
    boolean detachRing(Memento memento);

    /**
     * Stores the current state.
     * @return the memento stored
     */
    Memento save();

}
