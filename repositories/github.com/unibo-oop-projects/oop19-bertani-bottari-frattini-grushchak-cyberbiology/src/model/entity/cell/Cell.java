package model.entity.cell;

import model.entity.Entity;

/**
 * 
 * interface for the cell method.
 * 
 * this interface is for all type of cells.
 *
 */
public interface Cell extends Entity {
    /**
     * get the type of the cell.
     * 
     * @return the {@link CellTypeName}.
     */
    CellTypeNameEnum getCellTypeName();
    /**
     * a setter.
     * @param x
     */
    void setX(int x);
    /**
     * a setter.
     * @param y
     */
    void setY(int y);
}
