package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Condition that if is applied to a {@link Pokemon} kills the opponent when the user dies.
 */
public class DestinyBond extends AbstractConditionDecorator {

    @Serial
    private static final long serialVersionUID = 1L;
    private boolean isFirstTurn;

    /**
     * @param behaviour {@inheritDoc} 
     * @param value a value that has to be empty
     */
    public DestinyBond(final ConditionBehaviour behaviour, final Optional<JsonObject> value) {
        super(behaviour);
        this.isFirstTurn = true;
        if (!value.isEmpty()) {
            throw new IllegalArgumentException("Value has to be an empty Optional.");
        }
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link DestinyBond} to take values from
     */
    public DestinyBond(final ConditionBehaviour behaviour, final DestinyBond toCopy) {
        super(behaviour);
        this.isFirstTurn = toCopy.isFirstTurn;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon opponent, final Pokemon player) {
        if (player.getStatComponent().getHP() <= 0 && isFirstTurn) {
            opponent.getStatComponent()
            .removeHealth(opponent
                    .getStatComponent()
                    .getBaseStat(PokeStatType.HP_MAX));
        }
        if (isFirstTurn) {
            isFirstTurn = false;
        }
        return super.trigger(player, opponent);
    } 
}
