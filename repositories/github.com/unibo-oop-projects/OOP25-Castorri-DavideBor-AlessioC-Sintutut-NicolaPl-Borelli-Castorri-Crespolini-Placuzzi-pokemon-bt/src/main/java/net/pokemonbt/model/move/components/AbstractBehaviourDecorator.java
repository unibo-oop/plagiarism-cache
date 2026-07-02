package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * A Behaviour Decorator implements a small part of the {@link MoveBehaviour}.
 */
public abstract class AbstractBehaviourDecorator implements MoveBehaviour {
    @Serial
    private static final long serialVersionUID = 1L;

    private final MoveBehaviour base;

    /**
     * Takes a base {@link MoveBehaviour} and adds different behaviours to it.
     * 
     * @param base The Behaviour to Decorate.
     */
    public AbstractBehaviourDecorator(final MoveBehaviour base) {
        this.base = MoveBehaviour.copyOf(base);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.base.getID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DamageInstance getLastDamageAmount() {
        return this.base.getLastDamageAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastDamageAmount(final MoveDamageInstance dmg) {
        this.base.setLastDamageAmount(dmg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyDamagePower(
        final Function<Integer, Integer> powerModifier
    ) {
        this.base.modifyDamagePower(powerModifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyDamageStat(
        final Supplier<Optional<Integer>> attackerStatModifier
    ) {
        this.base.modifyDamageStat(attackerStatModifier);
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {
        return this.base.apply(user, opponent);
    }

    /**
     * Takes the base that is being decorated by a specific instance.
     *      Only related classes can access this method.
     * 
     * @param origin The Bhaviour instance to take the decorated base.
     * @return The {@link MoveBehaviour} inside the input.
     */
    protected static MoveBehaviour baseOf(final MoveBehaviour origin) {
        if (AbstractBehaviourDecorator.class.isInstance(origin)) {
            try {
                return (MoveBehaviour) AbstractBehaviourDecorator.class.getDeclaredField("base").get(origin);
            } catch (final ReflectiveOperationException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new IllegalArgumentException("The parameter must be a part of the Decorator");
        } 
    }
}
