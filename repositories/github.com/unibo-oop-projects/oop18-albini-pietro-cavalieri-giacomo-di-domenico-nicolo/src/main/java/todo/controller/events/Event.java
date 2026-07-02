package todo.controller.events;

/**
 * This interface represents a simple event that can take place during a level
 * of the game.
 */
public interface Event {
    /**
     * @return the message associated with this event
     */
    String getMessage();
}
