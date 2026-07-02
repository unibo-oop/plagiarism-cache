package todo.view.entities.level;

/**
 * This interface represents an output {@link Belt}. From this one, the player
 * will place the output {@link ValueBox}.
 */
public interface OutputBelt extends Belt {
    /**
     * Enqueue a {@link ValueBox} to the output belt.
     *
     * @param valueBox is the {@link ValueBox} to be enqueued
     */
    void enqueue(ValueBox valueBox);
}
