package utilities.test.demo;

import controller.ControllerImpl;

/**
 * An alternative main that can be used to launch a previous saved game.
 * Andrea Serafini.
 *
 */
public final class LoadGameLaucher {

    private LoadGameLaucher() {
        // not called
    }

    /**
     *
     * @param args
     *            arguments
     */
    public static void main(final String[] args) {

        ControllerImpl.getLog().reopenBoardView();
    }
}
