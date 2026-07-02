package main.java.com.view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import main.java.com.utility.Pos;
import main.java.com.utility.Position;

/**
 * This class represents the view for the game's map, in which the game entities
 * are drawn.
 */
public class MapViewImpl extends JPanel implements MapView {

    private static final long serialVersionUID = 7273434234021291999L;
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double MUL = 1.5;

    private final int xMapSize;
    private final int ymapSize;
    private final Map<Position, Position> cells = new HashMap<>();
    private final AppleViewImpl apple;
    private final SnakeViewImpl snake;
    private final int cellSize;

    /**
     * Constructor that creates and initializes the apple and snake's view's
     * representations.
     * 
     * @param x the game map's width
     * @param y the game map's height
     */
    public MapViewImpl(final int x, final int y) {
        xMapSize = x;
        ymapSize = y;
        // Check if screen is vertical or horizontal and set the cell size accordingly
        cellSize = (int) (SCREEN_SIZE.getHeight() / SCREEN_SIZE.getWidth() <= 1 ? SCREEN_SIZE.getHeight() / (y * MUL)
                                                                                : SCREEN_SIZE.getWidth() / (x * MUL));
        apple = new AppleViewImpl(cells, cellSize);
        snake = new SnakeViewImpl(cells, cellSize);
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    /**
     * Paints this panel and all the components on it.
     * 
     * @param g the {@link Graphics}
     */
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawBounds(g);
        apple.draw(g);
        if (!getMapBounds().contains(snake.getHeadCenter())) {
            snake.getBody().remove(0);
        }
        snake.draw(g);
    }

    /** {@inheritDoc} */
    public AppleView getAppleView() {
        return apple;
    }

    /** {@inheritDoc} */
    public SnakeView getSnakeView() {
        return snake;
    }

    /** {@inheritDoc} */
    public Rectangle2D getMapBounds() {
        return new Rectangle(getStartPos().getX(), getStartPos().getY(), xMapSize * cellSize, ymapSize * cellSize).getBounds2D();
    }

    /** {@inheritDoc} */
    public void populateCells() {
        for (int i = -1; i <= xMapSize + 1; i++) {
            for (int j = -1; j <= ymapSize + 1; j++) {
                cells.put(new Pos(i, j),
                        new Pos(getStartPos().getX() + i * cellSize, getStartPos().getY() + j * cellSize));
            }
        }
    }

    private Position getStartPos() {
        return new Pos((this.getWidth() - xMapSize * cellSize) / 2, (this.getHeight() - ymapSize * cellSize) / 2);
    }

    /**
     * Draws the bounds of the map.
     * 
     * @param g
     */
    private void drawBounds(final Graphics g) {
        final Point2D topLeft = new Point((this.getWidth() - xMapSize * cellSize) / 2,
                                          (this.getHeight() - ymapSize * cellSize) / 2);
        final Point2D topRight = new Point((int) (topLeft.getX() + xMapSize * cellSize), (int) topLeft.getY());
        final Point2D bottomLeft = new Point((int) topLeft.getX(), (int) (topLeft.getY() + ymapSize * cellSize));
        final Point2D bottomRight = new Point((int) (topLeft.getX() + xMapSize * cellSize),
                                              (int) (topLeft.getY() + ymapSize * cellSize));
        final Set<Line2D> bounds = new HashSet<>();
        bounds.add(new Line2D.Double(topLeft, topRight));
        bounds.add(new Line2D.Double(topLeft, bottomLeft));
        bounds.add(new Line2D.Double(topRight, bottomRight));
        bounds.add(new Line2D.Double(bottomLeft, bottomRight));
        g.setColor(Color.GREEN);
        bounds.stream().forEach(line -> {
            g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
        });
    }

}
