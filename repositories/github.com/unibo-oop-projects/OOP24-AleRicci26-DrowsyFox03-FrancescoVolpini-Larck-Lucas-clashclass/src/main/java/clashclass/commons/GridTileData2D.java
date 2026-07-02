package clashclass.commons;

import clashclass.ecs.AbstractComponent;

/**
 * Represents a component which holds useful information
 * of the grid tile in which the game object is placed.
 */
public class GridTileData2D extends AbstractComponent {
    private final VectorInt2D position;
    private final int rowSpan;
    private final int colSpan;

    /**
     * Construct the component.
     *
     * @param position the grid position
     * @param rowSpan the number of horizontal busy cells
     * @param colSpan the number of vertical busy cells
     */
    public GridTileData2D(final VectorInt2D position, final int rowSpan, final int colSpan) {
        this.position = position;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    /**
     * Gets the grid position.
     *
     * @return the grid position
     */
    public VectorInt2D getPosition() {
        return this.position;
    }

    /**
     * Gets the row span.
     *
     * @return the row span
     */
    public int getRowSpan() {
        return this.rowSpan;
    }

    /**
     * Gets the column span.
     *
     * @return the column span
     */
    public int getColSpan() {
        return this.colSpan;
    }
}
