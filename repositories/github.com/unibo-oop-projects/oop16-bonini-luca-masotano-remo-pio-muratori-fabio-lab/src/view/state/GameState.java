package view.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.KeyObserver;
import controller.Observer;
import controller.event.KeyEvent.KeyEventType;
import controller.event.KeyEventImpl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.SceneControllerLoaderProxy.FxmlID;
import view.ViewManagerImpl;
import view.controller.ControllerFXML;
import view.controller.GameSceneControllerImpl;
import view.util.StateFactoryImpl;

/**
 * The view state representing the game.
 */
public final class GameState extends PrimaryState {

    private static final String GAME_ID = "GAME";
    private GameSceneControllerImpl controller;
    private final List<Observer> gameObservers;

    /**
     * Constructor used to properly set the {@link ControllerFXML} variable used in
     * {@link PrimaryState}.
     */
    public GameState() {
        super(FxmlID.GAME);
        gameObservers = new ArrayList<>(Arrays.asList(new KeyObserver()));
        this.controller = (GameSceneControllerImpl) super.getController();
    }

    @Override
    public List<Observer> getObservers() {
        return this.gameObservers;
    }

    @Override
    public void exitState() {
        ViewManagerImpl.get().pushState(StateFactoryImpl.get().createPauseState());
    }

    @Override
    protected void specificKeyHandler(final KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            ViewManagerImpl.get()
                    .sendViewEvent(new KeyEventImpl(event.getCode().toString(), KeyEventType.KEY_PRESSED, GAME_ID));
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            ViewManagerImpl.get()
                    .sendViewEvent(new KeyEventImpl(event.getCode().toString(), KeyEventType.KEY_RELEASED, GAME_ID));
        }

        if (event.getCode().equals(KeyCode.TAB)) {
            if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                controller.resizeMap(true);
            } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                controller.resizeMap(false);
            }
        }
    }
}