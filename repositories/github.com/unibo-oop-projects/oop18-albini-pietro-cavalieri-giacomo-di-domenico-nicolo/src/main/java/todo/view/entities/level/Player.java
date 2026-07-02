package todo.view.entities.level;

import java.util.Optional;

import todo.view.entities.TaskAcceptingEntity;
import todo.view.entities.tasks.SequentialLoopableTaskManager;

/**
 * This interface represents a player that has a
 * {@link SequentialLoopableTaskManager} that can accept a
 * {@link todo.view.entities.tasks.LoopableTask}. The player has a hand, that
 * can hold a {@link todo.entities.ValueBox}.
 */
public interface Player extends TaskAcceptingEntity<SequentialLoopableTaskManager> {
    /**
     * @return the {@link ValueBox} in the player's hand, if present
     */
    Optional<ValueBox> getHand();

    /**
     * Set the player's hand to hold the specified {@link ValueBox}.
     *
     * @param valueBox is the {@link ValueBox} to be held by the player's hand
     */
    void setHand(ValueBox valueBox);

    /**
     * Discard the player's hand. If the hand is empty, this method does nothing.
     */
    void discardHand();

    /**
     * @return the player's current height
     */
    float getHeight();

    /**
     * Set the player's height above ground. The player's shadow will remain still,
     * while the player will go up and down.
     *
     * @param height is the new height of the player
     */
    void setHeight(float height);
}
