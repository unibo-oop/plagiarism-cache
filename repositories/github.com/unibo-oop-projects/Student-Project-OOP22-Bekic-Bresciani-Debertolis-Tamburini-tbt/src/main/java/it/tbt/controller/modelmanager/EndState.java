package it.tbt.controller.modelmanager;

/**
 * The {@code EndState} interface represents the end state of a model in the application's controller.
 * It extends the {@link ModelState} interface.
 * <p>
 * This interface provides methods for retrieving a message associated with the end state and triggering the main menu.
 * </p>
 */
public interface EndState extends ModelState {

    /**
     * Returns the message associated with the end state.
     *
     * @return The message associated with the end state.
     */
    String getMessage();

    /**
     * Triggers the exit from the game.
     */
    void triggerExit();
}
