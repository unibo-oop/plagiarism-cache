package talisman.view.board;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * A swing implementation of a view for a board.
 * 
 * @author Alberto Arduini
 *
 */
public class BoardViewImpl extends JPanel implements BoardView {
    private static final long serialVersionUID = 1L;
    
    private final List<BoardSectionView> sections;
    private final Set<BoardListener> updateListeners;

    /**
     * Creates a new board view.
     * 
     * @param sections    the sections that this board contains
     * @param mainSection the index of the most external section
     */
    public BoardViewImpl(final List<BoardSectionView> sections, final int mainSection) {
        this.sections = List.copyOf(sections);
        // I use an OverlayLayout so i can layer things on top of the board
        final LayoutManager layout = new OverlayLayout(this);
        this.setLayout(layout);
        this.setBackground(Color.DARK_GRAY);
        // Like for the sections cells, I take as granted that the sections use the
        // swing implementation.
        // Still, the most generic type is used in case the actual type of component
        // changes.
        this.add((Component) this.sections.get(mainSection), 0);
        this.updateListeners = new HashSet<>();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                BoardViewImpl.this.boardUpdated();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    // This is needed in order to properly render things on top of the cells
    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSectionCount() {
        return this.sections.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardSectionView getSection(final int sectionIndex) {
        return this.sections.get(sectionIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUpdateListener(final BoardListener listener) {
        this.updateListeners.add(listener);
    }

    /**
     * Invokes all listeners waiting for a board update.
     */
    protected void boardUpdated() {
        for (final BoardListener listener : this.updateListeners) {
            listener.onViewUpdated();
        }
    }
}
