package model.armory.weapon;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;

/**
 * Represents a weapon capable of shooting munitions.
 * <p>
 * A weapon can be aimed and can shoot projectiles, providing information about
 * its current ammunition count.
 */
public interface Weapon {

    /**
     * Shoots projectiles based on the elapsed time since the last shot.
     * 
     * @param deltaTime the time elapsed (in milliseconds or seconds, depending on
     *                  your system) since the last shoot action
     * @return a list of {@link Munition} objects that were fired during this call
     */
    List<Munition> shoot(final double deltaTime);

    /**
     * Gets the current amount of ammunition available in the weapon.
     * 
     * @return the number of remaining ammunition units
     */
    int getAmmoCount();

    /**
     * Aims the weapon in the specified direction and sets the position from which
     * the weapon fires.
     * 
     * @param dirWeapon the direction vector in which the weapon is aimed
     * @param posWeapon the position of the weapon (e.g., the muzzle position)
     */
    void aim(final Pair<Double, Double> dirWeapon, final Pair<Double, Double> posWeapon);

}
