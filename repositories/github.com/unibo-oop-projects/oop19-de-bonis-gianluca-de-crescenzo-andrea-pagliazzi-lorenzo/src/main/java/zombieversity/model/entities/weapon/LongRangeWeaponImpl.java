package zombieversity.model.entities.weapon;

import java.time.Duration;
import java.util.Optional;

import javafx.geometry.Point2D;

/**
 * Implementation of a {@link LongRangeWeapon}.
 */
public final class LongRangeWeaponImpl extends AbstractLongRangeWeapon {

    private final long rechargeTime;
    private final int damage;
    private final long rateOfFire;
    private final int magazineSize;
    private final String name;

    private long lastShot;
    private int ammoInMagazine;
    private boolean isRecharging;
    private long rechargingTime;

    public LongRangeWeaponImpl(final Point2D p2d, final String name, final Integer damage,
            final Long rechargeTime, final Long rateOfFire, final Integer magazineSize) {
        super(p2d);

        this.name = name;
        this.rechargeTime = rechargeTime;
        this.damage = damage;
        this.rateOfFire = rateOfFire;
        this.magazineSize = magazineSize;

        this.ammoInMagazine = magazineSize;
        this.isRecharging = false;
        this.lastShot = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getActualAmmo() {
        return this.ammoInMagazine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Attack> attack(final Point2D towards) {
        if (System.currentTimeMillis() - this.lastShot <= rateOfFire || isRecharging || this.ammoInMagazine <= 0) {
            return Optional.empty();
        }

        this.ammoInMagazine--;
        this.lastShot = System.currentTimeMillis();
        return Optional.of(new Bullet(this.getPosition(), towards, damage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startRecharging() {
        this.isRecharging = true;
        this.rechargingTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecharging() {
        return this.isRecharging;
    }

    private void rechargeMagazine() {
        this.ammoInMagazine = magazineSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (!isRecharging && this.ammoInMagazine <= 0) {
            this.startRecharging();
        }

        if (isRecharging && System.currentTimeMillis() - this.rechargingTime >= rechargeTime) {
            this.isRecharging = false;
            this.rechargeMagazine();
        }
    }

    /**
     * A builder to simplify the creation a LongRangeWeaponImpl.
     */
    public static class Builder {

        private static final long STANDARD_RECHARGE_TIME = 150;
        private static final long STANDARD_RATE_OF_FIRE = 300;
        private static final int STANDARD_MAGAZINE_SIZE = 10;

        private final String name;
        private final int damage;
        private final Point2D position;
        private Optional<Long> rechargeTime;
        private Optional<Long> rateOfFire;
        private Optional<Integer> magazineSize;

        /**
         * Instantiates a simple weapon with standard values.
         * @param position
         *      The starting position of the weapon.
         * @param name
         *      The weapon's name.
         * @param damage
         *      The typical damage dealt by this weapon.
         */
        public Builder(final Point2D position, final String name, final int damage) {
            this.position = position;
            this.name = name;
            this.damage = damage;
            this.rechargeTime = Optional.empty();
            this.rateOfFire = Optional.empty();
            this.magazineSize = Optional.empty();
        }

        /**
         * Used to set the reloading time.
         * @param duration
         *      The duration of the reloading time.
         * @return
         *      A Builder with reloading time set.
         */
        public final Builder rechargeTime(final Duration duration) {
            this.rechargeTime = Optional.of(duration.toMillis());
            return this;
        }

        /**
         * Used to set the rate of fire of the weapon.
         * @param duration
         *      The interval of time that will be waited between of shot and another.
         * @return
         *      A Builder with its rate of fire set.
         */
        public final Builder rateOfFire(final Duration duration) {
            this.rateOfFire = Optional.of(duration.toMillis());
            return this;
        }

        /**
         * Used to set the reloading time.
         * @param magazineSize
         *      The maximum ammo that will be in the magazine.
         * @return
         *      A Builder with magazine size set.
         */
        public final Builder magazineSize(final int magazineSize) {
            this.magazineSize = Optional.of(magazineSize);
            return this;
        }

        /**
         * @return
         *      A {@link LongRangeWeaponImpl} with the set properties.
         */
        public final LongRangeWeapon build() {
            return new LongRangeWeaponImpl(this.position, this.name, this.damage,
                    this.rechargeTime.orElse(STANDARD_RECHARGE_TIME), this.rateOfFire.orElse(STANDARD_RATE_OF_FIRE),
                    this.magazineSize.orElse(STANDARD_MAGAZINE_SIZE));
        }

    }

}
