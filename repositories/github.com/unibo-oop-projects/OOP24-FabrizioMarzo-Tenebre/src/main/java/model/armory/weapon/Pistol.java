package model.armory.weapon;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.armory.charger.Charger;
import model.armory.munition.Munition;

/**
 * Represents a pistol weapon that can shoot munitions with a cooldown and
 * limited shots per fire.
 */
public class Pistol implements Weapon {

    private static final double ARM_POSITION_OFFSET_X = 20.0;
    private static final double ARM_POSITION_OFFSET_Y = 100.0;
    private final double cooldownMillis;
    private final int shotsPerFire;
    private Charger charger;
    private double timeSinceLastShot = 0;
    private Pair<Double, Double> dirWeapon;
    private Pair<Double, Double> posWeapon;

    /**
     * Constructs a pistol with the specified weapon position, cooldown, shots per
     * fire, and ammunition charger.
     * 
     * @param posWeapon      initial position of the weapon
     * @param cooldownMillis cooldown time between shots in milliseconds
     * @param shotsPerFire   number of shots fired per trigger pull
     * @param charger        the ammunition source for the pistol
     */
    public Pistol(final Pair<Double, Double> posWeapon, final double cooldownMillis, final int shotsPerFire,
            final Charger charger) {
        this.posWeapon = this.setPositionWeapon(posWeapon);
        this.charger = charger;
        this.cooldownMillis = cooldownMillis;
        this.shotsPerFire = shotsPerFire;
        this.dirWeapon = new MutablePair<>();
    }

    /**
     * Shoots munitions if cooldown has elapsed.
     * 
     * @param deltaTime time elapsed since last update (milliseconds)
     * @return list of fired munitions or empty list if cooldown not elapsed
     */
    @Override
    public List<Munition> shoot(final double deltaTime) {
        timeSinceLastShot += deltaTime;

        if (timeSinceLastShot < cooldownMillis) {
            return List.of();
        } else {
            List<Munition> result = new ArrayList<>();

            for (int i = 0; i < shotsPerFire; i++) {
                Munition m = charger.extractAmmunition();
                m.setShoot(this.dirWeapon, this.posWeapon);
                result.add(m);
            }

            timeSinceLastShot = 0;
            return result;
        }
    }

    /**
     * Adjusts the weapon position with predefined offsets.
     * 
     * @param pos base position
     * @return adjusted position with offsets applied
     */
    private Pair<Double, Double> setPositionWeapon(final Pair<Double, Double> pos) {
        double x = pos.getLeft() + ARM_POSITION_OFFSET_X;
        double y = pos.getRight() + ARM_POSITION_OFFSET_Y;
        return Pair.of(x, y);
    }

    /**
     * Returns the current amount of ammunition left in the charger.
     * 
     * @return number of munitions left
     */
    @Override
    public int getAmmoCount() {
        return this.charger.getCurrentLoad();
    }

    /**
     * Aims the weapon by setting the shooting direction and position.
     * 
     * @param dirWeapon direction vector to aim the weapon
     * @param posWeapon position from which the weapon fires
     */
    @Override
    public void aim(final Pair<Double, Double> dirWeapon, final Pair<Double, Double> posWeapon) {
        this.dirWeapon = dirWeapon;
        this.posWeapon = this.setPositionWeapon(posWeapon);
    }

}
