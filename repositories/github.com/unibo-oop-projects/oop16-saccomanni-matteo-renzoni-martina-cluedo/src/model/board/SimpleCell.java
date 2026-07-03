package model.board;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import utilities.enumerations.CellType;
import utilities.enumerations.RoomCard;

/**
 * The implementation of the interface Cell.
 */
public class SimpleCell implements Cell {

    private static final long serialVersionUID = 8633241883126089505L;
    private final Position coords;
    private final Optional<RoomCard> room;
    private boolean occupied;

    /**
     * Creates an instance of SimpleCell.
     * 
     * @param coords
     *            the coordinates of the cell
     * @param room
     *            the room the cell belongs to
     */
    public SimpleCell(final Position coords, final Optional<RoomCard> room) {
        Preconditions.checkNotNull(coords);
        Preconditions.checkNotNull(room);
        this.coords = coords;
        this.room = room;
        this.occupied = false;
    }

    @Override
    public Position getPosition() {
        return this.coords;
    }

    @Override
    public Optional<RoomCard> getRoom() {
        return this.room;
    }

    @Override
    public boolean isOccupied() {
        return this.occupied;
    }

    @Override
    public CellType getType() {
        return CellType.SIMPLE;
    }

    @Override
    public void setOccupied() {
        this.occupied = true;
    }

    @Override
    public void setFree() {
        this.occupied = false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coords == null) ? 0 : coords.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleCell other = (SimpleCell) obj;
        if (coords == null) {
            if (other.coords != null) {
                return false;
            }
        } else if (!coords.equals(other.coords)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SimpleCell [coords=" + coords + ", room=" + room.orNull() + ", occupied=" + occupied + "]";
    }
}