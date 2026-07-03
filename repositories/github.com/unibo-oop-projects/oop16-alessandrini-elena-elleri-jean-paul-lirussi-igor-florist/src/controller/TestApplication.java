package controller;

/**
 * A class that contains the {@link TestApplication #main(String[])} of the application.
 *
 */
public final class TestApplication {
    //private TestApplication() { }
    /**
     * The Main that start the application.
     * 
     * @param args
     *            A simple {@link String}.
     * @throws Exception
     *            The {@link Exception} will be managed in the code.
     */
    public static void main(final String[] args) throws Exception {
        MainControllerImpl.getSingleton().startApp();
    }
}
