package talisman.model.board;

import java.util.List;

/**
 * An interface for a talisman board.
 * 
 * @author Alberto Arduini
 *
 */
public interface TalismanBoard extends PopulatedBoard<TalismanBoardSection, TalismanBoardCell, TalismanBoardPawn> {

    /**
     * Constructs a new talisman board.
     * 
     * @param sections       the sections that the board contains
     * @param characterPawns the player pawns
     * @return the board
     */
    static TalismanBoard createBoard(final List<TalismanBoardSection> sections, final List<TalismanBoardPawn> characterPawns) {
        return new TalismanBoardImpl(sections, characterPawns);
    }
}
