package ballblast.model.levels;

import org.locationtech.jts.geom.Coordinate;

import ballblast.model.Model;

/**
 * Represents the game's boundaries.
 */
public enum Boundary {
    /**
     * the left boundary.
     */
    LEFT,
    /**
     * the right boundary.
     */
    RIGHT,
    /**
     * the top boundary.
     */
    TOP,
    /**
     * the bottom boundary.
     */
    BOTTOM;

    private static final double VERTICAL_WALL_WIDTH = 5;
    private static final double VERTICAL_WALL_HEIGHT = Model.WORLD_HEIGHT;
    private static final double HORIZONTAL_WALL_WIDTH = Model.WORLD_WIDTH - 2 * VERTICAL_WALL_WIDTH;
    private static final double HORIZONTAL_WALL_HEIGHT = VERTICAL_WALL_WIDTH;

    private Coordinate position;
    private double width;
    private double height;

    static {
        LEFT.width = VERTICAL_WALL_WIDTH;
        LEFT.height = VERTICAL_WALL_HEIGHT;
        LEFT.position = new Coordinate(0, 0);

        RIGHT.width = VERTICAL_WALL_WIDTH;
        RIGHT.height = VERTICAL_WALL_HEIGHT;
        RIGHT.position = new Coordinate(Model.WORLD_WIDTH - VERTICAL_WALL_WIDTH, 0);

        TOP.width = HORIZONTAL_WALL_WIDTH;
        TOP.height = HORIZONTAL_WALL_HEIGHT;
        TOP.position = new Coordinate(VERTICAL_WALL_WIDTH, 0);

        BOTTOM.width = HORIZONTAL_WALL_WIDTH;
        BOTTOM.height = HORIZONTAL_WALL_HEIGHT;
        BOTTOM.position = new Coordinate(VERTICAL_WALL_WIDTH, Model.WORLD_HEIGHT - HORIZONTAL_WALL_HEIGHT);
    }

    /**
     * Check if is the BOTTOM Boundaries' position.
     * 
     * @param boundaryPosition the position to be checked.
     * @return true if it's the floor position, false otherwise.
     */
    public static boolean isFloor(final Coordinate boundaryPosition) {
        return boundaryPosition.equals(Boundary.BOTTOM.getPosition());
    }

    /**
     * Check if is the TOP Boundaries' position.
     * 
     * @param boundaryPosition the position to be checked.
     * @return true if it's the roof position, false otherwise.
     */
    public static boolean isRoof(final Coordinate boundaryPosition) {
        return boundaryPosition.equals(Boundary.TOP.getPosition());
    }

    /**
     * Check if is the RIGHT Boundaries' position.
     * 
     * @param boundaryPosition the position to be checked.
     * @return true if it's the right position, false otherwise.
     */
    public static boolean isRight(final Coordinate boundaryPosition) {
        return boundaryPosition.equals(Boundary.RIGHT.getPosition());
    }

    /**
     * Check if is the LEFT Boundaries' position.
     * 
     * @param boundaryPosition the position to be checked.
     * @return true if it's the left position, false otherwise.
     */
    public static boolean isLeft(final Coordinate boundaryPosition) {
        return boundaryPosition.equals(Boundary.LEFT.getPosition());
    }

    /**
     * Gets boundary's position.
     * 
     * @return the position of the boundary.
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Gets boundary's width.
     * 
     * @return the width of the boundary.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets boundary's height.
     * 
     * @return the height of the boundary.
     */
    public double getHeight() {
        return height;
    }
}
