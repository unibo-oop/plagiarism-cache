package model.board;

import utilities.enumerations.CellType;

/**
 * Cell containing a door.
 */
public class DoorCell extends CellDecorator {

    private static final long serialVersionUID = 5090046916816010605L;
    private final Cell destination;

    /**
     * Creates a DoorCell.
     * 
     * @param destination
     *            where the door leads
     * @param decorated
     *            the simple cell to be decorated
     */
    protected DoorCell(final Cell destination, final Cell decorated) {
        super(decorated);
        this.destination = destination;
    }

    @Override
    public CellType getType() {
        return CellType.DOOR;
    }

    /**
     * Returns where the door leads.
     * 
     * @return where the door leads
     */
    public Cell getDestination() {
        return this.destination;
    }

    @Override
    public String toString() {
        return "DoorCell [coords=" + getPosition() + ", room=" + getRoom().orNull() + ", occupied=" + isOccupied()
                + ", destination=" + destination + "]";
    }
}