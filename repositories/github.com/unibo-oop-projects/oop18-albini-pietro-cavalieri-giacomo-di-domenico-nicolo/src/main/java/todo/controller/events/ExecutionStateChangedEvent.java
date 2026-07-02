package todo.controller.events;

public enum ExecutionStateChangedEvent implements Event {
    PLAY("The program is now playing"), STEP("The program is now stepping"), STOP("The program is now stopped");

    private String message;

    ExecutionStateChangedEvent(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
