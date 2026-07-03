package controller.input;

import java.util.List;
import java.util.Optional;

/**
 * 
 * Represent the keys currently pressed by the user.
 *
 */
public interface KeyInput {

    /**
     * 
     * Used for to add the command when the user press a key.
     * 
     * @param command
     *          the command equivalent to the key that pressed by the user.
     * @return if the key was not previously pressed, return the command.
     */
    List<Optional<Command>> addCommand(Command command);

    /**
     * 
     * Used for to remove the command when the user release a key.
     * 
     * @param command
     *          the command equivalent to key that released by the user.
     */
    void deleteCommand(Command command);
 
    /**
     * Used to get a key that the user has pressed previously and which has not yet released when he releases 
     * a movement key.
     * 
     * @param command
     *          the command equivalent to key that released by the user.
     * @return if there is still a movement key pressed, return the command equivalent at the key pressed.
     */
    List<Optional<Command>> getCommand(Command command);

    /**
     * Clear the key pressed.
     */
    void clear();
}
