package model.world;

import java.util.Map;
import model.direction.Direction;
import model.entity.Entity;
import model.entity.EntityTypeNameEnum;
import model.entity.cell.CellTypeNameEnum;
import model.entity.cell.standard.CellStandard;
import model.square.SquareImp;
import utilities.Pair;


/**
 * a class that creates the world where the cells lives.
 */


public interface World {

    /**
     * @return height of the world
     */
    int getWorldHeight();

    /**
     * @return width of the world
     */
    int getWorldWidth();

    /**
     * @return the map containing all the cells
     */
    Map<Pair<Integer, Integer>, SquareImp> getMap();

    /**
     * create a new dead cell in the given position.
     * @param x width
     * @param y height
     */
    void makeCellDeath(int x, int y);

    /**
     * @param x width
     * @param y height
     * @return the square at the given position
     * @throws NoSquareException if the square doesn't exist
     */
    SquareImp getSquare(int x, int y);

    /**
     * @return number of alive cells in all the simulation
     */
    int getAliveCells();

    /**
     * @return number of dead cells in all the simulation
     */
    int getDeadCells();

    /**
     * free the square at the given position.
     * @param x width
     * @param y height
     */
    void removeCell(int x, int y);

    /**
     * move the cell in the given direction and update its position
     * if the position is already occupated, method is aborted.
     * @param x width
     * @param y height
     * @param direction
     */
    void tryMoveCell(int x, int y, Direction direction);

    /**
     * check if there's at least one position free around the cell we want to move.
     * @param x width
     * @param y height
     * @return TRUE if there's at least one position free around the cell, FALSE otherwise
     */
    boolean isThereFreeSpaceAround(int x, int y);

    /**
     * add the child of the cell in position (x, y) in the given direction, 
     * if the position is already occuped, it puts the cell in another place around the mother cell, if there's 
     * something free.
     * @param x width
     * @param y height
     * @param cell
     * @param direction
     */
    void putChild(int x, int y, CellStandard cell, Direction direction);

    /**
     * @param x width
     * @param y height
     * @return the type of the entity (cell or stone) in the given position (live or dead)
     * @throw IllegalStateException if the given position is free
     */
    EntityTypeNameEnum getType(int x, int y);

    /** 
     * @param x width
     * @param y height
     * @return the cell type of the square in the given position
     * @throws IllegalStateException if in the given position there's a stone or is empty
     */
    CellTypeNameEnum getCellType(int x, int y);

    /**
     * put a cell in the given postion, if it's free.
     * @param x
     * @param y
     * @param cell
     */
    void putCell(int x, int y, Entity cell);

    /**
     * @param x
     * @param y
     * @return true if the square in the given position is occuped by an entity, false otherwise
     */
    boolean isThereAnything(int x, int y);

 }


