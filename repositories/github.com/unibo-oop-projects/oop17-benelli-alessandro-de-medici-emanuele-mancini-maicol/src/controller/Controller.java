package controller;

/**
 * ControllerImpl interface.
 *
 */
public interface Controller {

    /**
     * Closes the application.
     */
    void quit();

    /**
     * Returns true if the operating system is Unix, false otherwise.
     * 
     * @return unix
     */
    boolean isUnix();
}
