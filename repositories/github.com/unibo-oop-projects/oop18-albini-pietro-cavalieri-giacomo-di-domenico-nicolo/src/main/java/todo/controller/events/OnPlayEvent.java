package todo.controller.events;

public class OnPlayEvent implements Event {
    @Override
    public String getMessage() {
        return "Program starts to play";
    }
}
