package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.move.MoveCategory;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Forces the {@link Pokemon} to use attacking moves for some turns.
 */
public class Taunt extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean hasTriggered;

    /**
     * @param behaviour {@inheritDoc}
     * @param value a value that gets passed by the factory and needs to be empty
     */
    public Taunt(final ConditionBehaviour behaviour, final Optional<JsonObject> value) {
        super(behaviour);
        hasTriggered = false;
        if (!value.isEmpty()) {
            throw new IllegalArgumentException("value has to be an empty Optional.");
        }
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Taunt} to take values from
     */
    public Taunt(final ConditionBehaviour behaviour, final Taunt toCopy) {
        super(behaviour);
        this.hasTriggered = toCopy.hasTriggered;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        if (!hasTriggered) {
            pokemon
                .getMoveComponent()
                .getMoveSet()
                .stream()
                .filter(m -> m.getCategory() == MoveCategory.STATUS)
                .forEach(m -> {
                    pokemon.getMoveComponent()
                    .changeAvailability(this.getID(), false);
                });
            hasTriggered = true;
        }
        return super.trigger(pokemon, opponent);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean onRemove(final Pokemon pokemon) {
        pokemon.getMoveComponent()
                .getMoveSet()
                .stream()
                .filter(m -> m.getCategory() == MoveCategory.STATUS)
                .forEach(m -> {
                    pokemon.getMoveComponent()
                    .changeAvailability(this.getID(), true);
                });
        return super.onRemove(pokemon);
    }
}
