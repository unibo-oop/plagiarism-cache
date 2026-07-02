package todo.view.entities.level.builders;

import todo.view.entities.EntityBuilder;
import todo.view.entities.level.Player;
import todo.view.entities.level.ValueBox;

/**
 * This interface represents a builder to create {@link Player} objects.
 *
 * @param <S> is the self type
 */
public interface PlayerBuilder<S extends PlayerBuilder<? extends S>> extends EntityBuilder<S, Player> {
    /**
     * Set the player's hand to the specified value.
     *
     * @param valueBox is the {@link ValueBox} in the player's hand
     * @return the builder
     */
    S hand(ValueBox valueBox);
}
