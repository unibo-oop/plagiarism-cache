package it.unibo.isaccoop.controller.input;
/**
 * InputController interface to get input state.
 */
public interface InputController {

    /**
     * Get if up input has been fired.
     * @return true if direction is UP
     */
    boolean isUp();

    /**
     * Get if down input has been fired.
     * @return true if direction is DOWN.
     */
    boolean isDown();

    /**
     * Get if left input has been fired.
     * @return true if direction is LEFT.
     */
    boolean isLeft();

    /**
     * Get if right input has been fired.
     * @return true if direction is RIGHT.
     */
    boolean isRight();
}
