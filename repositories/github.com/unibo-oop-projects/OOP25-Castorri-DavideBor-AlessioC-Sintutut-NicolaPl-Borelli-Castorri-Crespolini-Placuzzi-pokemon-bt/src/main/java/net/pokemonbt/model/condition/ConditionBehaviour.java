package net.pokemonbt.model.condition;

import java.io.Serial;
import java.io.Serializable;

import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.components.AbstractConditionDecorator;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Interface of the general actions of a condition.
 */
public interface ConditionBehaviour extends Serializable {

    @Serial
    long serialVersionUID = 1L;

    /**
     * @param pokemon the pokemon that has the condition applied
     * @param opponent the opponent of the pokemon that has the condition
     * @return true if it is able to apply the condition false otherwise
     */
    boolean trigger(Pokemon pokemon, Pokemon opponent);

    /**
     * @param damage the {@link DamageInstance} of the move that was chosen
     * @param isUser if the damage to modify is from the user or the enemy
     * @return true if it was able to do everything it needed
     */
    boolean modifyMove(MoveDamageInstance damage, boolean isUser);

    /**
     * @return the ID of the condition 
     */
    String getID();

    /**
     * @param pokemon the pokemon that has the condition applied
     * @return true if is able to apply it's last effect false otherwise 
     */
    boolean onRemove(Pokemon pokemon);

    /**
     * @param toCopy the {@link ConditionBehaviour} to make a copy of 
     * @return a new instance of the ConditionBehaviour with the data passed
     */
    static ConditionBehaviour copyOf(final ConditionBehaviour toCopy) {

        if (AbstractConditionDecorator.class.isAssignableFrom(toCopy.getClass())) {
            try {

                final var base = AbstractConditionDecorator.baseOf(toCopy);

                final String behaviourName = toCopy.getClass().getCanonicalName();

                //Load the same class and use the constructor to create a new Instance.
                return (ConditionBehaviour) Class.forName(behaviourName)
                .getDeclaredConstructor(
                    ConditionBehaviour.class,
                    toCopy.getClass()
                )
                .newInstance(base, toCopy);

            } catch (final ReflectiveOperationException e) {
                throw new IllegalArgumentException("The argument must contain a base.", e);
            }
        } else {
            return toCopy;
        }
    }
}
