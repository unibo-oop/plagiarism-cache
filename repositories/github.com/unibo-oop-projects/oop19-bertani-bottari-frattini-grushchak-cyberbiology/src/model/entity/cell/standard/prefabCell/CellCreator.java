package model.entity.cell.standard.prefabCell;

import model.entity.cell.standard.CellStandard;

/**
 * create the first cell to populate the word.
 * 
 * Good luck little cell!!!
 */
public interface CellCreator {
    /**
     * 
     * @param x
     * the x
     * @param y
     * the y
     * @return
     * the new first cell with a genome full of photosyntesis.
     */
    CellStandard newAllPhotosyntesisCell(int x, int y);
    /**
     * 
     * @param x
     * the x
     * @param y
     * the y
     * @return
     * the new first cell with a genome full of random genes.
     */
    CellStandard newAllRandomCell(int x, int y);
}
