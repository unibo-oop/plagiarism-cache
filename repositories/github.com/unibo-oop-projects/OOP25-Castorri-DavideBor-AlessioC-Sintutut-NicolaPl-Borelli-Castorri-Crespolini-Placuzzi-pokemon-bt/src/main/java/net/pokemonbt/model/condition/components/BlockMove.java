package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.utility.RandomUtility;
import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Stops the {@link Pokemon} from performing the {@link Move} he selected.
 */
public class BlockMove extends AbstractConditionDecorator {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int blockPercentage;

    /**
     * @param behaviour {@inheritDoc}
     * @param blockPercentage the percentage of the used move to be blocked
     */
    public BlockMove(final ConditionBehaviour behaviour, final Optional<JsonObject> blockPercentage) {
        super(behaviour);
        if (blockPercentage.isEmpty()) {
            throw new IllegalArgumentException("blockPercentage cannot be an empty Optional.");
        }
        this.blockPercentage = blockPercentage.get().get("percentage").getAsInt();
    }

    /**
     * @param behaviour the behaviour to decorate
     * @param toCopy the instance of {@link BlockMove} to take values from
     */
    public BlockMove(final ConditionBehaviour behaviour, final BlockMove toCopy) {
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
        if (isUser && !RandomUtility.check((float) blockPercentage / 100)) {
            damage.overridePower(0);
        }
        return super.modifyMove(damage, isUser);
    }
}
