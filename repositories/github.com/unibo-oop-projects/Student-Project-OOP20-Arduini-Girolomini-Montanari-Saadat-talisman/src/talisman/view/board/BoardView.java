package talisman.view.board;

import java.util.List;

/**
 * Interface for a view of a game board.
 * 
 * @author Alberto Arduini
 */
public interface BoardView {
    /**
     * Gets the number of sections in this board.
     * 
     * @return the sections count
     */
    int getSectionCount();

    /**
     * Gets the section at the given index.
     * 
     * @param sectionIndex the section index
     * @return the section instance
     */
    BoardSectionView getSection(int sectionIndex);

    /**
     * Adds a listener for when the board view updates.
     * 
     * @param listener
     */
    void addUpdateListener(BoardListener listener);

    /**
     * Creates a new view from a list of sections.
     * 
     * @param sections    the contained sections
     * @param mainSection the index of the most external section
     * @return the created view
     */
    static BoardView create(final List<BoardSectionView> sections, final int mainSection) {
        return new BoardViewImpl(sections, mainSection);
    }
}
