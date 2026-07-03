
package application;

/**
 * Start application class with main method.
 */
public final class Application {

    private final Controller controller;

    private Application() {
        this.controller = Controller.getController();
    }

    /**
     * Application initialization.
     */
    private void startApplication(final String... args) {
        this.controller.init(args);
    }

    /**
     * Main method of the program.
     * 
     * @param args
     *            default parameters.
     */
    public static void main(final String[] args) {
        final Application application = new Application();
        application.startApplication(args);
    }
}