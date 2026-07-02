package todo.view.menu;

import java.util.Objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;

import todo.controller.Controller;
import todo.controller.RoomControllerImpl;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.screens.RoomScreenImpl;

public class MenuObserverImpl implements MenuObserver {
    private final Controller gameController;
    private final SettingsScreen settings;
    private final MenuScreen menu;
    private final Game game;

    public MenuObserverImpl(final Game game, final Controller gameController) {
        this.game = Objects.requireNonNull(game);
        this.gameController = Objects.requireNonNull(gameController);
        this.settings = new SettingsScreen();
        this.settings.setObserver(this);
        this.menu = new MenuScreen(gameController.getAvailableLevels());
        this.menu.setObserver(this);
    }

    @Override
    public void openLevelScreen(final String title) {
        this.game.setScreen(new RoomScreenImpl(new RoomControllerImpl(this.gameController.loadLevel(title)), this));
    }

    @Override
    public void openSettingsScreen() {
        this.game.setScreen(this.settings);
        Gdx.input.setInputProcessor(this.settings.getStage());
    }

    @Override
    public void openMenuScreen() {
        this.game.setScreen(this.menu);
        Gdx.input.setInputProcessor(this.menu.getStage());
    }

    @Override
    public void changeDisplayMode(final DisplayMode mode) {
        ResolutionManagerImpl.getInstance().update().setDisplayMode(mode).apply();

    }

    @Override
    public void changeWindowOrFullscreen(final boolean setFullscreen) {
        ResolutionManagerImpl.getInstance().update().setFullscreen(setFullscreen).apply();
    }

    @Override
    public void close() {
        Gdx.app.exit();
    }
}
