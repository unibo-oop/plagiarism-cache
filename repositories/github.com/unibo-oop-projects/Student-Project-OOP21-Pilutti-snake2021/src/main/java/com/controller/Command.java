package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.utility.Direction;

/**
 * Interface that models a command, central for the command pattern.
 *
 */
public interface Command {

    /**
     * Executes the command.
     * @param gameModel the game's model
     */
    void execute(Model gameModel);

    /**
     * Method used to get the {@link Direction} that this command is trying to set.
     * @return the direction
     */
    Direction getDir();
}
