package it.unibo.oop18.cfc.Input;

import it.unibo.oop18.cfc.Physics.Direction;

/**
 * This interface models a generic InputComponent for .
 */
public interface InputComponent {

    /**
     * Creates a new Command.
     *
     * @param direction of the command
     * @param distance covered by the entity in a second
     */
    void createDirectionCommand(Direction direction, double distance);

    /**
     * Process input entity.
     */
    void processInput();

    /**
     * Adds generic command.
     *
     * @param command to be added to the queue
     */
    void createGenericCommand(Command command);

}
