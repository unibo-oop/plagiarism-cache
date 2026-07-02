package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Forces the {@link Pokemon} to use the same {@link Move} he used the last turn.
 */
public final class Encore extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean hasTriggered;

    /**
     * @param behaviour {@inheritDoc}
     * @param value a value that gets passed by the factory and needs to be empty
     */
    public Encore(final ConditionBehaviour behaviour, final Optional<JsonObject> value) {
        super(behaviour);
        hasTriggered = false;
        if (!value.isEmpty()) {
            throw new IllegalArgumentException("Value has to be an empty Optional.");
        }
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Encore} to take values from
     */
    public Encore(final ConditionBehaviour behaviour, final Encore toCopy) {
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
                .forceMove(
                    pokemon
                    .getMoveComponent()
                    .getLastMoveId()
                    .get());

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
            .getMoveComponent()
            .clearForcedMove();
        return super.onRemove(pokemon);
    }
}
