package model.board;

import utilities.enumerations.CellType;
import utilities.enumerations.RoomCard;

/**
 * Cell containing a trap-door.
 */
public class TrapDoorCell extends CellDecorator {

    private static final long serialVersionUID = -4904068235258806258L;
    private final RoomCard destination;

    /**
     * Creates a TrapDoorCell.
     * 
     * @param destination
     *            where the trap-door leads
     * @param decorated
     *            the simple cell to be decorated
     */
    protected TrapDoorCell(final RoomCard destination, final Cell decorated) {
        super(decorated);
        this.destination = destination;
    }

    @Override
    public CellType getType() {
        return CellType.TRAP_DOOR;
    }

    /**
     * Returns where the trap-door leads.
     * 
     * @return where the trap-door leads
     */
    public RoomCard getDestinationRoom() {
        return this.destination;
    }

    @Override
    public String toString() {
        return "DoorCell [coords=" + getPosition() + ", room=" + getRoom().orNull() + ", occupied=" + isOccupied()
                + ", destination=" + destination + "]";
    }
}