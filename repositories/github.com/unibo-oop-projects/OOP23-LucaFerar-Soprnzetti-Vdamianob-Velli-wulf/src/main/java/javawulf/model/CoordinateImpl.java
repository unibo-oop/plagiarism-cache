package javawulf.model;

import java.awt.Point;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of Coordinate.
 */
public final class CoordinateImpl implements Coordinate {

    private Point position;

    /**
     * Creates a new coordinate.
     * 
     * @param x The position on the X-axis
     * @param y the position on the Y-axis
     */
    public CoordinateImpl(final int x, final int y) {
        this.position = new Point(x, y);
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "getPosition is used inside of test classes for easier testing"
    )
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final int x, final int y) {
        this.position = new Point(x, y);
    }

    @Override
    public Integer getX() {
        return (int) this.position.getX();
    }

    @Override
    public Integer getY() {
        return (int) this.position.getY();
    }
}
