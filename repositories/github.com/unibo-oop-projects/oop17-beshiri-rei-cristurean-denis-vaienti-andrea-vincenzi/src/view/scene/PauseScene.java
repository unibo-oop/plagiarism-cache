package view.scene;

import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import view.ViewImpl;
import view.ViewManagerImpl;

import static view.utility.SceneType.PAUSE;

import controller.event.KeyEventImpl;
import controller.event.KeyType;

/**
 * Class for a pause scene.
 *
 */
public class PauseScene extends AbstractGenericScene {

    /**
     * Constructor for a pause scene.
     *
     */
    public PauseScene() {
        super(PAUSE);
    }

    /**
     * Exit status for a specific scene.
     */
    @Override
    public void exitStatus() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Check events that occurs in this scene.
     */
    @Override
    public void checkSceneHandler(final Event e) { 
        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            ViewImpl.get().notifyEvent(new KeyEventImpl(((KeyEvent) e).getCode(),
                    this.getSceneType(), KeyType.KEY_PRESSED));
        }
    }

    /**
     * Used when focus up.
     */
    @Override
    public void notifyFocusUp() { 
        getSceneController().setText();
    }

    /**
     * Used when focus down.
     */
    @Override
    public void notifyFocusDown() { }
}
