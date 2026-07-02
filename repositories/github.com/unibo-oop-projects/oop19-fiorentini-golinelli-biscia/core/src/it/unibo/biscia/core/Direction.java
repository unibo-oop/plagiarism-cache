package it.unibo.biscia.core;

/**
 * Direction of movement.
 *
 */
public enum Direction {
    /**
     * up direction.
     */
    UP(0, -1),
    /**
     * down direction.
     */
    DOWN(0, 1),
    /**
     * left direction.
     */
    LEFT(-1, 0),
    /**
     * right direction.
     */
    RIGHT(1, 0);

    private final int stepCol;
    private final int stepRow;

    Direction(final int stepCol, final int stepRow) {
        this.stepCol = stepCol;
        this.stepRow = stepRow;
    }

    /**
     * entity of step on columns for direction.
     * 
     * @return integer
     */
    public int getStepCol() {
        return this.stepCol;
    }

    /**
     * entity of step on row for direction.
     * 
     * @return integer
     */
    public int getStepRow() {
        return this.stepRow;
    }

    @Override
    public String toString() {
        switch (this) {
        case DOWN:
            return "D";
        case UP:
            return "U";
        case LEFT:
            return "L";
        case RIGHT:
            return "R";
        default:
            break;
        }
        return "";
    }

}
