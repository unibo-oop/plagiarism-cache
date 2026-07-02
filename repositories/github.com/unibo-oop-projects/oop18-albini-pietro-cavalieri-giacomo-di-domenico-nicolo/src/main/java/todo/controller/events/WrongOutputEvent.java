package todo.controller.events;

public class WrongOutputEvent implements Event {

    @Override
    public String getMessage() {
        return "The output is not as expected!";
    }
}
