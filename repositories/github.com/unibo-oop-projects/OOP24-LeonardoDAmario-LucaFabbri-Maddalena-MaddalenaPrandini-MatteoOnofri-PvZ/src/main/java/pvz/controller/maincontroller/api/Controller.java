package pvz.controller.maincontroller.api;

/**
 * Interface for controllers in the application.
 * Provides a method to handle exceptions at the controller level.
 */
public interface Controller {

    /**
     * Handles an exception thrown during application execution.
     *
     * @param exception the exception to handle
     */
    void handleException(Exception exception);

    /**
     * Requests the orderly shutdown of the application and releases all resources.
     * <p>
     * Implementations should ensure that all relevant processes and user interfaces
     * are properly closed before termination.
     */
    void quit();

}
