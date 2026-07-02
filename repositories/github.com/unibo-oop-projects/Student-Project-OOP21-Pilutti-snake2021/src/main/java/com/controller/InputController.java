package main.java.com.controller;

/**
 * Interface that models the controller for user inputs.
 */
public interface InputController {

    /**
     * Notifies the given {@link Command}.
     * @param cmd the command to be notified
     */
    void notifyCommand(Command cmd);
}
