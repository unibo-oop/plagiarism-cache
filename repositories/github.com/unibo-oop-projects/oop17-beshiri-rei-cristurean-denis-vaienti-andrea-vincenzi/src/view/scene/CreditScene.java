package view.scene;

import static view.utility.SceneType.CREDITS;
import javafx.event.Event;
import view.ViewManagerImpl;

/**
 * Class that represent Credit scene.
 *
 */
public class CreditScene extends AbstractGenericScene {

    /**
     * Constructor for credit scene.
     */
    public CreditScene() {
        super(CREDITS);
    }

    /**
     * Exit status for this specific scene.
     */
    @Override
    public void exitStatus() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Method used to check if occur events in this particular scene.
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
