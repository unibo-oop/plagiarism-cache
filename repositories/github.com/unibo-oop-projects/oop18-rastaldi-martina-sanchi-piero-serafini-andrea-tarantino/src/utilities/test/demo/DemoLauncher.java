package utilities.test.demo;

import controller.ControllerImpl;
import model.players.Player;
import utilities.Colors;

/**
 * An alternative main that can be used to launch a test game.
 * Andrea Serafini.
 *
 */
public final class DemoLauncher {

    private DemoLauncher() {
        // not called
    }

    /**
     *
     * @param args
     *            arguments
     */
    public static void main(final String[] args) {

        ControllerImpl.getLog().setEdgeJumpingMode(true);
        ControllerImpl.getLog().addPlayer(new Player("Andrea", Colors.Blue));
        ControllerImpl.getLog().addPlayer(new Player("Piero", Colors.Red));

        ControllerImpl.getLog().openDemoBoardView();
        // ControllerImpl.getLog().openBoardView();

    }

}
