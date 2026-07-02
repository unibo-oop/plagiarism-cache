package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * The {@link Pokemon} heals every turn for a given ammount.
 */
public final class HealOverTime extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int healPercentage;

    /**
     * @param behaviour {@inheritDoc}
     * @param healPercentage for how much the condition heals in percentage
     */
    public HealOverTime(
        final ConditionBehaviour behaviour,
        final Optional<JsonObject> healPercentage
    ) {
        super(behaviour);

        if (healPercentage.isEmpty()) {
            throw new IllegalArgumentException("values cannot be an empty Optional.");
        }
        this.healPercentage = healPercentage.get().get("heal").getAsInt();
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link HealOverTime} to take values from
     */
    public HealOverTime(final ConditionBehaviour behaviour, final HealOverTime toCopy) {
        super(behaviour);
        this.healPercentage = toCopy.healPercentage;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        pokemon
        .getStatComponent()
        .increaseHealth((int) Math.floor(pokemon
        .getStatComponent()
        .getStat(
            PokeStatType.HP_MAX) * (float) healPercentage / 100));
        return super.trigger(pokemon, opponent);
    }
}
