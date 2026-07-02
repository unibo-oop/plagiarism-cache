package main.java.com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.com.utility.Pos;
import main.java.com.utility.Position;

/**
 * This class is the view for the snake game entity. The snake is represented as
 * a list of Rectangles.
 *
 */
public class SnakeViewImpl implements SnakeView {

    private final List<Rectangle> body;
    private final int cellSize;
    private final Map<Position, Position> cells;

    /**
     * 
     * @param c maps the all the possible positions on the game map to graphical
     *          coordinates on the screen
     * @param s the size of a single cell in pixels
     */
    public SnakeViewImpl(final Map<Position, Position> c, final int s) {
        body = new ArrayList<>();
        cellSize = s;
        cells = c;
    }

    /** {@inheritDoc} */
    public List<Rectangle> getBody() {
        return body;
    }

    /** {@inheritDoc} */
    public List<Rectangle> getBodyRects() {
        final List<Rectangle> b = new ArrayList<>();
        final List<Rectangle> bodyView = new ArrayList<>(body);
        bodyView.remove(0);
        for (final Rectangle r : bodyView) {
            final Position p = cells.get(new Pos((int) r.getX(), (int) r.getY()));
            b.add(new Rectangle(p.getX(), p.getY(), (int) r.getWidth() + 1, (int) r.getHeight() + 1));
        }
        return b;
    }

    /** {@inheritDoc} */
    public Point2D getHeadCenter() {
        final Position p = new Pos((int) body.get(0).getX(), (int) body.get(0).getY());
        final Rectangle head = new Rectangle(cells.get(p).getX(), cells.get(p).getY(), cellSize + 1, cellSize + 1);
        return new Point((int) (head.getLocation().getX() + head.getWidth() / 2),
                         (int) (head.getLocation().getY() + head.getHeight() / 2));
    }

    /** {@inheritDoc} */
    public void setBody(final List<Position> list) {
        body.clear();
        list.stream().forEach(p -> {
            body.add(new Rectangle(new Point(p.getX(), p.getY()), new Dimension(cellSize, cellSize)));
        });
    }

    /** {@inheritDoc} */
    public void draw(final Graphics g) {
        g.setColor(Color.WHITE);
        body.stream().forEach(r -> {
            final Position p = new Pos((int) r.getLocation().getX(), (int) r.getLocation().getY());
            g.fillRect(cells.get(p).getX(), cells.get(p).getY(), cellSize + 1, cellSize + 1);
        });
    }
}
