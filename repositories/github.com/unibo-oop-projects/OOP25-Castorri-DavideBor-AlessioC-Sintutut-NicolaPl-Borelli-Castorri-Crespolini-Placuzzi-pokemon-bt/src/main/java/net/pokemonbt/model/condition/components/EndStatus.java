package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * The property of a {@link Condition} to remove itself.
 */
public final class EndStatus extends AbstractConditionDecorator {

    @Serial
    private static final long serialVersionUID = 1L;
    private boolean toRemove;
    private int countDown;
    private int removedPercentage;
    private final boolean increasing;

    /**
     * @param behaviour {@inheritDoc}
     * @param values the values of when the status will end
     */
    public EndStatus(
        final ConditionBehaviour behaviour,
        final Optional<JsonObject> values
    ) {
        super(behaviour);
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Values cannot be an empty Optional.");
        }
        this.countDown = values.get().get("countdown").getAsInt();
        this.removedPercentage = values.get().get("percentage").getAsInt();
        this.increasing = values.get().get("increasing").getAsBoolean();
        this.toRemove = false;
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link EndStatus} to take values from
     */
    public EndStatus(final ConditionBehaviour behaviour, final EndStatus toCopy) {
        super(behaviour);
        this.toRemove = toCopy.toRemove;
        this.countDown = toCopy.countDown;
        this.removedPercentage = toCopy.removedPercentage;
        this.increasing = toCopy.increasing;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {
        if (!this.toRemove) {
            if (countDown < 0) {
                toRemove = !RandomUtility.check((float) removedPercentage / 100);
                if (increasing) {
                    removedPercentage += removedPercentage;
                }
            } else {
                if (countDown == 1) {
                    toRemove = true;
                    countDown--;
                } else {
                    countDown--;
                }
            }
        } else {
            pokemon.getConditionComponent().addToRemoveList(this.getID());
        }
        return super.trigger(pokemon, opponent);
    }
}
