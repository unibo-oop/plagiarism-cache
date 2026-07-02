package rogue.model.items.rings;

import java.util.function.Consumer;

import rogue.model.items.Equipment;
import rogue.model.items.armor.ArmorImpl;
import rogue.model.items.weapons.IncreaseAccuracy;
import rogue.model.items.weapons.IncreaseDamage;

/**
 * Represents an enumeration for declaring ring types.
 * 
 * The field keeps track a consumer describing its effect on the player.
 */
public enum RingType {

    /**
     * Increase armor AC by 2 points.
     */
    PROTECTION(e -> { 
        e.setArmor(new ArmorImpl(e.getArmor().getArmorType())); 
        e.getArmor().decreaseAC(2); 
    }),
    /**
     * Improves weapon accuracy.
     */
    DEXTERITY(e -> e.setWeapon(new IncreaseAccuracy(e.getWeapon()))),
    /**
     * Increases weapon damage.
     */
    INCREASE_DAMAGE(e -> e.setWeapon(new IncreaseDamage(e.getWeapon())));

    private final Consumer<Equipment> consumer;

    RingType(final Consumer<Equipment> consumer) {
        this.consumer = consumer;
    }

    /**
     * @return a Consumer which apply the ring's effect on the player given in input
     */
    protected Consumer<Equipment> getConsumer() {
        return this.consumer;
    }

}
