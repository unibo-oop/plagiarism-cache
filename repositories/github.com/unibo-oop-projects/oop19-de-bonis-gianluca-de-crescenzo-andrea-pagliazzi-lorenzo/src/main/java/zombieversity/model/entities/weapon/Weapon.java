package zombieversity.model.entities.weapon;

import java.util.Optional;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Entity;

/**
 * Represents a general weapon that generates a specific {@link Attack} and that can be used by the player to defend himself from enemies.
 */
public interface Weapon extends Entity {

    /**
     * Used to distinguish the type of weapon.
     */
    enum WeaponType {
        /**
         * The weapon creates Attacks around the user.
         */
        SHORT_RANGE,
        /**
         * The weapon can create different types of long distance Attacks.
         */
        LONG_RANGE
    }

    /**
     * @return Weapon's name
     */
    String getName();

    /**
     * @param towards
     *          The point towards the weapon will attack beginning from its position.
     * @return
     *          Attack which represents the movement of its hit method.
     */
    Optional<Attack> attack(Point2D towards);

    /**
     * @return 
     *          The characteristic damage per hit of this weapon.
     */
    int getDamage();

    /**
     * @return
     *          The WeaponType of this weapon.
     */
    WeaponType getWeaponType();

    /**
     * Method used to update the internal structure of the weapon.
     */
    void update();

}
