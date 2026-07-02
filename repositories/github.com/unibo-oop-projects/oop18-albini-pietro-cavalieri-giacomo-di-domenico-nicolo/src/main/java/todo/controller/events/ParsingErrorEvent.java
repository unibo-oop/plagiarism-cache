package todo.controller.events;

import todo.vm.parser.ParserException;

public class ParsingErrorEvent implements Event {
    private final ParserException exc;

    public ParsingErrorEvent(final ParserException exc) {
        this.exc = exc;
    }

    public ParserException getException() {
        return this.exc;
    }

    @Override
    public String getMessage() {
        return "An error occurred while parsing the program: " + this.exc.getMessage();
    }
}
