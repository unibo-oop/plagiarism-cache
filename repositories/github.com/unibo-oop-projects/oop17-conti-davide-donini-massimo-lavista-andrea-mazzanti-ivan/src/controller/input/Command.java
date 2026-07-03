package controller.input;

import model.Model;

/**
 * Represent the command.
 */
public interface Command {

    /**
     * @param model
     *          Model of the application.
     */
    void execute(Model model);

    /**
     * 
     * @return the type of the command.
     */
    CommandType getCommandType();

}
