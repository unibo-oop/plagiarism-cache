package model.board;

import java.io.Serializable;

import com.google.common.base.Optional;

import utilities.enumerations.CellType;
import utilities.enumerations.RoomCard;

/**
 * This interface represents a cell of the game board.
 */
public interface Cell extends Serializable {

    /**
     * Returns the coordinates of the cell.
     * 
     * @return the coordinates of the cell
     */
    Position getPosition();

    /**
     * Returns the room the cell belongs to.
     * 
     * @return the room the cell belongs to
     */
    Optional<RoomCard> getRoom();

    /**
     * Returns the type of the cell.
     * 
     * @return the type of the cell
     */
    CellType getType();

    /**
     * Returns true if the cell is occupied by a player, false otherwise.
     * 
     * @return true if the cell is occupied by a player
     */
    boolean isOccupied();

    /**
     * Sets the cell occupied.
     */
    void setOccupied();

    /**
     * Sets the cell free.
     */
    void setFree();
}