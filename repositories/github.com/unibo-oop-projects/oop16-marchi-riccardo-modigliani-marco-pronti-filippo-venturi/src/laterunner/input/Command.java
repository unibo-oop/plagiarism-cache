package laterunner.input;

import laterunner.model.world.GameState;

/**
 * Command class.
 */
public interface Command {

    /**
     * Executes a command.
     * 
     * @param gameState
     *          game coordinator
     */
    void execute(GameState gameState);
}
