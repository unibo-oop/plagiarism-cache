package net.pokemonbt.model.move.components.special;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.components.AbstractBehaviourDecorator;
import net.pokemonbt.model.move.components.MoveBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Special Behaviour exclusive to the {@link Move} "Substitute". 
 */
public final class Substitute extends AbstractBehaviourDecorator {
    public static final Float COST = 0.25f;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Substitute(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        values.ifPresent(val -> {
            throw new IllegalArgumentException("values must be an empty Optional.");
        });
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Substitute} to take the values from.
     */
    public Substitute(final MoveBehaviour base, final Substitute toCopy) {
        super(base);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        final var amount = this.requirement(user);
        if (
            amount.isPresent()
            && super.apply(user, opponent)
        ) {
            user.getStatComponent()
            .removeHealth(amount.get());
            user.getMoveComponent()
            .changeAvailability(super.getID(), false);

            return true;
        }
        return false;
    }

    /**
     * A private method used to find out if the user meets
     *      the criteria for the correct activation of this {@link Move}.
     * 
     * @param user The {@link Pokemon} trying to use the move.
     * @return An {@link Optional} empty when it is not possible to activate
     *      the move. Containing the {@link Integer} to pay otherwise.
     */
    private Optional<Integer> requirement(final Pokemon user) {
        final int maxHP = user.getStatComponent()
        .getBaseStat(PokeStatType.HP_MAX);
        final int actualHP = user.getStatComponent()
        .getHP();

        return actualHP > maxHP * COST
            ? Optional.of((int) (maxHP * COST))
            : Optional.empty();
    }
}
