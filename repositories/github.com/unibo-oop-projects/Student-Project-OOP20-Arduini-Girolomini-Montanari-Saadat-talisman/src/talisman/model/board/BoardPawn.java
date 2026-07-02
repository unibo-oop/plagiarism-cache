package talisman.model.board;

/**
 * Interface that models a pawn on a board.
 * 
 * @author Alberto Arduini
 *
 */
public interface BoardPawn {
    /**
     * Gets the player controlling this pawn.
     * 
     * @return the player's index
     */
    int getPlayerIndex();

    /**
     * Gets the path to the pawn's image.
     * 
     * @return the pawn's image
     */
    String getImagePath();

    /**
     * Gets the current section on which this pawn is.
     * 
     * @return the section index
     */
    int getPositionSection();

    /**
     * Gets the current cell on which this pawn is.
     * 
     * @return the cell index
     */
    int getPositionCell();

    /**
     * Sets the current position of this pawn.
     * 
     * @param section the new section index
     * @param cell    the new cell index
     */
    void setPosition(int section, int cell);
}
