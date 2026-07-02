package model.physics;

/**
 * method that makes bubble go up.
 */
public interface MovementManager {

    /**
     * method that makes character (both hero and enemies) jump.
     * @return true if character is still jumping, false otherwise.
     */
    boolean characterJump();

    /**
     * method that makes object fall down.
     */
    void fall();

    /**
     * method that undoes fall down.
     * @param objectY needed to make the character stay on level with the floor.
     */
    void fallUndo(int objectY);

    /**
     * method that makes character move up.
     */
    void characterMoveUp();

    /**
     * method that makes character move left.
     */
    void characterMoveLeft();

    /**
     * method that makes character move right.
     */
    void characterMoveRight();

    /**
     * method that makes bubble move left.
     */
    void bubbleMoveLeft();

    /**
     * method that makes bubble move left.
     */
    void bubbleMoveRight();

    /**
     * method that makes bubble go up.
     */
    void bubbleMoveUp();

    /**
     * method that manages movement that makes the bubble float near the ceiling
     * while it waits to be destroyed.
     */
    void floatUpDown();

}
