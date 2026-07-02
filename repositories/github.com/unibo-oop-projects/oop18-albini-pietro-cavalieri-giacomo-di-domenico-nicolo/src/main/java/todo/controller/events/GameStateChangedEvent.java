package todo.controller.events;

public class GameStateChangedEvent implements Event {
    @Override
    public String getMessage() {
        return "The game state has changed";
    }
}
