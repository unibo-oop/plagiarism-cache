package frogger.common.input;

import frogger.model.interfaces.Game;

/**
 * Interface for handling input commands in the game.
 * Implementations manage the reception and processing of input commands.
 */
public interface InputController {

    /**
     * Processes the next input command, if any, and executes it on the given game instance.
     *
     * @param game the game instance on which to execute the command
     */
    void processInput(Game game);

    /**
     * Notifies the controller of a new input command to be processed.
     *
     * @param input the command to notify
     */
    void notifyCommand(Command input);
}
