package view.util;

import java.util.Objects;

import util.SceneControllerLoaderProxy.FxmlID;
import view.state.GameState;
import view.state.MenuState;
import view.state.PauseState;
import view.state.WindowState;

/**
 * Implementation of the interface StateFactory.
 */
public final class StateFactoryImpl implements StateFactory {

    private static StateFactory singleton;

    private StateFactoryImpl() {
    }

    @Override
    public WindowState createCreditsState() {
        return new WindowState(FxmlID.CREDITS);
    }

    @Override
    public WindowState createGameOverState() {
        return new WindowState(FxmlID.GAME_OVER);
    }

    @Override
    public GameState createGameState() {
        return new GameState();
    }

    @Override
    public WindowState createHelpState() {
        return new WindowState(FxmlID.HELP);
    }

    @Override
    public MenuState createMenuState() {
        return new MenuState();
    }

    @Override
    public WindowState createOptionsState() {
        return new WindowState(FxmlID.OPTIONS);
    }

    @Override
    public WindowState createPauseState() {
        return new PauseState();
    }

    /**
     * Getter of the singleton of this class.
     * 
     * @return an initialized object of type StateFactory
     */
    public static StateFactory get() {
        if (Objects.isNull(singleton)) {
            singleton = new StateFactoryImpl();
        }
        return singleton;
    }
}
