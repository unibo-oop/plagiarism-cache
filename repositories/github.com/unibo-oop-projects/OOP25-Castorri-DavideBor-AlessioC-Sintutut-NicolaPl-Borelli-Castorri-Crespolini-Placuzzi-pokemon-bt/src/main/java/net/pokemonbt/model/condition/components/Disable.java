package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Blocks the last {@link Move} used by the pokemon.
 */
public final class Disable extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean hasTriggered;
    private String disabledMove;

    /**
     * @param behaviour {@inheritDoc} 
     * @param value a value that gets passed by the factory and needs to be empty
     */
    public Disable(final ConditionBehaviour behaviour, final Optional<JsonObject> value) {
        super(behaviour);
        hasTriggered = false;
        if (!value.isEmpty()) {
            throw new IllegalArgumentException("Value has to be an empty Optional.");
        }
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Disable} to take values from
     */
    public Disable(final ConditionBehaviour behaviour, final Disable toCopy) {
        super(behaviour);
        this.hasTriggered = toCopy.hasTriggered;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        if (!hasTriggered && pokemon.getMoveComponent().getLastMoveId().isPresent()) {
            pokemon.getMoveComponent()
                .changeAvailability(
                    pokemon
                    .getMoveComponent()
                    .getLastMoveId()
                    .get(),
                    hasTriggered);
            disabledMove = pokemon
                    .getMoveComponent()
                    .getLastMoveId().get();

            hasTriggered = true;
        }
        return super.trigger(pokemon, opponent);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean onRemove(final Pokemon pokemon) {
        if (disabledMove != null) {
            pokemon.getMoveComponent()
                .changeAvailability(disabledMove, true);
        }
        return super.onRemove(pokemon);
    }
}
