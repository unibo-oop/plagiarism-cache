package net.pokemonbt.model.battle;

/**
 * This interface is used to set an instace of damage inflicted by something,
 *      allowing a free access to it in a later moment.
 */
public interface DamageCallback {
    /**
     * Sets the actual {@link DamageInstance} inflicted on the opponent.
     * 
     * @param dmg The instance to set.
     */
    default void setLastDamageAmount(final MoveDamageInstance dmg) {
        throw new IllegalStateException("");
    }
}
