package migglione;

import migglione.controller.impl.ControllerImpl;

/**
 * Class used to start the application.
 */
public final class MigglioneStart {

    private MigglioneStart() {
    }

    /**
     * Functional main to start the application by initializing the Controller class.
     * 
     * @param args is functional
     */
    public static void main(final String[] args) {
        new ControllerImpl();
    }
}
