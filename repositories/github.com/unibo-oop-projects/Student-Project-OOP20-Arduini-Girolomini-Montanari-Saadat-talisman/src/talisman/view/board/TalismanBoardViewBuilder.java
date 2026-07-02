package talisman.view.board;

import talisman.model.board.TalismanBoard;

import talisman.util.CellType;

public class TalismanBoardViewBuilder extends PopulatedBoardViewBuilder {
    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardViewBuilder addCell(final String imagePath, final String text, final CellType type) {
        super.addCell(imagePath, text, type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardViewBuilder addCell(final BoardCellView cell) {
        super.addCell(cell);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardViewBuilder setAsMainSection() {
        super.setAsMainSection();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardViewBuilder finalizeSection() {
        super.finalizeSection();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardViewBuilder finalizeSectionAndInsertInto(final int parentSectionIndex) {
        super.finalizeSectionAndInsertInto(parentSectionIndex);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardViewBuilder addPawn(final String imagePath) {
        super.addPawn(imagePath);
        return this;
    }

    /**
     * Imports the values from a board model into the builder.
     * 
     * @param board the board model
     * @return the builder
     */
    public TalismanBoardViewBuilder importModel(final TalismanBoard board) {
        super.importModel(board);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardView build() {
        this.checkNotBuilt();
        this.setBuilt(true);
        return TalismanBoardView.create(this.getSections(), this.getMainSection(), this.getPawns());
    }

    /**
     * Builds a view from a given model.
     * 
     * @param board the board model
     * @return the created view.
     */
    public TalismanBoardView buildFromModel(final TalismanBoard board) {
        return this.importModel(board).build();
    }
}
