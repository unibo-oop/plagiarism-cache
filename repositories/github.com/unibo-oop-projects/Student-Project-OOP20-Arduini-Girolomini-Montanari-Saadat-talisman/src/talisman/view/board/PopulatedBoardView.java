package talisman.view.board;

import java.util.List;

/**
 * An interface for a MVC view populated board.
 * 
 * @author Alberto Arduini
 *
 */
public interface PopulatedBoardView extends BoardView {
    /**
     * Moves a pawn to a specific section and cell.
     * 
     * @param index   the pawn's index
     * @param section the new section index
     * @param cell    the new cell index
     */
    void movePawnToCell(int index, int section, int cell);

    /**
     * Gets the pawn's view at the given index.
     * 
     * @param index the pawn's index
     * @return the pawn's view instance
     */
    PawnView getPawn(int index);

    /**
     * Constructs a new populated board.
     * 
     * @param sections    the board sections
     * @param mainSection the board main section index
     * @param pawns       the pawns
     * @return the created board
     */
    static PopulatedBoardView create(List<BoardSectionView> sections, int mainSection, List<PawnView> pawns) {
        return new PopulatedBoardViewImpl(sections, mainSection, pawns);
    }
}
