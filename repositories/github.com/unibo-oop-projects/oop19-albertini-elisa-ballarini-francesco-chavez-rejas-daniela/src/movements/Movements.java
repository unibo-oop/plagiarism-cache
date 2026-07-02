package movements;

/**
 * This class implements all the methods to move safety all the kind of pieces
 * (the classic ones and the custom ones). In particular, the piece rotates
 * around its center in two different ways: clockwise and counterclockwise. It
 * also translates in two direction: to the left and to the right and drops
 * down.
 */
public interface Movements {

    /**
     * Rotates the piece clockwise.
     */
    void rotateClockwise();

    /**
     * Rotates the piece counterclockwise.
     */
    void rotateCounterclockwise();

    /**
     * Shifts left the piece.
     */
    void moveLeft();

    /**
     * Shifts right the piece.
     */
    void moveRight();

    /**
     * Drop down the piece.
     * 
     * @return true, if drop down is successful otherwise it returns false.
     */
    boolean dropDown();
}
