package model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.hitbox.Hitbox;
import model.hitbox.HitboxImpl;
import model.hitbox.HitboxRectangle;
import model.utils.Direction;

/**
 * 
 * An utility class that contain all the doors and walls of all rooms and
 * utility methods.
 *
 */
public final class StandardRoom {

    private static final double WIDTH = 1600;
    private static final double HEIGHT = 900;
    private static final double WALL_THICKNESS = 50;
    private static final double DOOR_HEIGHT = 50;
    private static final double DOOR_WIDTH = 200;
    private static final List<HitboxRectangle> DOORS = new ArrayList<HitboxRectangle>(
            Arrays.asList(new HitboxRectangle(WIDTH / 2 - DOOR_WIDTH / 2, 0, DOOR_WIDTH, DOOR_HEIGHT),
                    new HitboxRectangle(WIDTH / 2 - DOOR_WIDTH / 2, HEIGHT - DOOR_HEIGHT, DOOR_WIDTH, DOOR_HEIGHT),
                    new HitboxRectangle(0, HEIGHT / 2 - DOOR_WIDTH / 2, DOOR_HEIGHT, DOOR_WIDTH),
                    new HitboxRectangle(WIDTH - DOOR_HEIGHT, HEIGHT / 2 - DOOR_WIDTH / 2, DOOR_HEIGHT, DOOR_WIDTH)));
    private static final List<HitboxRectangle> WALLS = new ArrayList<HitboxRectangle>(
            Arrays.asList(new HitboxRectangle(0, 0, WIDTH, WALL_THICKNESS),
                    new HitboxRectangle(0, HEIGHT - WALL_THICKNESS, WIDTH, WALL_THICKNESS),
                    new HitboxRectangle(0, 0, WALL_THICKNESS, HEIGHT),
                    new HitboxRectangle(WIDTH - WALL_THICKNESS, 0, WALL_THICKNESS, HEIGHT)));

    private StandardRoom() {
    }

    /**
     * Get the value of the standard width for all rooms.
     * 
     * @return The standard width of all rooms.
     */
    public static double getWidth() {
        return WIDTH;
    }

    /**
     * Get the value of the standard height for all rooms.
     * 
     * @return The standard height of all rooms.
     */
    public static double getHeight() {
        return HEIGHT;
    }

    /**
     * Get the available space of a room where the player can move.
     * 
     * @return An HitboxRectangle that defines the position, width and height of
     *         the space where the player can move. The walls are excluded.
     */
    public static HitboxRectangle getRoomSpace() {
        return new HitboxRectangle(WALL_THICKNESS, WALL_THICKNESS, WIDTH - WALL_THICKNESS * 2,
                HEIGHT - WALL_THICKNESS * 2);
    }

    /**
     * Get the HitboxRectangle of the door provided.
     * 
     * @param d
     *            The direction representing the door.
     * @return The HitboxRectangle of the door.
     */
    public static HitboxRectangle getDoor(final Direction d) {
        switch (d) {
        case UP:
            return DOORS.get(0);
        case DOWN:
            return DOORS.get(1);
        case LEFT:
            return DOORS.get(2);
        case RIGHT:
            return DOORS.get(3);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Computes the new coordinates of the player when he passes through a door
     * and change room.
     * 
     * @param d
     *            The direction representing the door the player have passed
     *            through.
     * @return The Hitbox of the player's position in the new room.
     */
    public static Hitbox getExitCoordinate(final Direction d) {
        final HitboxRectangle h;
        switch (d) {
        case UP:
            h = getDoor(Direction.DOWN);
            return new HitboxImpl(h.getX() + (h.getWidth() / 2), h.getY() - h.getHeight());
        case DOWN:
            h = getDoor(Direction.UP);
            return new HitboxImpl(h.getX() + (h.getWidth()) / 2, h.getY() + 2 * h.getHeight());
        case LEFT:
            h = getDoor(Direction.RIGHT);
            return new HitboxImpl(h.getX() - h.getWidth(), h.getY() + (h.getHeight()) / 2);
        case RIGHT:
            h = getDoor(Direction.LEFT);
            return new HitboxImpl(h.getX() + 2 * h.getWidth(), h.getY() + (h.getHeight()) / 2);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return the walls of the room.
     * 
     * @return A list of walls, represented with HitboxRectangle.
     */
    public static List<HitboxRectangle> getWalls() {
        return Collections.unmodifiableCollection(WALLS).stream().collect(Collectors.toList());
    }
}
