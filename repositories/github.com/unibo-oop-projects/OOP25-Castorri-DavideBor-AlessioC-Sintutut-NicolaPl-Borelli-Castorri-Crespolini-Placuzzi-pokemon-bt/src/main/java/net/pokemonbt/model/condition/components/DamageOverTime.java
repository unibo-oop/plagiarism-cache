package net.pokemonbt.model.condition.components;

import java.io.Serial;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import java.util.Optional;

import com.google.gson.JsonObject;

/**
 * The {@link Pokemon} takes damage every turn for a given ammount.
 */
public final class DamageOverTime extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int damagePercentage;

    /**
     * @param behaviour {@inheritDoc}
     * @param damagePercentage how much damage the condition should deal in percentage
     */
    public DamageOverTime(
        final ConditionBehaviour behaviour,
        final Optional<JsonObject> damagePercentage
    ) {
        super(behaviour);

        if (damagePercentage.isEmpty()) {
            throw new IllegalArgumentException("damagePercentage cannot be an empty Optional.");
        }
        this.damagePercentage = damagePercentage.get().get("damage").getAsInt();
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link DamageOverTime} to take values from
     */
    public DamageOverTime(final ConditionBehaviour behaviour, final DamageOverTime toCopy) {
        super(behaviour);
        this.damagePercentage = toCopy.damagePercentage;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        pokemon.getStatComponent()
        .removeHealth((int) 
                Math.floor(
                    pokemon.getStatComponent()
                    .getStat(PokeStatType.HP_MAX) * (float) damagePercentage / 100));
        return super.trigger(pokemon, opponent);
    }
}
