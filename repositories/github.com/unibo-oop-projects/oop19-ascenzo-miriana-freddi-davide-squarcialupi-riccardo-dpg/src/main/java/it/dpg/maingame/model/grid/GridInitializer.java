package it.dpg.maingame.model.grid;

public interface GridInitializer {

    /**
     * creates a grid based on the grid type
     */
    Grid makeGrid(GridType gridType);

    /**
     * returns the created grid
     */
    Grid getGrid();

    /**
     * returns the first Cell of the Grid
     */
    Cell getFirst();

    /**
     * returns the last Cell of the Grid
     */
    Cell getLast();

}
