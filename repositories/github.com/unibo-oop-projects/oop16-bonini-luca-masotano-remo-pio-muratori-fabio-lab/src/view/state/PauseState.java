package view.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ButtonObserver;
import controller.KeyObserver;
import controller.Observer;
import controller.event.KeyEvent.KeyEventType;
import controller.event.KeyEventImpl;
import javafx.scene.input.KeyEvent;
import util.SceneControllerLoaderProxy.FxmlID;
import view.ViewManagerImpl;

/**
 * The view state representing the game during pause.
 */
public class PauseState extends WindowState {

    private static final String PAUSE_ID = "PAUSE";
    private final List<Observer> pauseObservers = new ArrayList<>(Arrays.asList(new KeyObserver(), new ButtonObserver()));

    /**
     * Constructor used to properly set the {@link ControllerFXML} variable used in {@link WindowState}.
     */
    public PauseState() {
        super(FxmlID.PAUSE);
    }

    @Override
    protected void specificKeyHandler(final KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            ViewManagerImpl.get()
                    .sendViewEvent(new KeyEventImpl(event.getCode().toString(), KeyEventType.KEY_PRESSED, PAUSE_ID));
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            ViewManagerImpl.get()
                    .sendViewEvent(new KeyEventImpl(event.getCode().toString(), KeyEventType.KEY_RELEASED, PAUSE_ID));
        }
    }

    @Override
    public List<Observer> getObservers() {
        return this.pauseObservers;
    }
}