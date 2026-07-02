package model.gravity;

import model.entity.cell.cellDead.CellDead;

public interface Gravity {

    /**
     * method that simulates gravity for dead cells.
     * @param cell the cell to move
     */
    void cellFallingDown(CellDead cell);

}
