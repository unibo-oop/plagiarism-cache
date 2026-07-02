package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.pokemon.Pokemon;

/**
 * After the first use of the move the user is forced to "Recharge", wasting a turn without moving.
 * Is the opposite of {@link Delay} behaviour.
 */
public final class Recharge extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean ready;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Recharge(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);
        this.ready = true;

        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Recoil} to take the values from.
     */
    public Recharge(final MoveBehaviour base, final Recharge toCopy) {
        super(base);
        this.ready = toCopy.ready;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        boolean success = false;
        if (this.ready) {
            success = super.apply(user, opponent);
            user.getMoveComponent()
            .forceMove(this.getID());

            this.ready = false;
        } else {
            user.getMoveComponent()
            .clearForcedMove();

            this.ready = true;
        }

        return success;
    }
}
