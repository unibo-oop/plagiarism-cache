package vg.utils;

/**
 * Enum that defines possible tier of weight of entities.
 * Entities will bounce after a collision only if the other
 * entity is in a MassTier equal or higher. {@link #NOCOLLISION} is
 * a mass tier that defines entities which cannot bounce.
 * The mass tiers are in ascending order as the ordinals of
 * this enum may be used. (Spoiler: they are indeed)
 */
public enum MassTier {

    /**
     * NOCOLLISION : this mass tier is the lowest one
     * and defines entities that will not bounce even if
     * colliding with other enties in this mass tier.
     */
    NOCOLLISION,
    /**
     * LOW : this mass tier is not used for any Entity
     * at the present time.
     */
    LOW,
    /**
     * MEDIUM : this is the mass tier for minor enemy such
     * as Mosquitoes.
     */
    MEDIUM,
    /**
     * HIGH : this is the mass tier for borders and bosses.
     */
    HIGH;

}
