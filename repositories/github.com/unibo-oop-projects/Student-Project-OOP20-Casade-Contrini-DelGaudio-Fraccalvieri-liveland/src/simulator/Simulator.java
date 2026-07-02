package simulator;

import controller.ControllerImpl;

public final class Simulator {

    private Simulator() {
    }

    /**
     * It creates a new ControllerImpl, which sets the environment by 
     * allocating the EnvironmentControllerImpl and displaying menu dialog.
     * @param args
     *            ignored
     */
    public static void main(final String[] args) {
        final ControllerImpl controller = new ControllerImpl();
        controller.setEnvironmentController();
        controller.setView();
    }

}
