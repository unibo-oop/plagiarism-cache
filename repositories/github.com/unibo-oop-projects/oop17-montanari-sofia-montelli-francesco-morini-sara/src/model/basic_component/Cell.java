package model.basic_component;

import java.io.Serializable;

/**
 * This interface represents a Cell of the grid with status information.
 */
public interface Cell extends Serializable {
    /**
     * Represent the status of the Cell.
     * {@link #FREE}
     * {@link #OCCUPIED}
     * {@link #TARGETED}
     * {@link #OCCUPIED_AND_TARGETED}
     * 
     */
    enum Status {
        /**
         * The cell is free.
         */
        FREE,
        /**
         * The cell is occupied.
         */
        OCCUPIED,
        /**
         * The cell is targeted.
         */
        TARGETED,
        /*
         * The cell is occupied and targeted.
         */
        OCCUPIED_AND_TARGETED;
    }
    /**
     * Used in exception launch, you can't target 
     * a cell which has already been targeted.
     */

    String ERR_SHOOT_TRGD = "Cell already targeted";
    /**
     * Used in exception launch, you can't target
     * a cell which has already been targeted.
     */
    String ERR_SHT_OCC_TRGD = "Cell already occupied and targeted";

    /**
     * Used in exception launch, you can't target a cell with an undefined status.
     */
    String ERR_SHOOT_UNDEF = "Undefined error: state of the cell is unknown";

    /**
     * 
     * @return {@link Cell.Status} The status of the cell
     */
    Cell.Status getStatus();

    /**
     * @return the cell's coordinate as a {@link StaticPoint2D}
     */
    StaticPoint2D getCoordinate();

    /**
     * a method for interact with the cell.
     * @throws IllegalStateException If the status doesn't permit an interaction
     */
    void interact() throws IllegalStateException;

    /**
     * Checks if this and the other cell are adjacent.
     * @param other is the {@link StaticPoint2D} to test
     * @return if are adjacent
     */
    boolean adjacent(StaticPoint2D other);
}
