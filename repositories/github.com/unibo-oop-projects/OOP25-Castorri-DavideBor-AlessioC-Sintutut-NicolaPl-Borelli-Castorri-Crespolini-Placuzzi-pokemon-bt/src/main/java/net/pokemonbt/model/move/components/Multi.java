package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * Makes a single {@link Move} hit multiple times in a single turn
 *      therefore creating multiple {@link DamageInstance}s.
 */
public final class Multi extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int hits;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Multi(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        if (values.isEmpty()) {
            throw new IllegalArgumentException("values cannot be an empty Optional.");
        }

        this.hits = values.orElseThrow().get("max").getAsInt();
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Multi} to take the values from.
     */
    public Multi(final MoveBehaviour base, final Multi toCopy) {
        super(base);

        this.hits = toCopy.hits;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {

        //Only the first hit determines if the move is successfull.
        final boolean success = super.apply(user, opponent);
        if (success) {
            for (int i = 0; i < RandomUtility.nextInteger(this.hits); i++) {
                super.apply(user, opponent);
            }
        }
        return success;
    }
}
