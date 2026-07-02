package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * The {@link Pokemon} hits himself instead of the opponent.
 */
public class HitSelf extends AbstractConditionDecorator {

    @Serial
    private static final long serialVersionUID = 1L;
    private final int blockPercentage;

    /**
     * @param behaviour {@inheritDoc}
     * @param chance the chance of the {@link Pokemon} hitting himself
     */
    public HitSelf(final ConditionBehaviour behaviour, final Optional<JsonObject> chance) {
        super(behaviour);

        if (chance.isEmpty()) {
            throw new IllegalArgumentException("blockPercentage cannot be an empty Optional.");
        }
        this.blockPercentage = chance.get().get("percentage").getAsInt();
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link HitSelf} to take values from
     */
    public HitSelf(final ConditionBehaviour behaviour, final HitSelf toCopy) {
        super(behaviour);
        this.blockPercentage = toCopy.blockPercentage;
    }


    /**
     * @param damage {@inheritDoc}
     * @param isUser {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean modifyMove(final MoveDamageInstance damage, final boolean isUser) {
        if (!RandomUtility.check((float) blockPercentage / 100) && isUser) {
            damage.overridePower(0);
        }
        return super.modifyMove(damage, isUser);
    }
}
