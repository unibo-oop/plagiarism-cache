package talisman.model.board;

import java.util.List;

/**
 * An interface for a talisman board section.
 * 
 * @author Alberto Arduini
 *
 */
public interface TalismanBoardSection extends BoardSection<TalismanBoardCell> {
    /**
     * Constructs a new section with the given cells.
     * 
     * @param cells the list of cells contained in this section
     * @return the section
     */
    static TalismanBoardSection createSection(final List<TalismanBoardCell> cells) {
        return new TalismanBoardSectionImpl(cells);
    }
}
