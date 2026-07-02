package input;

import physics.Direction;

/**
 * This interface models the IaInputComponent, that manages the Enemies' input.
 */
public interface InputComponent {

    /**
     * Adds a new command to the queue.
     * @param newCommand : new action to perform.
     */
    void addCommand(Command newCommand);

    /**
     * Creates a new Command.
     * @param direction : direction of the command.
     * @param distance : distance that will be covered by the entity.
     */
    void createCommand(Direction direction, double distance);
}
