package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Condition that creates a substitute that protects the pokemon. 
 */
public class Substitute extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean created;
    private int substituteHP;

    /**
     * @param behaviour {@inheritDoc} 
     * @param values a value that has to be empty when passed by the factory
     */
    public Substitute(final ConditionBehaviour behaviour, final Optional<JsonObject> values) {
        super(behaviour);
        created = false;

        if (!values.isEmpty()) {
            throw new IllegalArgumentException("Values has to be an empty Optional.");
        }
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Substitute} to take values from
     */
    public Substitute(final ConditionBehaviour behaviour, final Substitute toCopy) {
        super(behaviour);
        this.created = toCopy.created;
    }

    /**
     * @param user the user of the move 
     * @return if the trigger was successful
     */
    @Override
    public boolean trigger(final Pokemon user, final Pokemon opponent) {
        if (!created) {
            substituteHP = user.getStatComponent().getBaseStat(PokeStatType.HP_MAX) / 4;
            created = true;
        }
        if (substituteHP <= 0) {
            user.getConditionComponent().addToRemoveList(this.getID());
            created = false;
        }
        return super.trigger(user, opponent);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean onRemove(final Pokemon pokemon) {
        pokemon.getMoveComponent().clearForcedMove();
        pokemon.getMoveComponent()
        .changeAvailability(getID(), true);
        return super.onRemove(pokemon);
    }

    /**
     * @param damage {@inheritDoc}
     * @param isUser {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean modifyMove(final MoveDamageInstance damage, final boolean isUser) {
        if (!isUser && created && substituteHP > 0) {
            substituteHP -= damage.getDamage(); 
            damage.overrideBlocked(true);
        }
        return super.modifyMove(damage, isUser);
    }
}
