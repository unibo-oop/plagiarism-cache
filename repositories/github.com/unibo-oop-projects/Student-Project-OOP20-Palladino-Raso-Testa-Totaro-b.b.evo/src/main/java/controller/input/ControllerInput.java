package controller.input;

/**
 * Interface that helps to check if a user is using input devices.
 */
public interface ControllerInput {

    /**
     * @return true or false if the user is trying to move left or not.
     */
    boolean canMoveLeft();

    /**
     * @return true or false if the user is trying to move right or not.
     */
    boolean canMoveRight();

    /**
     * @param cond  The moveRight flag is set based on the "cond" parameter. (If the user tries to move right)
     */
    void setMoveRight(boolean cond);

    /**
     * @param cond  The moveLeft flag is set based on the "cond" parameter. (If the user tries to move left)
     */
    void setMoveLeft(boolean cond);
}
