package models;

/**
 * This enum contains the possible movements that a type of Character can make
 * inside the game map. It currently only contains movements of one tile in a cardinal
 * direction (could be expanded with diagonal directions or bigger movements)
 */
public enum MOVEMENT {
    /**
     * Left,right,up,down movements.
     */
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    /**
     * This is the Point2D corresponding to a movement of one tile in one direction.
     */
    public final Point2D movement;

    /**
     * This is the constructor of Movement and its constants.
     * 
     * @param offsetX Movement that must be done on the x coordinate
     * @param offsetY Movement that must be done on the y coordinate
     */
    MOVEMENT(final int offsetX, final int offsetY) {
        this.movement = new Point2D(offsetX, offsetY);
    }
}
