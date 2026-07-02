package net.pokemonbt.model.battle;

import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Represents a single instance of damage to be applied to a {@link Pokemon}.
 */
public interface DamageInstance {
    /**
     * @return  The final amount of damage this
     *          {@link DamageInstance} will do to that pokemon.
     */
    int getDamage();

    /**
     * @return  If the {@link DamageInstance} is actually a "fake" damage
     *          with 0 power and 0 damage, actually used to apply a condition.
     */
    boolean isFake();

    /**
     * @return  If the {@link DamageInstance} has been fully blocked by a
     *          condition, ability or any other sources.
     */
    boolean isBlocked();

    /**
     * @return The {@link DamageInstance}'s type multiplier.
     */
    DamageTypeMultiplier getTypeMultiplier();

    /**
     * @return If the {@link DamageInstance}'s damage is a critical hit.
     */
    boolean isCritical();
}
