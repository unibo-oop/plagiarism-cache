package talisman.model.board;

import talisman.util.CellType;

/**
 * Basic implementation of a board cell.
 * 
 * @author Alberto Arduini
 *
 */
public class BoardCellImpl implements BoardCell {
    private static final long serialVersionUID = -242754665843262191L;

    private final String imagePath;
    private final String text;
    private final CellType type;

    /**
     * Creates a new cell.
     * 
     * @param imagePath the path to the cell image/icon
     * @param text      the text to show on the cell
     * @param type      the cell's type or orientation
     */
    public BoardCellImpl(final String imagePath, final String text, final CellType type) {
        this.imagePath = imagePath;
        this.text = text;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellType getCellType() {
        return type;
    }
}
