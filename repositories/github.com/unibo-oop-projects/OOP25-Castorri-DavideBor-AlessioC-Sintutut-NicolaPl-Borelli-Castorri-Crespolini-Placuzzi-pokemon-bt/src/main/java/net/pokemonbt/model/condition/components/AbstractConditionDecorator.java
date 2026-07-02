package net.pokemonbt.model.condition.components;

import java.io.Serial;

import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.components.AbstractPokeComponent;

/**
 * 
 */
public abstract class AbstractConditionDecorator extends AbstractPokeComponent implements ConditionBehaviour {

    @Serial
    private static final long serialVersionUID = 1L;
    private final ConditionBehaviour decorated;

    /**
     * @param decorated the behaviour of the condition to apply the effect
     */
    public AbstractConditionDecorator(final ConditionBehaviour decorated) {
        this.decorated = ConditionBehaviour.copyOf(decorated);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        return this.decorated.trigger(pokemon, opponent);
    }

    /**
     * @param damage the damage that needs to be modified by the condition 
     * @param isUser if the damage passed is from the user or the enemy
     * @return {@inheritDoc}
     */
    @Override
    public boolean modifyMove(final MoveDamageInstance damage, final boolean isUser) {
        return this.decorated.modifyMove(damage, isUser);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getID() {
        return this.decorated.getID(); 
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean onRemove(final Pokemon pokemon) {
        return this.decorated.onRemove(pokemon);
    }

    /**
     * @return the behaviour of the current condition
     */
    public ConditionBehaviour getBehaviour() {
        return this.decorated;
    }

    /**
     * copyOf used to create copies of behaviours with values that would otherwise be lost.
     * 
     * @param toCopy The Bhaviour instance to take the decorated base.
     * @return a copy of the instance of the ConditionBehaviour
     */
    public static ConditionBehaviour baseOf(final ConditionBehaviour toCopy) {
        try {
            return (ConditionBehaviour) toCopy.getClass().getSuperclass().getDeclaredField("decorated").get(toCopy);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
