package talisman.view.board;

import java.util.LinkedList;
import java.util.List;

import talisman.model.board.Board;
import talisman.model.board.BoardCell;
import talisman.model.board.BoardSection;
import talisman.util.CellType;

/**
 * Builder used to compose a view.
 * 
 * @author Alberto Arduini
 *
 */
public class BoardViewBuilder {
    private final List<BoardCellView> cells;
    private final List<BoardSectionView> sections;
    private int mainSection;
    private boolean built;

    /**
     * Creates a new builder.
     */
    public BoardViewBuilder() {
        this.cells = new LinkedList<>();
        this.sections = new LinkedList<>();
        this.built = false;
    }

    /**
     * Adds a new cell to the current section.
     * 
     * @param imagePath the path to the cell's image
     * @param text      the cell's text
     * @param type      the cell's type
     * @return the builder
     */
    public BoardViewBuilder addCell(final String imagePath, final String text, final CellType type) {
        return this.addCell(BoardCellView.create(imagePath, text, type));
    }

    /**
     * Adds a new cell to the current section.
     * 
     * @param cell the cell instance to add
     * @return the builder
     */
    public BoardViewBuilder addCell(final BoardCellView cell) {
        this.checkNotBuilt();
        this.getCells().add(cell);
        return this;
    }

    /**
     * Sets the current section as main.
     * 
     * @return the builder
     */
    public BoardViewBuilder setAsMainSection() {
        this.checkNotBuilt();
        this.mainSection = this.sections.size();
        return this;
    }

    /**
     * Finalizes the current board section with the current cells, and starts the
     * creation of a new section.
     * 
     * @return the builder
     */
    public BoardViewBuilder finalizeSection() {
        this.checkNotBuilt();
        this.getSections().add(BoardSectionView.create(cells));
        this.getCells().clear();
        return this;
    }

    /**
     * Finalizes the current board section with the current cells, and starts the
     * creation of a new section.
     * 
     * @param parentSectionIndex the parent section's index
     * @return the builder
     */
    public BoardViewBuilder finalizeSectionAndInsertInto(final int parentSectionIndex) {
        this.checkNotBuilt();
        final BoardSectionView section = BoardSectionView.create(cells);
        this.getSections().add(section);
        this.getCells().clear();
        this.getSections().get(parentSectionIndex).setContainedSection(section);
        return this;
    }

    /**
     * Imports a given model.
     * 
     * @param board the model from which the board will be created
     * @return the builder
     */
    public BoardViewBuilder importModel(final Board<?, ?> board) {
        this.checkNotBuilt();
        for (int i = 0; i < board.getSectionCount(); i++) {
            final BoardSection<?> section = board.getSection(i);
            for (int j = 0; j < section.getCellCount(); j++) {
                final BoardCell cell = section.getCell(j);
                this.addCell(cell.getImagePath(), cell.getText(), cell.getCellType());
            }
            if (i == 0) {
                this.setAsMainSection().finalizeSection();
            } else {
                this.finalizeSectionAndInsertInto(i - 1);
            }
        }
        return this;
    }

    /**
     * Completes the view and returns it. After this call the builder will become
     * unusable.
     * 
     * @return the created view
     */
    public BoardView build() {
        this.checkNotBuilt();
        this.setBuilt(true);
        return BoardView.create(this.getSections(), this.getMainSection());
    }

    /**
     * Creates a view from a given model. After this call the builder will become
     * unusable.
     * 
     * @param board the model from which the board will be created
     * @return the created view
     */
    public BoardView buildFromModel(final Board<?, ?> board) {
        return this.importModel(board).build();
    }

    /**
     * Gets the current sections list.
     * 
     * @return the sections
     */
    protected List<BoardSectionView> getSections() {
        return this.sections;
    }

    /**
     * Gets the section current cell list.
     * 
     * @return the cells
     */
    protected List<BoardCellView> getCells() {
        return this.cells;
    }

    /**
     * Sets if the builder has built a board.
     * 
     * @param built the new build value
     */
    protected void setBuilt(final boolean built) {
        this.built = built;
    }

    /**
     * Gets the current main section index.
     * 
     * @return the main section index
     */
    protected int getMainSection() {
        return this.mainSection;
    }

    /**
     * Throws an exception if the builder has already build a board.
     */
    protected void checkNotBuilt() {
        if (this.built) {
            throw new IllegalStateException("Builder is aready finalized");
        }
    }
}
