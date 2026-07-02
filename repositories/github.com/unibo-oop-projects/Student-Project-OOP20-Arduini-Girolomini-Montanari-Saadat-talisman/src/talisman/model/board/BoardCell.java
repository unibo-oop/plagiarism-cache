package talisman.model.board;

import java.io.Serializable;

import talisman.util.CellType;

/**
 * Interface that models a cell on a board.
 * 
 * @author Alberto Arduini
 *
 */
public interface BoardCell extends Serializable {
    /**
     * Gets the cell's image path.
     * 
     * @return the image's path
     */
    String getImagePath();

    /**
     * Get the text to show on the cell.
     * 
     * @return the cell's text
     */
    String getText();

    /**
     * Get the type/orientation of the cell.
     * 
     * @return the cell's text
     */
    CellType getCellType();
}
