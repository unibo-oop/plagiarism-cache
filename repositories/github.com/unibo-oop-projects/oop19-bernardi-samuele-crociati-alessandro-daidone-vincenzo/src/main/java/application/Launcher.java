package application;

import controller.Controller;
import controller.ControllerImpl;
import view.ViewImpl;

/**
 * This class represents the Launcher of the system, to bypass JAVA 11 modules
 * constraints.
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * @param args
     *            unused
     */
    public static void main(final String[] args) {
        final Controller c = ControllerImpl.getInstance();
        c.setView(new ViewImpl());
        c.loadModel();
        c.startGameLoop();
    }
}
