package todo.controller.events;

import java.util.Objects;

/**
 * An ExecutionErrorEvent notifies the happening of an error in the VM that
 * makes it impossible to execute the code any further.
 */
public class ExecutionErrorEvent implements Event {
    private final String message;

    public ExecutionErrorEvent(final String message) {
        this.message = Objects.requireNonNull(message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
