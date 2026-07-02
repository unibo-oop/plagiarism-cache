package talisman.view.board;

/**
 * Interface that models a MVC view for a pawn.
 * 
 * @author Alberto Arduini
 *
 */
public interface PawnView {
    /**
     * Moves a pawn into the specified cell.
     * 
     * @param cell the destination cell
     */
    void moveToCell(BoardCellView cell);

    /**
     * Constructs a new pawn with the given image.
     * 
     * @param imagePath the path to the pawn's image
     * @return the created Pawn
     */
    static PawnView create(final String imagePath) {
        return new PawnViewImpl(imagePath);
    }
}
