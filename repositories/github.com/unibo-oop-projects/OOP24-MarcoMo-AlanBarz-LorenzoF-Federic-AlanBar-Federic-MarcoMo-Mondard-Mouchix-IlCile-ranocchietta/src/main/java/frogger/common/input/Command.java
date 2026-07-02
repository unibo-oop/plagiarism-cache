package frogger.common.input;

import frogger.model.interfaces.Game;

/**
 * Represents a command that can be executed in the game context.
 * Implementations define specific actions to be performed on the game.
 */
public interface Command {
    /**
     * Executes the command on the given game instance.
     *
     * @param game the game on which to execute the command
     */
    void execute(Game game);
}
