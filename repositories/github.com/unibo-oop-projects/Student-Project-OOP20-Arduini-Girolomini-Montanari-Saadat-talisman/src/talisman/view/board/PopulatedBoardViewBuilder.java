package talisman.view.board;

import java.util.LinkedList;
import java.util.List;

import talisman.model.board.PopulatedBoard;
import talisman.util.CellType;

public class PopulatedBoardViewBuilder extends BoardViewBuilder {
    private final List<PawnView> pawns;

    /**
     * Creates a new builder.
     */
    public PopulatedBoardViewBuilder() {
        super();
        this.pawns = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardViewBuilder addCell(final String imagePath, final String text, final CellType type) {
        super.addCell(imagePath, text, type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardViewBuilder addCell(final BoardCellView cell) {
        super.addCell(cell);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardViewBuilder setAsMainSection() {
        super.setAsMainSection();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardViewBuilder finalizeSection() {
        super.finalizeSection();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardViewBuilder finalizeSectionAndInsertInto(final int parentSectionIndex) {
        super.finalizeSectionAndInsertInto(parentSectionIndex);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public PopulatedBoardViewBuilder importModel(final PopulatedBoard<?, ?, ?> board) {
        super.importModel(board);
        for (int i = 0; i < board.getPawnCount(); i++) {
            this.addPawn(board.getPawn(i).getImagePath());
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardView build() {
        this.checkNotBuilt();
        this.setBuilt(true);
        return PopulatedBoardView.create(this.getSections(), this.getMainSection(), this.pawns);
    }

    /**
     * {@inheritDoc}
     */
    public PopulatedBoardView buildFromModel(final PopulatedBoard<?, ?, ?> board) {
        return this.importModel(board).build();
    }

    /**
     * Adds a pawn to the board.
     * 
     * @param imagePath the path to the pawn's image
     * @return the builder
     */
    public PopulatedBoardViewBuilder addPawn(final String imagePath) {
        this.checkNotBuilt();
        this.pawns.add(PawnView.create(imagePath));
        return this;
    }

    /**
     * Gets the list of the currently added pawns.
     * 
     * @return the pawns list
     */
    protected List<PawnView> getPawns() {
        return this.pawns;
    }
}
