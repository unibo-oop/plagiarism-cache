package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Objects;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * This decoration is used as a Base for each behaviour.
 */
public class Base implements MoveBehaviour {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String moveId;

    /**
     * @param id The 'id' of the {@link Move} this Behaviour is part of.
     */
    public Base(final String id) {
        this.moveId = id;
    }

    /**
     * Create a new Instance using a different one already existing.
     * 
     * @param toCopy An old instance of {@link Base} to take the values from.
     */
    public Base(final Base toCopy) {
        this.moveId = String.valueOf(toCopy.moveId);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.moveId;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        Objects.requireNonNull(this.getID());
        return true;
    }
}
