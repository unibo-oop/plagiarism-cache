package view.scene;

import static view.utility.SceneType.LOST;

import javafx.event.Event;

/**
 * Class that represent lost scene.
 *
 */
public class LostScene extends AbstractGenericScene {

    /**
     * Constructor for this class.
     */
    public LostScene() {
        super(LOST);
    }

    /**
     * Exit status for this scene.
     */
    @Override
    public void exitStatus() { }

    /**
     * Method used to check if particular type of events occur in this scene.
     */
    @Override
    public void checkSceneHandler(final Event e) { }

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
