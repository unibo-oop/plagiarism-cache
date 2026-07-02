package model.inanimated;

/**
 * 
 * Interface that represents buttons in the world.
 *
 */
public interface Button extends Inanimated {

    /**
     * Check the status of the button.
     * @return return the status of the button. Return true if the button is
     *         pressed, false otherwise.
     */
    boolean isPressed();

    /**
     * Setter for status of the button.
     * @param pressed
     *            The state of the button.
     */
    void setPressed(boolean pressed);
}
