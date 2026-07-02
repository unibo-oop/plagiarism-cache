package view.scene;

import static view.utility.SceneType.HELP;

import javafx.event.Event;
import view.ViewManagerImpl;

/**
 * Class that represent help game scene.
 *
 */
public class HelpScene extends AbstractGenericScene {

    /**
     * Constructor for a help scene.
     */
    public HelpScene() {
        super(HELP);
    }

    /**
     * Exit status for the specific scene.
     */
    @Override
    public void exitStatus() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Method used to check if occurs events in this type of scene.
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
