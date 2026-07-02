package talisman.view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

import talisman.util.CellType;

import talisman.view.ImagePanel;

/**
 * An implementation for a swing-based cell view.
 * 
 * * @author Alberto Arduini
 *
 */
public final class BoardCellViewImpl extends ImagePanel implements BoardCellView {
    private static final long serialVersionUID = 1L;
    private static final int SIZE_X = 275;
    private static final int SIZE_Y = 150;

    private final CellType type;
    private final JPanel pawnsPanel;
    private final JPanel textPanel;

    /**
     * Creates a new cell.
     * 
     * @param imagePath the path to the background image
     * @param text      the text on the cell
     * @param type      the type of the cell
     */
    public BoardCellViewImpl(final String imagePath, final String text, final CellType type) {
        super(imagePath);
        this.type = type;
        final LayoutManager layout = new OverlayLayout(this);
        this.setLayout(layout);
        final Dimension cellSize = new Dimension(BoardCellViewImpl.SIZE_X, BoardCellViewImpl.SIZE_Y);
        this.setMinimumSize(cellSize);
        this.setPreferredSize(cellSize);
        this.setMaximumSize(cellSize);
        // Pawns panel
        this.pawnsPanel = new JPanel();
        this.pawnsPanel.setOpaque(false);
        this.pawnsPanel.setMinimumSize(cellSize);
        this.pawnsPanel.setPreferredSize(cellSize);
        this.pawnsPanel.setMaximumSize(cellSize);
        this.pawnsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.pawnsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(this.pawnsPanel);
        // Description text area
        this.textPanel = this.createDescriptionTextArea(text, cellSize);
        this.setTextVisiblity(false);
        this.add(this.textPanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPawn(final PawnView pawn) {
        this.addPawn((PawnViewImpl) pawn);
    }

    /**
     * Adds a pawn from the cell.
     * 
     * @param pawn the pawn to add
     */
    public void addPawn(final PawnViewImpl pawn) {
        this.pawnsPanel.add(pawn);
        this.pawnsPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePawn(final PawnView pawn) {
        this.removePawn((PawnViewImpl) pawn);
    }

    /**
     * Removes a pawn from the cell.
     * 
     * @param pawn the pawn to remove
     */
    public void removePawn(final PawnViewImpl pawn) {
        this.pawnsPanel.remove(pawn);
        this.pawnsPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellType getCellType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextVisiblity(final boolean visible) {
        this.textPanel.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    private JPanel createDescriptionTextArea(final String text, final Dimension wrapperDimension) {
        final JTextArea textArea = new JTextArea(1, 1);
        textArea.setText(text);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                BoardCellViewImpl.this.processMouseEvent(e);
            }
            @Override
            public void mouseExited(final MouseEvent e) {
                BoardCellViewImpl.this.processMouseEvent(e);
            }
            @Override
            public void mouseClicked(final MouseEvent e) {
                BoardCellViewImpl.this.processMouseEvent(e);
            }
        });
        final JPanel wrapper = new JPanel();
        final LayoutManager layout = new BorderLayout();
        wrapper.setLayout(layout);
        wrapper.setOpaque(false);
        wrapper.setMinimumSize(wrapperDimension);
        wrapper.setPreferredSize(wrapperDimension);
        wrapper.setMaximumSize(wrapperDimension);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setAlignmentY(Component.CENTER_ALIGNMENT);
        wrapper.add(textArea, BorderLayout.SOUTH);
        wrapper.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                BoardCellViewImpl.this.processMouseEvent(e);
            }
            @Override
            public void mouseExited(final MouseEvent e) {
                BoardCellViewImpl.this.processMouseEvent(e);
            }
            @Override
            public void mouseClicked(final MouseEvent e) {
                BoardCellViewImpl.this.processMouseEvent(e);
            }
        });
        return wrapper;
    }
}
