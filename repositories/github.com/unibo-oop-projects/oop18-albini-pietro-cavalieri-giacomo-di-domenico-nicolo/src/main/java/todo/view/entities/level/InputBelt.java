package todo.view.entities.level;

import java.util.Optional;

/**
 * This interface represents an input {@link Belt}. From this one, the player
 * will receive the puzzle input {@link ValueBox}.
 */
public interface InputBelt extends Belt {
    /**
     * Get the first {@link ValueBox} in the input belt, if present, and set the
     * belt so that the player can take the next box in queue.
     *
     * @return the first {@link ValueBox}
     */
    Optional<ValueBox> poll();

    /**
     * Get the first {@link ValueBox} in the input belt, if present, without taking
     * it and moving to the next box.
     * 
     * @return the first {@link ValueBox}
     */
    Optional<ValueBox> peek();
}
