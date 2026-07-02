package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * A {@link Move} with a {@link Condition} has a predetermined chance to apply
 * an effect on the {@link Pokemon} opponent.
 */
public final class Condition extends AbstractBehaviourDecorator {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String conditionId;
    private final Float chance;
    private final boolean self;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Condition(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        if (values.isEmpty()) {
            throw new IllegalArgumentException("values cannot be an empty Optional.");
        }

        final var cond = values.get();

        this.conditionId = cond.get("id").getAsString();
        this.chance = cond.get("chance").getAsFloat();
        this.self = cond.get("self").getAsBoolean();
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Condition} to take the values from.
     */
    public Condition(final MoveBehaviour base, final Condition toCopy) {
        super(base);

        this.chance = toCopy.chance;
        this.conditionId = toCopy.conditionId;
        this.self = toCopy.self;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {

        if (super.apply(user, opponent)) {
            if (RandomUtility.check(this.chance)) {
                if (this.self) {
                    user.getConditionComponent()
                    .applyCondition(this.conditionId);
                } else {
                    opponent.getConditionComponent()
                    .applyCondition(this.conditionId);
                }
            }
            return true;
        }
        return false;
    }

}
