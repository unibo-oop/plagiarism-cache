package it.unibo.modularcheckers.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Build the Grid for all the buttons of the chess board.
 */
public class ChessBoardComponent extends JPanel {

    private static final long serialVersionUID = -6766149699188621419L;

    /**
     * Creates the panel and the layout of the chess board.
     *
     * @param height height of the chess board
     * @param width  width of the chess board
     */
    public ChessBoardComponent(final int height, final int width) {
        super(new GridLayout(height, width));
        setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.BLACK)));
        setBackground(new Color(204, 119, 34));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Dimension getPreferredSize() {
        final Dimension d = super.getPreferredSize();
        Dimension prefSize;
        final Component c = getParent();
        if (c == null) {
            prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
        } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
            prefSize = c.getSize();
        } else {
            prefSize = d;
        }
        final int w = (int) prefSize.getWidth();
        final int h = (int) prefSize.getHeight();
        // the smaller of the two sizes
        final int s = (w > h ? h : w);
        return new Dimension(s, s);
    }
}
