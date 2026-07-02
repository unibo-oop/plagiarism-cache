package it.unibo.isaccoop.model.weapon;

import java.util.function.BiFunction;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.common.Vector2D;

/**
 * TimeIntervalWeapon class that models a weapon which shots with time intervals.
 * */
public class TimeIntervalWeapon extends AbstractWeapon {

    private final BiFunction<Point2D, Vector2D, WeaponShot> weaponShotSupplier;
    private long timeSinceLastShot;
    private double shotTimeInterval;

    /**
     * TimeIntervalWeapon constructor.
     *
     * @param shotTimeInterval time insterval between shots
     * @param weaponShotSupplier {@link BiFunction} to get a new weapon shot instance
     * */
    public TimeIntervalWeapon(final Double shotTimeInterval, final BiFunction<Point2D, Vector2D, WeaponShot> weaponShotSupplier) {
        this.weaponShotSupplier = weaponShotSupplier;
        this.timeSinceLastShot = System.currentTimeMillis();
        this.shotTimeInterval = shotTimeInterval;
    }

    /**
     * Make a new shoot after a certain time interval.
     *
     * @param startPosition shot start position
     * @param direction shot direction vector
     * */
    @Override
    public void shoot(final Point2D startPosition, final Vector2D direction) {
        if (System.currentTimeMillis() - this.timeSinceLastShot > this.shotTimeInterval) {
            super.addWeaponShot(this.weaponShotSupplier.apply(startPosition, direction));
            this.timeSinceLastShot = System.currentTimeMillis();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWeaponInterval(final double interval) {
        this.shotTimeInterval = interval;
    }
}
