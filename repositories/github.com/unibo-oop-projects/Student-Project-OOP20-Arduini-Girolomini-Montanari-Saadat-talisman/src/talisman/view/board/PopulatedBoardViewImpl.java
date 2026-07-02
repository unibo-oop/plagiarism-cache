package talisman.view.board;

import java.util.List;

import javax.swing.SwingUtilities;

/**
 * A swing implementation for a populated board view.
 * 
 * @author Alberto Arduini
 *
 */
public class PopulatedBoardViewImpl extends BoardViewImpl implements PopulatedBoardView {
    private static final long serialVersionUID = 1L;

    private final List<PawnView> pawns;

    /**
     * Creates a new board.
     * 
     * @param sections    the board sections
     * @param mainSection the main board section index
     * @param pawns       the pawns
     */
    public PopulatedBoardViewImpl(final List<BoardSectionView> sections, final int mainSection,
            final List<PawnView> pawns) {
        super(sections, mainSection);
        this.pawns = List.copyOf(pawns);
        SwingUtilities.invokeLater(() -> {
            this.pawns.forEach(p -> {
                p.moveToCell(this.getSection(0).getCell(0));
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePawnToCell(final int index, final int section, final int cell) {
        SwingUtilities.invokeLater(() -> {
            this.getPawn(index).moveToCell(this.getSection(section).getCell(cell));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PawnView getPawn(final int index) {
        return this.pawns.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.boardUpdated();
        }
    }
}
