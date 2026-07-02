package net.pokemonbt.model.condition;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import net.pokemonbt.model.battle.BattleEvent;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * An interface to with the basic methods needed by a condition.
 */
public interface Condition extends Serializable {
    @Serial
    long serialVersionUID = 1L;

    /**
     * @return If the condition is permanent.
     */
    boolean isPermanent();

    /**
     * @param pokemon to check if the condition can be applied
     * @return If the condition can be applied to the pokemon.
     */
    boolean canBeApplied(Pokemon pokemon);

    /**
     * @return The condition ID.
     */
    String getCondID();

    /**
     * Gets the condition's {@link ConditionBehaviour}.
     * 
     * @return  The reference to the component.
     */
    ConditionBehaviour getBehaviour();

    /**
     * @return The name of the condition.
     */
    String getName();

    /**
     * @return the description of the condition
     */
    String getDescription();

    /**
     * @return the battle event associated with the condition
     */
    BattleEvent getBattleEvent();

    /**
     * @param toCopy the {@link Condition} to make a copy of 
     * @return a new instance of the Condition with the data passed
     */
    static Condition copyOf(final Condition toCopy) {

        Objects.requireNonNull(toCopy);
        return new Condition() {
            @Override
            public boolean isPermanent() {
                return toCopy.isPermanent();
            }

            @Override
            public boolean canBeApplied(final Pokemon pokemon) {
                return toCopy.canBeApplied(pokemon);
            }

            @Override
            public String getCondID() {
                return toCopy.getCondID();
            }

            @Override
            public ConditionBehaviour getBehaviour() {
                return ConditionBehaviour.copyOf(toCopy.getBehaviour());
            }

            @Override
            public String getName() {
                return toCopy.getName();
            }

            @Override
            public String getDescription() {
                return toCopy.getDescription();
            }

            @Override
            public BattleEvent getBattleEvent() {
                return toCopy.getBattleEvent();
            }
        };
    }
}
