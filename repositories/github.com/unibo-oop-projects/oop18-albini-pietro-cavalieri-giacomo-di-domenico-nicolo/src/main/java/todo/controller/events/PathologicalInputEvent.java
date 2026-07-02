package todo.controller.events;

import java.util.List;

import todo.vm.Value;

/**
 * An event thrown whenever a program written by the user solves the level with
 * a specific input but fails with other inputs, an input that caused the
 * problem to fail is available through the method {@link #getInput()}.
 */
public class PathologicalInputEvent implements Event {
    private final List<Value> pathologicalInput;

    public PathologicalInputEvent(final List<Value> input) {
        this.pathologicalInput = input;
    }

    @Override
    public String getMessage() {
        return "The program doesn't work with all the inputs!";
    }

    public List<Value> getInput() {
        return this.pathologicalInput;
    }
}
