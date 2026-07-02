package talisman.view.board;

import java.awt.Dimension;

import talisman.view.ImagePanel;

/**
 * Swing implementation of a pawn MVC view.
 * 
 * @author Alberto Arduini
 *
 */
public class PawnViewImpl extends ImagePanel implements PawnView {
    private static final long serialVersionUID = 1L;
    private static final int SIZE = 50;

    private BoardCellView parentCell;

    /**
     * Creates a new pawn.
     * 
     * @param imagePath the path to the pawn's image
     */
    public PawnViewImpl(final String imagePath) {
        super(imagePath);
        final Dimension size = new Dimension(PawnViewImpl.SIZE, PawnViewImpl.SIZE);
        this.setOpaque(false);
        this.setSize(size);
        this.setMaximumSize(size);
        this.setPreferredSize(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveToCell(final BoardCellView cell) {
        if (this.parentCell != null) {
            this.parentCell.removePawn(this);
        }
        this.parentCell = cell;
        this.parentCell.addPawn(this);
        this.revalidate();
    }
}
