package frogger.common.input;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import frogger.model.interfaces.Game;

/**
 * Implementation of the InputController interface.
 * Manages a queue of input commands and processes them for the game.
 */
public class InputControllerImpl implements InputController {

    /** Queue to store input commands. */
    private final BlockingQueue<Command> inputQueue = new ArrayBlockingQueue<>(100);

    /**
     * {@inheritDoc}
     * Adds the given command to the input queue.
     *
     * @param input the command to notify
     */
    @Override
    public void notifyCommand(final Command input) {
        this.inputQueue.add(input);
    }

    /**
     * {@inheritDoc}
     * Processes the next command in the input queue, if any, by executing it on the given game.
     *
     * @param game the game instance on which to execute the command
     */
    @Override
    public void processInput(final Game game) {
        final Command input = inputQueue.poll();
        if (input != null) {
            input.execute(game);
        }
    }

}
