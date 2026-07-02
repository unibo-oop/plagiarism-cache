package it.unibo.isaccoop.model.room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * Delegated class that contains utility methods useful when creating and operating on a {@link Level}.
 */
public final class LevelUtils {

    /**
     * Get a map containing the neighbor rooms of the current one and the direction to reach them.
     * A neighbor room is a room that is UP, DOWN, LEFT or RIGHT of the specified room.
     * This map associates a {@link Room} to the {@link Direction} to take to reach it.
     * @param currentRoom the room of which to find the near accessible rooms
     * @param rooms all the rooms in the level
     * @return a map containing the rooms near the current one and the direction to reach them
     */
    public Map<Direction, Room> getNearAccessibleRooms(final Room currentRoom, final List<Room> rooms) {
        final Map<Direction, Room> res = new HashMap<>();

        // get all neighbor rooms of currentRoom (as entry <Direction, Point2D>)
        getNeighborRoomCoords(currentRoom).entrySet().stream()
            .forEach(e -> {
                final var correspondingRoom = rooms.stream()
                        .filter(r -> r.getCoords().equals(e.getValue())).findFirst();
                if (correspondingRoom.isPresent()) {
                    // convert the map <Direction, Point2D> to a new Map<Direction, Room>
                    res.put(e.getKey(), correspondingRoom.get());
                }
            });
        return res;
    }

    /**
     * Get a new coordinate from the current coordinate coord, along the specified direction dir.
     * @param coord the current coordinate from which calculate the new coordinate
     * @param dir the direction along which the coordinate has to ben calculated
     * @return the new coordinate calculated from coordinate coord along direction dir
     */
    public Point2D getNewCoordinateAlongDirection(final Point2D coord, final Direction dir) {
        return new Point2D(coord.getX() + dir.getX(), coord.getY() + dir.getY());
    }

    /**
     * check if specified coordinate is valid (inside the grid).
     * @param coord the coordinate to be checked
     * @return true if the coordinate is valid (inside the grid), false otherwise
     */
    public boolean isValidCoord(final Point2D coord) {
        return coord.getX() >= 0 && coord.getY() >= 0;
    }

    /**
     * Get a map containing the coordinate of neighbor rooms of the current one and the direction to reach them.
     * A neighbor room is a room that is UP, DOWN, LEFT or RIGHT of the specified room.
     * @param room the current room
     * @return a map containing the coordinates of rooms near the current one and the direction to reach them
     */
    private Map<Direction, Point2D> getNeighborRoomCoords(final Room room) {
        final Point2D coord = room.getCoords();
        final Map<Direction, Point2D> res = new HashMap<>();
        for (final var dir: Direction.values()) {
            final var newCoord = getNewCoordinateAlongDirection(coord, dir);
            if (isValidCoord(newCoord) && !res.containsValue(newCoord)) {
                res.put(dir, newCoord);
            }
        }
        return res;
    }
}
