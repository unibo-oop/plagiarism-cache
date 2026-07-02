package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.StatMod;

/**
 * A debuff applied by the {@link Condition} to the pokemon's {@link BaseStats}.
 */
public final class Debuff extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean hasTriggered;
    private final String statID;

    /**
     * @param behaviour {@inheritDoc} 
     * @param statID the ID of the stat to change 
     */
    public Debuff(final ConditionBehaviour behaviour, final Optional<JsonObject> statID) {
        super(behaviour);
        hasTriggered = false;

        if (statID.isEmpty()) {
            throw new IllegalArgumentException("statID cannot be an empty Optional.");
        }
        this.statID = statID.get().get("stat").getAsString();
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Debuff} to take values from
     */
    public Debuff(final ConditionBehaviour behaviour, final Debuff toCopy) {
        super(behaviour);
        this.statID = toCopy.statID;
        this.hasTriggered = toCopy.hasTriggered;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        if (!hasTriggered) {
                pokemon
                .getStatComponent()
                .addStatMod(PokeStatType
                        .stringToStatType(statID).get(), 
                        this.getID(), 
                        new StatMod(1, false, true));
            hasTriggered = true;
        }
        return super.trigger(pokemon, opponent);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean onRemove(final Pokemon pokemon) {
        pokemon
            .getStatComponent()
            .removeStatMod(PokeStatType
                        .stringToStatType(statID)
                        .get(), this.getID());
        return super.onRemove(pokemon);
    }
}
