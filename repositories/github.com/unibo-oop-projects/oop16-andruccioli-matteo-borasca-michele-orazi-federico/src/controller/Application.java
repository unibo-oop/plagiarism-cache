package controller;

import view.ViewImpl;

/**
 * Contains the main method.
 */
public final class Application {

    private Application() { }

    /**
     * Main method.
     * @param args
     *            not used.
     */
    public static void main(final String[] args) {
        ViewImpl.create(ControllerFactory.getSurrogateController());
        ControllerFactory.getController().registerView(ViewImpl.getIstance());
        ViewImpl.getIstance().startView();
    }

}
