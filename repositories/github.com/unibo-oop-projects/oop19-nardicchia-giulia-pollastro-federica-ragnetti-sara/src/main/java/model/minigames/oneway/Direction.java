package model.minigames.oneway;

import java.util.Random;
import utility.Pair;

/**
 * Enumeration for the possible direction.
 * 
 *
 */
public enum Direction {

    /**
     * Left direction.
     * 
     */
    LEFT("/images/left.png"),

    /**
     * Right direction.
     * 
     */
    RIGHT("/images/right.png"),

    /**
     * Up direction.
     * 
     */
    UP("/images/up.png"),

    /**
     * Down direction.
     * 
     */
    DOWN("/images/down.png");

    private static Pair<Integer, Integer> step;
    private String imgPath;

    /**
     * 
     * @param imgPath
     */
    Direction(final String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * 
     * @return the path of the image
     */
    public String getPath() {
        return this.imgPath;
    }

    /**
     * Compute the step associated to the specified direction.
     * @param direction
     *               the direction to compute
     * @return the computed step 
     */
    public static Pair<Integer, Integer> delta(final Direction direction) {
        switch (direction) {
        case LEFT:
            step = new Pair<>(0, -1);
            break;
        case RIGHT:
            step = new Pair<>(0, 1);
            break;
        case UP:
            step = new Pair<>(-1, 0);
            break;
        case DOWN:
            step = new Pair<>(1, 0);
            break;
        default:
            break;
        }
        return step;
    }

    /**
     * Generate a Direction randomly.
     * @return random Direction
     */
    public static Direction generateStep() {
        final int pick = new Random().nextInt(Direction.values().length);
        return Direction.values()[pick];
    }

}
