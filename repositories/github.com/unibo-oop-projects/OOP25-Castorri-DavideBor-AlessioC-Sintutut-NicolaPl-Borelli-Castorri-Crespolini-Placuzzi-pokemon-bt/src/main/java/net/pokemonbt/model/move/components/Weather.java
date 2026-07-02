package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Decorator related to an Optional behaviour.
 */
public final class Weather extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Weather(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        if (values.isEmpty()) {
            throw new IllegalArgumentException("values cannot be an empty Optional.");
        }
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Weather} to take the values from.
     */
    public Weather(final MoveBehaviour base, final Weather toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        System.err.println(//NOPMD
            this.getClass().getSimpleName() 
            + " is OPTIONAL feature. It may be implemented in the future."
        );

        return super.apply(user, opponent);
    }
}
