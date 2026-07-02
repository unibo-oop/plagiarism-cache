package pvz.view.gameview.impl;

import pvz.controller.gamecontroller.api.ViewListener;
import pvz.utilities.Position;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Panel representing the plant placement grid.
 * Handles mouse input and draws the background grid.
 */
public final class GridPanel extends JPanel implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int BASE_CELL_SIZE = 80;
    private static final int MARGIN_X = 0;
    private static final int MARGIN_Y = 0;

    private final int cellSize;
    private transient ViewListener listener;

    /**
     * Constructs a new grid panel with the given scaling factor.
     *
     * @param scaling the UI scaling factor based on resolution
     */
    public GridPanel(final double scaling) {
        this.setOpaque(false);
        initMouseListener();
        this.cellSize = (int) (scaling * BASE_CELL_SIZE);
    }

    /**
     * Sets the listener for grid cell input.
     *
     * @param listener the ViewListener instance
     */
    public void setViewListener(final ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Paints the grid background on the panel.
     *
     * @param g the Graphics context to use
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(Color.BLACK);

        final int totalWidth = COLS * cellSize;
        final int totalHeight = ROWS * cellSize;

        for (int x = 0; x <= COLS; x++) {
            final int xPos = MARGIN_X + x * cellSize;
            g2.drawLine(xPos, MARGIN_Y, xPos, MARGIN_Y + totalHeight);
        }

        for (int y = 0; y <= ROWS; y++) {
            final int yPos = MARGIN_Y + y * cellSize;
            g2.drawLine(MARGIN_X, yPos, MARGIN_X + totalWidth, yPos);
        }
        g2.dispose();
    }

    /**
     * Returns the preferred size of the panel.
     *
     * @return the panel's preferred dimensions
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MARGIN_X * 2 + COLS * cellSize, MARGIN_Y * 2 + ROWS * cellSize);
    }

    /**
     * Initializes the mouse listener to detect grid cell clicks.
     */
    private void initMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int x = (e.getX() - MARGIN_X) / cellSize;
                final int y = (e.getY() - MARGIN_Y) / cellSize;

                if (x >= 0 && x < COLS && y >= 0 && y < ROWS && listener != null) {
                    listener.processInputGrid(new Position(x, y));
                }
            }
        });
    }
}
