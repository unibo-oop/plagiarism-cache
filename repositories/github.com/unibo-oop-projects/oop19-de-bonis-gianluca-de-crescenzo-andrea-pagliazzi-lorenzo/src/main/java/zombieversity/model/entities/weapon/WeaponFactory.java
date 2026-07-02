package zombieversity.model.entities.weapon;

import java.time.Duration;

import javafx.geometry.Point2D;

/**
 * Creates a factory to create different types of weapons.
 */
public class WeaponFactory {

    public enum WeaponName {

        /**
         * A standard weapon with small magazine size, low damage and relatively low rate of fire, and fast reloading time.
         */
        GUN("Gun"),

        /**
         * A weapon with medium magazine size, medium damage, medium rate of fire and fast reloading time.
         */
        RIFLE("Rifle"),

        /**
         * A weapon with a great magazine size, high damage, high rate of fire but slow reloading time.
         */
        MACHINE_GUN("Machine Gun"),

        /**
         * A basic short range weapon that will perform an angular attack in front of the user.
         */
        KNIFE("Knife");

        private String name;

        WeaponName(final String name) {
            this.name = name;
        }

        /**
         * @return
         *      The name of the weapon as a String.
         */
        public String getName() {
            return this.name;
        }
    }

    private static final int GUN_DAMAGE = 25;

    private static final int RIFLE_DAMAGE = 50;
    private static final long RIFLE_RATE_OF_FIRE = 200;
    private static final long RIFLE_RECHARGE = 1500;
    private static final int RIFLE_MAGAZINE = 50;

    private static final int MACHINEGUN_DAMAGE = 75;
    private static final long MACHINEGUN_RATE_OF_FIRE = 50;
    private static final long MACHINEGUN_RECHARGE = 4000;
    private static final int MACHINEGUN_MAGAZINE = 100;

    /**
     * Method to obtain a standard long range weapon, defined by a {@link WeaponName}.
     * @param position
     *          The initial position of the weapon.
     * @param name
     *          The name of the weapon as a string.
     * @return
     *          The {@link WeaponName} that define the specific {@link LongRangeWeapon} requested.
     */
    public final LongRangeWeapon getLongRangeWeapon(final Point2D position, final WeaponName name) {
        switch (name) {
        case GUN:
            return this.getGun(position);
        case RIFLE:
            return this.getRifle(position);
        case MACHINE_GUN:
            return this.getMachineGun(position);
        default:
            return this.getGun(position);
        }
    }

    /**
     * Method to obtain a standard short range weapon, defined by a {@link WeaponName}.
     * @param position
     *          The initial position of the weapon.
     * @param name
     *          The name of the weapon as a string.
     * @return
     *          The {@link WeaponName} that define the specific {@link ShortRangeWeapon} requested.
     */
    public final ShortRangeWeapon getShortRangeWeapon(final Point2D position, final WeaponName name) {
        switch (name) {
        case KNIFE:
            return this.getKnife(position);
        default:
            return this.getKnife(position);
        }
    }

    private LongRangeWeapon getGun(final Point2D position) {
        return new LongRangeWeaponImpl.Builder(position, "Gun", GUN_DAMAGE)
                                      .build();
    }

    private LongRangeWeapon getRifle(final Point2D position) {
        return new LongRangeWeaponImpl.Builder(position, "Rifle", RIFLE_DAMAGE)
                                      .rechargeTime(Duration.ofMillis(RIFLE_RECHARGE))
                                      .rateOfFire(Duration.ofMillis(RIFLE_RATE_OF_FIRE))
                                      .magazineSize(RIFLE_MAGAZINE)
                                      .build();
    }

    private LongRangeWeapon getMachineGun(final Point2D position) {
        return new LongRangeWeaponImpl.Builder(position, "Machine Gun", MACHINEGUN_DAMAGE)
                                      .rechargeTime(Duration.ofMillis(MACHINEGUN_RECHARGE))
                                      .rateOfFire(Duration.ofMillis(MACHINEGUN_RATE_OF_FIRE))
                                      .magazineSize(MACHINEGUN_MAGAZINE)
                                      .build();
    }

    private ShortRangeWeapon getKnife(final Point2D position) {
        return new Knife(position);
    }

}
