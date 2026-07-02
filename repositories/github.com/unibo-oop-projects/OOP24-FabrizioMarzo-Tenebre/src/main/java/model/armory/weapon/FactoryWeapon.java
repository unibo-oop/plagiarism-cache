package model.armory.weapon;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.armory.charger.FactoryCharger;

/**
 * {@inheritDoc}
 */
public class FactoryWeapon {

    private FactoryCharger factCharge = new FactoryCharger();

    /**
     * {@inheritDoc}
     */
    public Weapon createPistol(final Pair<Double, Double> posWeapon) {
        final double cooldownMillis = 450;
        final int shotsPerFire = 1;
        return new Pistol(new MutablePair<>(posWeapon.getLeft(), posWeapon.getRight()),
                cooldownMillis,
                shotsPerFire,
                factCharge.createDrumCharger(posWeapon));
    }

}
