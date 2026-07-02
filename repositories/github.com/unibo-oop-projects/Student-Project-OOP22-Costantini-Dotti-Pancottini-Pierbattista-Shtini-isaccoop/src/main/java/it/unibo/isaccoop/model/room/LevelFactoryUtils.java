package it.unibo.isaccoop.model.room;

import java.util.LinkedList;
import java.util.List;

import it.unibo.isaccoop.model.common.Direction;
import it.unibo.isaccoop.model.common.Point2D;

/**
 * Delegated class that contains utility methods to help {@link LevelFactoryImpl} creating 
 * a room.
 */
public final class LevelFactoryUtils {

    /**
     * Generates dynamically the coordinates that will be used as positions
     * for the rooms in this level.
     * @param numberOfRooms the number of rooms that will be in this level
     * @return the list of coordinates to be assigned to the rooms
     */
    public List<Point2D> generateRoomCoordinates(final int numberOfRooms) {
        // initial position for the first room of the level
        Point2D roomPos = new Point2D(0.0, 0.0);
        final List<Point2D> list = new LinkedList<>();

        for (int i = 0; i < numberOfRooms; i++) {
            if (isValidCoord(roomPos) && !list.contains(roomPos)) {
                list.add(roomPos);
                roomPos = getNewCoordinateAlongDirection(roomPos, Direction.RIGHT);
            }
        }
        return list;
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
     * Get the coordinates of all neighbor rooms of specified room.
     * A neighbor room is a room that is UP, DOWN, LEFT or RIGHT of the specified room.
     * @param coord the current room coordinate
     * @return a list of coordinates of neighbor rooms
     */
    public List<Point2D> getNeighborRooms(final Point2D coord) {
        final List<Point2D> res = new LinkedList<>();
        for (final var dir: Direction.values()) {
            final var newCoord = getNewCoordinateAlongDirection(coord, dir);
            if (isValidCoord(newCoord) && !res.contains(newCoord)) {
                res.add(newCoord);
            }
        }
        return res;
    }
}
