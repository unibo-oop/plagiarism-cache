package start;

import manager.ControllerManagerImpl;

/**
 * Class from which the project will start.
 */
public final class StartProject {
    private StartProject() {
    }

    /**
     * @param args 
     */
    public static void main(final String[] args) {
        new ControllerManagerImpl();
    }

}
