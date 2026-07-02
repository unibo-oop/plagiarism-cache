package zombietsunami;

import zombietsunami.api.Controller;
import zombietsunami.controller.ControllerImpl;

/**
 * This class allows you to launch the game.
 */
public final class LaunchGame {

    private LaunchGame() {
    }

    /**
     * This method is the main of the project and launch the whole game by creating
     * a new Controller and setting the Model and the View.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final Controller controller = new ControllerImpl();
        controller.setModel();
        controller.setView();
    }
}
