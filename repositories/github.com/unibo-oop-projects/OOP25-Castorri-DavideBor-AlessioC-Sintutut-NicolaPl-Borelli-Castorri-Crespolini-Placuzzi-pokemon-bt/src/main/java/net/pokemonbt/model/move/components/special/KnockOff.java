package net.pokemonbt.model.move.components.special;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.components.AbstractBehaviourDecorator;
import net.pokemonbt.model.move.components.MoveBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Special Behaviour exclusive to the {@link Move} "Knock Off". 
 */
public final class KnockOff extends AbstractBehaviourDecorator {
    public static final Float POW_MOD = 0.5f;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public KnockOff(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link KnockOff} to take the values from.
     */
    public KnockOff(final MoveBehaviour base, final KnockOff toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        if (opponent.getItemComponent().isItemPresent()) {
            this.modifyDamagePower(pow -> (int) Math.floor(pow * POW_MOD));
            opponent.getItemComponent().removeItem();
        }
        return super.apply(user, opponent);
    }
}
