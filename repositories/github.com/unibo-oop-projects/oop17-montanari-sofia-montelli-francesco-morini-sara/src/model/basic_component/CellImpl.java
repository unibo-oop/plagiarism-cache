package model.basic_component;

import lombok.EqualsAndHashCode;

/**
 * A basic implementation of {@link Cell}.
 */
@EqualsAndHashCode(exclude =  {"status"})
public final class CellImpl implements Cell {

    /**
     * The encapsulated {@link StaticPoint2D}.
     */

    private static final long serialVersionUID = 1L;

    /**
     * The encapsulated {@link StaticPoint2D}.
     */
    private final StaticPoint2D coordinates;

    /**
     * Status information of the {@link Cell}.
     */
    private Cell.Status status;

    private void checkAndThrow(final boolean condition, final RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }
    /**
     * 
     * @param xCord is the x coordinate of the cell
     * @param yCord is the y coordinate of the cell
     * @param status The initial status of the cell
     */
    public CellImpl(final int xCord, final int yCord, final Cell.Status status) {
        checkAndThrow(xCord < 0, new IllegalArgumentException());
        checkAndThrow(yCord < 0, new IllegalArgumentException());
        coordinates = new StaticPoint2DImpl(xCord, yCord);
        this.status = status;
    }

    /**
     * @param xCord is the x coordinate of the {@link Cell}
     * @param yCord is the y coordinate of the {@link Cell}
     */
    public CellImpl(final int xCord, final int yCord) {
        this(xCord, yCord, Status.FREE);
    }

    /**
     * @param point is the {@link StaticPoint2D} of the {@link Cell}
     */
    public CellImpl(final StaticPoint2D point) {
        this(point.getX(), point.getY(), Status.FREE);
    }

    /**
     * 
     * @param point is the {@link StaticPoint2D} of the {@link Cell}
     * @param status The initial status of the cell
     */
    public CellImpl(final StaticPoint2D point, final Status status) {
        this(point.getX(), point.getY(), status);
    }

    @Override
    public Status getStatus() {
        return status;
    }
 
    @Override
    public StaticPoint2D getCoordinate() {
        return coordinates;
    }

    @Override
    public void interact() throws IllegalStateException {

        switch (status) {
        case FREE:
            status = Cell.Status.TARGETED;
            break;

        case OCCUPIED:
            status = Cell.Status.OCCUPIED_AND_TARGETED;
            break;

        case TARGETED:
            throw new IllegalStateException(ERR_SHOOT_TRGD);

        case OCCUPIED_AND_TARGETED:
            throw new IllegalStateException(ERR_SHT_OCC_TRGD);

        default:
            throw new IllegalStateException(ERR_SHOOT_UNDEF);
        }
    }

    @Override
    public boolean adjacent(final StaticPoint2D other) {
        return false;
    }

    @Override
    public String toString() {
        return "CellImpl [coordinates=" + coordinates + "]";
    }
}
