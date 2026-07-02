package talisman.view.board;

import talisman.util.CellType;

/**
 * Interface that models a MVC cell view.
 * 
 * @author Alberto Arduini
 *
 */
public interface BoardCellView {
    /**
     * Gets the cell's orientation.
     * 
     * @return the cell's orientation type
     */
    CellType getCellType();

    /**
     * Adds a pawn on this cell.
     * 
     * @param pawn the pawn to add
     */
    void addPawn(PawnView pawn);

    /**
     * Removes a pawn from this cell.
     * 
     * @param pawn the pawn to remove
     */
    void removePawn(PawnView pawn);

    /**
     * Sets if the cell's text is visible.
     * 
     * @param visible is the text visible
     */
    void setTextVisiblity(boolean visible);

    /**
     * Creates a new cell.
     * 
     * @param imagePath the path to the background image
     * @param text      the text on the cell
     * @param type      the orientation of the cell
     * @return the created cell
     */
    static BoardCellView create(final String imagePath, final String text, final CellType type) {
        return new BoardCellViewImpl(imagePath, text, type);
    }
}
