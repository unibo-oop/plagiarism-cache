package model.weapon.basic;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import model.weapon.basic.BasicWeapon.StandardProjectileTemplate;
import utilities.exception.IllegalInitializationException;

/**
 * Models a factory of BasicWeapon, capable of building different variants of BasicWeapon.
 */
public class BasicWeaponFactory {

    private static final Map<BasicWeaponVariant, BasicWeapon> BASICWEAPON_TABLE = new EnumMap<>(BasicWeaponVariant.class);

    static {
        initializeData();
    }

    /**
     * Returns a BasicWeapon with the properties associated to the specified variant. 
     * @param variant : the specified variant of BasicWeapon.
     * @return a BasicWeapon with the properties associated to the specified variant. 
     */
    public BasicWeapon build(final BasicWeaponVariant variant) {
        return BASICWEAPON_TABLE.get(variant);
    }

    /**
     * Initialises the tables that map each BasicWeaponVariant to their respective BasicWeapon.
     */
    private static void initializeData() {
        BASICWEAPON_TABLE.putAll(Map.ofEntries(

                Map.entry(BasicWeaponVariant.DEFAULT_WEAPON, new BasicWeapon()),
                /*BasicWeapon(StandardProjectileTemplate(speed, radius, damage), rateOfFire)*/
                /*-------|WeaponVariant                                                                         |Spd. |R.  |Dmg. |RPM */
                Map.entry(BasicWeaponVariant.ENEMY_HEAVY_CANNON,  new BasicWeapon(new StandardProjectileTemplate(0.8,  0.4, 100), 105)),
                Map.entry(BasicWeaponVariant.ENEMY_CANNON,        new BasicWeapon(new StandardProjectileTemplate(0.9,  0.4, 60),  80)),
                Map.entry(BasicWeaponVariant.ENEMY_LIGHT_CANNON,  new BasicWeapon(new StandardProjectileTemplate(1,    0.4, 20),  40)),
                Map.entry(BasicWeaponVariant.PLAYER_HEAVY_CANNON, new BasicWeapon(new StandardProjectileTemplate(2.0,  0.4, 50),  40)),
                Map.entry(BasicWeaponVariant.PLAYER_CANNON,       new BasicWeapon(new StandardProjectileTemplate(2.25, 0.4, 30),  30)),
                Map.entry(BasicWeaponVariant.PLAYER_LIGHT_CANNON, new BasicWeapon(new StandardProjectileTemplate(2.5,  0.4, 10),  10))
        ));
        if (!BASICWEAPON_TABLE.keySet().containsAll(Set.of(BasicWeaponVariant.values()))) {
            throw new IllegalInitializationException("BasicWeaponFactory.initializeData: the basic weapon table doesn't have a key for each possible basic weapon variant.");
        }
    }

    /**
     * Models a variant of a BasicWeapon.
     */
    public enum BasicWeaponVariant {
        /**Refers to a BasicWeapon with default properties.*/
        DEFAULT_WEAPON,

        /**Refers to a BasicWeapon that shoots slowly but has projectiles with high damage.*/
        ENEMY_HEAVY_CANNON,

        /**Refers to a balanced BasicWeapon. */
        ENEMY_CANNON,

        /**Refers to a BasicWeapon that shoots fast but has projectiles with low damage. */
        ENEMY_LIGHT_CANNON,

        /**Refers to a BasicWeapon that shoots slowly but has projectiles with high damage. Stronger than the enemy version.*/
        PLAYER_HEAVY_CANNON,

        /**Refers to a balanced BasicWeapon. Stronger than the enemy version.*/
        PLAYER_CANNON,

        /**Refers to a BasicWeapon that shoots fast but has projectiles with low damage. Stronger than the enemy version.*/
        PLAYER_LIGHT_CANNON,
    }
}
