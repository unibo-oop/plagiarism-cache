package model.board;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import utilities.enumerations.CellType;
import utilities.enumerations.RoomCard;

/**
 * This abstract class uses the Decorator pattern. It permits to add new
 * features dynamically to an already existent cell by wrapping it.
 */
public abstract class CellDecorator implements Cell {

    private static final long serialVersionUID = 4201653537669744494L;
    private final Cell decorated;

    /**
     * Wraps a cell object.
     * 
     * @param decorated
     *            the cell to be decorated
     */
    protected CellDecorator(final Cell decorated) {
        Preconditions.checkNotNull(decorated);
        this.decorated = decorated;
    }

    @Override
    public abstract CellType getType();

    @Override
    public Position getPosition() {
        return this.decorated.getPosition();
    }

    @Override
    public Optional<RoomCard> getRoom() {
        return this.decorated.getRoom();
    }

    @Override
    public boolean isOccupied() {
        return this.decorated.isOccupied();
    }

    @Override
    public void setOccupied() {
        this.decorated.setOccupied();
    }

    @Override
    public void setFree() {
        this.decorated.setFree();
    }
}