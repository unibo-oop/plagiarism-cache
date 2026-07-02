package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * A Move with a {@link Repeat} Behaviour forces the user to use
 * the same move for multiple turns.
 * 
 */
public final class Repeat extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int min;
    private final int maxExclusive;
    private int uses;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Repeat(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        if (values.isEmpty()) {
            throw new IllegalArgumentException("values cannot be an empty Optional.");
        }

        this.min = values.get().get("min").getAsInt();
        this.maxExclusive = values.get().get("max").getAsInt() + 1;
        this.uses = 0;
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Repeat} to take the values from.
     */
    public Repeat(final MoveBehaviour base, final Repeat toCopy) {
        super(base);

        this.min = toCopy.min;
        this.maxExclusive = toCopy.maxExclusive;
        this.uses = toCopy.uses;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {

        if (this.uses > 0) {
            this.uses--;
            if (this.uses == 0) {
                user.getMoveComponent()
                .clearForcedMove();
            }
        } else {
            this.uses = RandomUtility.nextInteger(this.min, this.maxExclusive);

            user.getMoveComponent()
            .forceMove(this.getID());
        }
        return super.apply(user, opponent);
    }
}
