package it.unibo.michelito.controller.maincontroller.api;

/**
 * Interface for every controller.
 * Controllers need to provide a way to request the termination of the application.
 */
public interface Controller {
    /**
     * Requests the termination of the application.
     * The actual shutdown process may be handled asynchronously.
     */
    void quit();

    /**
     * Requests management of a exception.
     *
     * @param exception the exception to manage.
     */
    void handleException(Exception exception);
}

