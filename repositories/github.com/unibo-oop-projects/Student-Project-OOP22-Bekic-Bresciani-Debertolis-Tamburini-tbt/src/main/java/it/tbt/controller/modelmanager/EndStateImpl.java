package it.tbt.controller.modelmanager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.statechange.StateTrigger;

/**
 * The {@code EndStateImpl} class represents an implementation of the {@link EndState} interface
 * and also implements the {@link StateTrigger} interface.
 * <p>
 * This class provides functionality for an end state in the application's controller.
 * It holds a message associated with the end state and allows triggering the main menu.
 * </p>
 */
public class EndStateImpl implements EndState {

    private final String message;

    /**
     * Creates a new {@code EndStateImpl} object with the specified message.
     *
     * @param message The message associated with the end state.
     */
    public EndStateImpl(final String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }


    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
            value = "Dm",
            justification = "EndState needs to kill the application"
    )
    @Override
    public void triggerExit() {
        System.exit(0);
    }
}
