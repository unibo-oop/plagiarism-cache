package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import net.pokemonbt.model.battle.DamageCallback;
import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Interface describing the basic actions of a Behaviour.
 */
@FunctionalInterface
public interface MoveBehaviour extends DamageCallback, Serializable {
    @Serial
    long serialVersionUID = 1L;

    String ERROR_MSG = "Use the appropriate decorator.";

    /**
     * @param user The {@link Pokemon} activating the {@link Move}.
     * @param opponent The target  of the Attack.
     * @return 'true' on success and 'false' on failure.
     */
    boolean apply(Pokemon user, Pokemon opponent);

    /**
     * Needs to be replaced by creating a new class for the Decoration.
     * 
     * @return The move id.
     * @throws IllegalStateException If not properly set with a decorator. 
     */
    default String getID() {
        throw new IllegalStateException(ERROR_MSG);
    }

    /**
     * @return The latest damage amount created by the move.
     * @throws IllegalStateException If not properly set with a decorator.
     */
    default DamageInstance getLastDamageAmount() {
        throw new IllegalStateException(ERROR_MSG);
    }

    /**
     * Set the latest damage instance created by the move.
     * 
     * @throws IllegalStateException If not properly set with a decorator.
     */
    @Override
    default void setLastDamageAmount(final MoveDamageInstance dmg) {
        throw new IllegalStateException(ERROR_MSG);
    }

    /**
     * Applies a function to change the power under predermined conditions.
     * 
     * @param powerModifier To change the base power of the {@link Move}
     * @throws IllegalStateException If not properly set with a decorator.
     */
    default void modifyDamagePower(
       final Function<Integer, Integer> powerModifier
    ) {
        throw new IllegalStateException(ERROR_MSG);
    }

    /**
     * Applies a function to change the stat under predermined conditions.
     * 
     * @param attackerStatModifier To change the current stat of the {@link Pokemon} user.
     * @throws IllegalStateException If not properly set with a decorator.
     */
    default void modifyDamageStat(
       final Supplier<Optional<Integer>> attackerStatModifier
    ) {
        throw new IllegalStateException(ERROR_MSG);
    }

    /**
     * Creates a new Instance of a given {@link MoveBehaviour}, it takes into
     *      consideration a possible decorator.
     * 
     * @param behav The Behaviour to be copied.
     * @return A new instance of {@link MoveBehaviour} corrisponding the parameter.
     */
    static MoveBehaviour copyOf(final MoveBehaviour behav) {
        Objects.requireNonNull(behav);
        final String behavName = behav.getClass().getCanonicalName();

        try {
            if (AbstractBehaviourDecorator.class.isAssignableFrom(behav.getClass())) {
                final var base = AbstractBehaviourDecorator.baseOf(behav);

                //Load the same class and use the constructor to create a new decoration Instance.
                return (MoveBehaviour) Class.forName(behavName)
                .getDeclaredConstructor(
                    MoveBehaviour.class,
                    behav.getClass()
                )
                .newInstance(base, behav);
            } else {
                //Load the same class and use the constructor to create a new Instance.
                return (MoveBehaviour) Class.forName(behavName)
                .getDeclaredConstructor(behav.getClass())
                .newInstance(behav);
            }
        } catch (final ReflectiveOperationException e) {
            throw new IllegalArgumentException("The argument must contain a base.", e);
        }
    }
}
