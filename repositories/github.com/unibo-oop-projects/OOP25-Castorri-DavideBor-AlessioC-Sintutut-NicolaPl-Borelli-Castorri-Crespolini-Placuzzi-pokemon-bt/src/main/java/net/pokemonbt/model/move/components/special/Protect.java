package net.pokemonbt.model.move.components.special;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.components.AbstractBehaviourDecorator;
import net.pokemonbt.model.move.components.MoveBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * Special Behaviour exclusive to the {@link Move} "Protect". 
 */
public final class Protect extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int MAX_USES = 3;
    private int consecutiveUses;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Protect(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);
        this.consecutiveUses = 0;

        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Protect} to take the values from.
     */
    public Protect(final MoveBehaviour base, final Protect toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        if (
            user.getMoveComponent()
            .getLastMoveId()
            .orElse("")
            .equals(this.getID())
        ) {
            this.consecutiveUses++;
        } else {
            this.consecutiveUses = 0;
        }

        return RandomUtility.nextInteger(MAX_USES) >= this.consecutiveUses
            && super.apply(user, opponent);
    }
}
