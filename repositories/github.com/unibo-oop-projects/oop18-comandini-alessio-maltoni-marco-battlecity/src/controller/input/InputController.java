package controller.input;

/**
 * Interface for a generic input controller.
 */
public interface InputController {

    /**
     * Method that returns true if move up is set, false otherwise.
     * 
     * @return true if move up is set, false otherwise.
     */
    boolean isMoveUp();

    /**
     * Method that returns true if move right is set, false otherwise.
     * 
     * @return true if move right is set, false otherwise.
     */
    boolean isMoveRight();

    /**
     * Method that returns true if move down is set, false otherwise.
     * 
     * @return true if move down is set, false otherwise.
     */
    boolean isMoveDown();

    /**
     * Method that returns true if move left is set, false otherwise.
     * 
     * @return true if move left is set, false otherwise.
     */
    boolean isMoveLeft();

    /**
     * Method that returns true if fire is set, false otherwise.
     * 
     * @return true if fire is set, false otherwise.
     */
    boolean isFire();

}
