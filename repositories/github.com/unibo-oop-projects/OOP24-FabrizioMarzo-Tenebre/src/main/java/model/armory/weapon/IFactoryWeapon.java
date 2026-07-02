package model.armory.weapon;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Factory for creating weapon instances.
 */
public interface IFactoryWeapon {
    /**
     * Creates a pistol weapon at the specified position.
     * 
     * @param posWeapon the initial position of the pistol
     * @return a new Pistol instance
     */
    Weapon createPistol(final Pair<Double, Double> posWeapon);
}
