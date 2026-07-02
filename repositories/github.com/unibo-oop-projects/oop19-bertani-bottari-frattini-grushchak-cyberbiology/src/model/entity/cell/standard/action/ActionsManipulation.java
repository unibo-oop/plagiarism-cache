package model.entity.cell.standard.action;

import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.CellStandardImpl;
import model.properties.cells.CellData;

public interface ActionsManipulation extends Action {
    /**
     * kill the cell, comunicate with the world and create a dead cell.
     * @param cell
     * the cell protagonist
     */
    void becomeDead(CellStandard cell);
    /**
     * 
     * @param cellStandardImpl
     * @param cellData
     * @return the copy of the cell
     */
    CellStandard dupicate(CellStandardImpl cellStandardImpl, CellData cellData);
    /**
     * reproduce the current cell.
     * @param cell
     */
    void reproduce(CellStandard cell);
    /**
     * absorb the mineral from the ground.
     * @param cell
     * the cell
     * 
     */
    void absorbMinerals(CellStandard cell);

}
