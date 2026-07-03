package model.board;

import java.io.Serializable;
import java.util.Set;

import com.google.common.base.Optional;

import utilities.enumerations.RoomCard;

/**
 * This is the interface of a game board. A board is composed of rooms, and both
 * are a set of cells. This interface offers different methods to access the
 * various fields of the class.
 */
public interface Board extends Serializable {

    /**
     * Returns the height of the board.
     * 
     * @return the height of the board
     */
    int getHeight();

    /**
     * Returns the width of the board.
     * 
     * @return the width of the board
     */
    int getWidth();

    /**
     * Returns all the rooms of the board.
     * 
     * @return all the rooms of the board
     */
    Set<RoomCard> getRooms();

    /**
     * Returns the cells of the room.
     * 
     * @param room
     *            the room
     * @return the cells of the room
     */
    Set<Cell> getRoomCells(RoomCard room);

    /**
     * Returns the door of the room.
     * 
     * @param room
     *            the room
     * @return the door of the room
     */
    DoorCell getDoor(RoomCard room);

    /**
     * Returns, if present, the trap-door of the room.
     * 
     * @param room
     *            the room
     * @return the trap-door of the room
     */
    Optional<TrapDoorCell> getTrapDoor(RoomCard room);

    /**
     * Returns the cells of the hallway.
     * 
     * @return the cells of the hallway
     */
    Set<Cell> getHallwayCells();

    /**
     * Returns the distance between two points.
     * 
     * @param a
     *            the first point
     * @param b
     *            the second point
     * @return the distance between two cells
     */
    int getDistance(Position a, Position b);
}