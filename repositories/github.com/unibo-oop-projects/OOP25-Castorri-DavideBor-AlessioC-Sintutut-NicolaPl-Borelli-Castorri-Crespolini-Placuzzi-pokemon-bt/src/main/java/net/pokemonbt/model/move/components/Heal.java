package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Allows the {@link Pokemon} user to Heal themselves by a portion of the Max HP.
 */
public final class Heal extends AbstractBehaviourDecorator {
    public static final float HEAL_PERCENTAGE = 0.5f;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Heal(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Heal} to take the values from.
     */
    public Heal(final MoveBehaviour base, final Heal toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        final int maxHP = user.getStatComponent()
        .getStat(PokeStatType.HP_MAX);

        if (super.apply(user, opponent)) {
            user.getStatComponent()
            .increaseHealth((int) Math.floor(maxHP * HEAL_PERCENTAGE));
            return true;
        }
        return false;
    }

}
