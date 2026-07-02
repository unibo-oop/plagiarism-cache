package unibo.exiled.view.items;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Component;
import java.io.Serial;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * Used in HUD to make panels resizeable.
 */
public final class WrapLayout extends FlowLayout {
    @Serial
    private static final long serialVersionUID = 42L;

    /**
     * Creates a Wrap Layout.
     *
     * @param align The alignment.
     * @param hgap  The horizontal gap.
     * @param vgap  The vertical gap.
     */
    public WrapLayout(final int align, final int hgap, final int vgap) {
        super(align, hgap, vgap);
    }

    @Override
    public Dimension preferredLayoutSize(final Container target) {
        return layoutSize(target, true);
    }

    @Override
    public Dimension minimumLayoutSize(final Container target) {
        final Dimension minimum = layoutSize(target, false);
        minimum.width -= getHgap() + 1;
        return minimum;
    }

    private Dimension layoutSize(final Container target, final boolean preferred) {
        synchronized (target.getTreeLock()) {
            int targetWidth = target.getSize().width;

            if (targetWidth == 0) {
                targetWidth = Integer.MAX_VALUE;
            }

            final int hgap = getHgap();
            final int vgap = getVgap();
            final Insets insets = target.getInsets();
            final int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
            final int maxWidth = targetWidth - horizontalInsetsAndGap;

            final Dimension dim = new Dimension(0, 0);
            int rowWidth = 0;
            int rowHeight = 0;

            final int nmembers = target.getComponentCount();

            for (int i = 0; i < nmembers; i++) {
                final Component m = target.getComponent(i);

                if (m.isVisible()) {
                    final Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

                    if (rowWidth + d.width > maxWidth) {
                        addRow(dim, rowWidth, rowHeight);
                        rowWidth = 0;
                        rowHeight = 0;
                    }

                    if (rowWidth != 0) {
                        rowWidth += hgap;
                    }

                    rowWidth += d.width;
                    rowHeight = Math.max(rowHeight, d.height);
                }
            }

            addRow(dim, rowWidth, rowHeight);

            dim.width += horizontalInsetsAndGap;
            dim.height += insets.top + insets.bottom + vgap * 2;

            final Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);
            if (scrollPane != null) {
                dim.width -= hgap + 1;
            }

            return dim;
        }
    }

    private void addRow(final Dimension dim, final int rowWidth, final int rowHeight) {
        dim.width = Math.max(dim.width, rowWidth);

        if (dim.height > 0) {
            dim.height += getVgap();
        }

        dim.height += rowHeight;
    }
}
