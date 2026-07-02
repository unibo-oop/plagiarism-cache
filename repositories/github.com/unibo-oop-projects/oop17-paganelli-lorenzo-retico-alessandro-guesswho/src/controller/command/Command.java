package controller.command;

import controller.gamecontroller.Controller;

/**
 * Modeling interface for a Command of the Controller.
 * Different commands can be created with different arguments and can be executed any time with the execute method. 
 */
public interface Command {

    /**
     * Executes the command on the given Controller.
     * @param controller
     *                  the controller
     */
    void execute(Controller controller);

}
