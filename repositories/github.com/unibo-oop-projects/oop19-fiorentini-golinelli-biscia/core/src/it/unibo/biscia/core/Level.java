package it.unibo.biscia.core;

import java.util.List;

/**
 * Add level and elements (wall, biscia and food) to a grid.
 *
 */
public interface Level {
    /**
     * min number of columns.
     */
    int MIN_COLS = 80;
    /**
     * min number of rows.
     */
    int MIN_ROWS = 50;

    /**
     * the number of current level.
     * 
     * @return a integer
     */
    int getCardinal();

    /**
     * Getting a set of elements on board.
     * 
     * @return the entities contained on board
     */
    List<Entity> getEntities();

    /**
     * Get the numbers of columns on board.
     * 
     * @return a number positive
     */
    int getCols();

    /**
     * Get the numbers of rows on board.
     * 
     * @return a number positive
     */
    int getRows();

    /**
     * Get a single cell on board.
     * 
     * @param col index of column
     * @param row index of row
     * @return a single cell at coordinate indicated
     */
    Cell getCell(int col, int row);

    /**
     * return the cell to the side of passed cell.
     * 
     * @param col       column index of cell to start
     * @param row       row index of cell to start
     * @param direction side to request
     * @return cell at side indicated
     */
    Cell getSideCell(int col, int row, Direction direction);

    /**
     * return the cell to the side of passed cell.
     * 
     * @param cell      cell to start
     * @param direction side to request
     * @return cell at side indicated
     */
    Cell getSideCell(Cell cell, Direction direction);

    /**
     * Additional functions for internal use.
     *
     */
    interface LevelManaged extends Level {

        void setCardinal(int cardinal);

        List<Entity.EntityManaged> getEntitiesManaged();

        /**
         * return the full sequences of cells.
         * 
         * @return list of all cells grid
         */
        List<Cell> getCells();

        /**
         * for require a delimited subset of cells of level.
         * 
         * @param cell   cell of start or area
         * @param width  width of area
         * @param height height of area
         * @return a list with all cells of area definited
         */
        List<Cell> getArea(Cell cell, int width, int height);

        /**
         * for require a delimited subset of cells of level.
         * 
         * @param cell1 cell of first corner of area
         * @param cell2 cell of second corner of area
         * @return a list with all cells of area definited
         */
        List<Cell> getArea(Cell cell1, Cell cell2);

        /**
         * add entity to a level. If the cells of new entity is occpupies then throw
         * IllegalArgumentException.
         * 
         * @param entity entity to add
         */
        void addEntity(Entity.EntityManaged entity);

        /**
         * remove entity to level.
         * 
         * @param entity entity to remove
         */
        void removeEntity(Entity.EntityManaged entity);

        /**
         * the specialized interface that expose method for analyze this level.
         * 
         * @return a level analyzer
         */
        LevelAnalyzer getAnalyzer();

        /**
         * the specialized interface that expose method for moveing entity ad manage his
         * interactions.
         * 
         * @return a manager of interactions
         */

        InteractionManager getInteractionManager();
    }

}
