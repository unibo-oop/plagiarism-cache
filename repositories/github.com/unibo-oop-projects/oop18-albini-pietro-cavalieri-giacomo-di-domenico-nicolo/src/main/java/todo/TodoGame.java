package todo;

import java.io.IOException;

import com.badlogic.gdx.Game;

import todo.controller.Controller;
import todo.controller.ControllerImpl;
import todo.controller.clipboard.SystemClipboard;
import todo.view.menu.MenuObserverImpl;

public class TodoGame extends Game {
    private final Controller gameController;

    public TodoGame() throws IOException {
        this.gameController = new ControllerImpl(SystemClipboard.getInstance());
    }

    @Override
    public void create() {
        new MenuObserverImpl(this, this.gameController).openMenuScreen();
    }
}
