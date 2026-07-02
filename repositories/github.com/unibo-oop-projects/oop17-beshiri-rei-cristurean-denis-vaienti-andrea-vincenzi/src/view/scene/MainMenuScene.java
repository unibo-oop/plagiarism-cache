package view.scene;

import static view.utility.SceneType.MENU;

import javafx.event.Event;

/**
 * Class that represent menu scene.
 *
 */
public class MainMenuScene extends AbstractGenericScene {

    /**
     * Constructor for menu scene.
     */
    public MainMenuScene() {
        super(MENU);
    }

    /**
     * Exit status for the specific scene.
     */
    @Override
    public void exitStatus() { }

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
