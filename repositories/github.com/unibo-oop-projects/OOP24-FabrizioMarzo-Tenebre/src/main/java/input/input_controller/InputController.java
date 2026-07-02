package input.input_controller;

/**
 * Interface for handling and interpreting user input, particularly movement
 * commands.
 * 
 * <p>
 * This interface abstracts the input logic, providing methods to notify
 * movement actions and retrieve the current direction of movement.
 * </p>
 */
public interface InputController {

    /**
     * Notifies the controller of a movement input based on a key code.
     *
     * @param inputIdentifier the key code corresponding to a movement direction
     *                        (e.g., arrow keys or custom mappings)
     */
    public void notifyInput(final int inputIdentifier);

    /**
     * Notifies the controller that there is no current movement input.
     */
    public void notifyNoInput();

    /**
     * Returns the current movement direction based on the latest input.
     *
     * @return a {@link KeyCodes} enum value representing the current direction,
     *         or {@code Directions.NONE} if no input is active
     */
    public int getInputCode();
}
