package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * A {@link Move} with a {@link Vampirism} Behaviour will heal
 * the {@link Pokemon} user based on the damage inflicted on
 * the {@link Pokemon} opponent with it.
 */
public final class Vampirism extends AbstractBehaviourDecorator {
    public static final Float PERCENTAGE = 0.5f;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Vampirism(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);
        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Vampirism} to take the values from.
     */
    public Vampirism(final MoveBehaviour base, final Vampirism toCopy) {
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
            .increaseHealth((int) Math.floor(PERCENTAGE * this.getLastDamageAmount().getDamage()));

            return true;
        }
        return false;
    }
}
