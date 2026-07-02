package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Protects the pokemon form all incoming damage from moves.
 */
public class Protected extends AbstractConditionDecorator {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param behaviour {@inheritDoc} 
     * @param value a value that has to be empty
     */
    public Protected(final ConditionBehaviour behaviour, final Optional<JsonObject> value) {
        super(behaviour);
        if (!value.isEmpty()) {
            throw new IllegalArgumentException("Value has to be an empty Optional.");
        }
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Protected} to take values from
     */
    public Protected(final ConditionBehaviour behaviour, final Protected toCopy) {
        super(behaviour);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        pokemon.getConditionComponent().addToRemoveList(this.getID());
        return super.trigger(pokemon, opponent);
    }

    /**
     * @param damage {@inheritDoc}
     * @param isUser {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean modifyMove(final MoveDamageInstance damage, final boolean isUser) {
        if (!isUser) {
            damage.overrideBlocked(true);
        }
        return super.modifyMove(damage, isUser);
    }
}
