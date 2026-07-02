package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Causes the {@link Pokemon} user to take some of the damage inflicted 
 * with this {@link Move} as Recoil Damage.
 */
public final class Recoil extends AbstractBehaviourDecorator {
    public static final float PERCENTAGE = 0.33f;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Recoil(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);
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
    public Recoil(final MoveBehaviour base, final Recoil toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        if (super.apply(user, opponent)) {
            user.getStatComponent()
            .removeHealth((int) Math.floor(PERCENTAGE * this.getLastDamageAmount().getDamage()));

            return true;
        }
        return false;
    }
}
