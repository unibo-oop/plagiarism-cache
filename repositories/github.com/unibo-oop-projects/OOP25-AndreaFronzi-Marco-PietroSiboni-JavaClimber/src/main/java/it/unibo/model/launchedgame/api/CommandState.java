package it.unibo.model.launchedgame.api;

import java.util.Optional;

/**
 * Interface representing a queue of commands.
 * Defines the operations for adding a command and executing command-specific
 * logic.
 * 
 * @param <X> the type of commands that this state will handle.
 */
public interface CommandState<X> {

    /**
     * Adds a command to the queue.
     * 
     * @param command the command to be added.
     */
    void addCommand(X command);

    /**
     * Polls the next command from the queue.
     * 
     * @return an Optional containing the next command if available, or an empty
     *         Optional if no commands are present
     */
    Optional<X> pollCommand();

}
