package view.scene;

import static view.utility.SceneType.LEADERBOARD;

import javafx.event.Event;
import view.ViewManagerImpl;

/**
 * Class that represent leader board view.
 *
 */
public class LeaderboardScene extends AbstractGenericScene {

    /**
     * Constructor for this scene.
     */
    public LeaderboardScene() {
        super(LEADERBOARD);
    }

    /**
     * Exit status for this scene.
     */
    @Override
    public void exitStatus() {
        ViewManagerImpl.get().pop();
    }

    /**
     * Events that can occur in this scene.
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
