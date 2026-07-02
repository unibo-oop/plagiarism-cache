package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.io.Serializable;

import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Interface to check if a {@link Condition} can be applied to a {@link Pokemon}.
 */
@FunctionalInterface
public interface ConditionCheck extends Serializable {

    @Serial
    long serialVersionUID = 1L;

    /**
     * @param target the {@link Pokemon} that needs the condition to be applied
     * 
     * @return if the condition can be applied to the {@link Pokemon}
     */
    boolean canBeApplied(Pokemon target);
}
