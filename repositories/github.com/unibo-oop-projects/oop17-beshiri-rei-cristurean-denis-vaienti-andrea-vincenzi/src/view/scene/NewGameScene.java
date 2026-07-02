package view.scene;

import static view.utility.SceneType.NEWGAME;

import javafx.event.Event;
import view.ViewManagerImpl;

/**
 * New game scene.
 * 
 */
public class NewGameScene extends AbstractGenericScene {
    /**
     * Constructor for a new game scene.
     * 
     */
    public NewGameScene() {
        super(NEWGAME);
    }

    /**
     * Exit status for this scene.
     */
    @Override
    public void exitStatus() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Check specific type of events.
     */
    @Override
    public void checkSceneHandler(final Event e) { }

    /**
     * Used when focus up.
     */
    @Override
    public void notifyFocusUp() { }

    /**
     * Used when focus down.
     */
    @Override
    public void notifyFocusDown() { }
}
