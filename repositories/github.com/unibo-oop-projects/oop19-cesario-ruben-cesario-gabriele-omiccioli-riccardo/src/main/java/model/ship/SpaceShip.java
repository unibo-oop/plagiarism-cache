package model.ship;

import java.util.List;

import model.entity.ConditionalEntity;
import model.entity.MovingEntity;
import model.weapon.Weapon;

/**
 *  Models a ship most generic. It's a MovingEntity capable of shooting
 *  (therefore dealing damage), receiving damage, restoring itself and be
 *  destroyed.
 */
public interface SpaceShip extends MovingEntity, ConditionalEntity {

    /**
     * Returns the weapons available to this ship.
     * @return the weapons available to this ship.
     */
    List<Weapon> getWeapons();

    /**
     * Reduces the health of this ship according to the damage taken and
     * possibly other ship properties.
     * @param damage : damage taken by this ship
     */
    void receiveDamage(double damage);
    /**
     * Increases the health of this ship according to the healing received
     * and possibly other ship properties.
     * @param healthHealed : the amount of health to be restored
     */
    default void repairSelf(double healthHealed) {
        receiveDamage(-healthHealed);
    }

}
