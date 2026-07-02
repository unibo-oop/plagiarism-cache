package vg.utils;

import com.sun.javafx.geom.Vec2d;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class V2D implements Serializable {
    /**
     * X: Horizontal coordinate.
     */
    private final double x;
    /**
     * Y: Vertical coordinate.
     */
    private final double y;

    public V2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor that builds a new V2D equal
     * to the one that is passed.
     * @param equal the V2D point which coordinates
     *              will be used to create the new one
     */
    public V2D(final V2D equal) {
        this.x = equal.getX();
        this.y = equal.getY();
    }
    public final V2D sum(final V2D pos) {
        return new V2D(this.x + pos.x, this.y + pos.y);
    }

    public final V2D diff(final V2D pos) {
        return new V2D(this.x - pos.x, this.y - pos.y);
    };

    public final V2D mul(final V2D pos) {
        return new V2D(this.x * pos.getX(), this.y * pos.getY());
    }

    public final V2D mul(final double x, final double y) {
        return new V2D(this.x * x, this.y * y);
    }

    public final V2D scalarMul(final double scalar) {
        return this.mul(new V2D(scalar, scalar));
    }

    /**
     * @return vector with absolute value of coordinates
     * */
    public final V2D removeSign() {
        return new V2D(abs(this.getX()), abs(this.getY()));
    }

    /**
     * Change coordinate sign to the passed signs of signVector
     * Each nth-coordinate of current vector gets sing of nth-sign of passed vector.
     * @param signVector vector to get signs of coordinates
     * @return a {@link V2D} with the updated sign
     */
    public final V2D updateSign(final V2D signVector) {
        //remove signs
        V2D absVector = this.removeSign();
        double xSign = signVector.getX() < 0 ? -1 : 1;
        double ySign = signVector.getY() < 0 ? -1 : 1;
        return absVector.mul(xSign, ySign);
    }

    /**
     * Return a new vector where if coordinates are sign of vector coordinate. So they can assume only values 1, -1 and 0.
     * @return Vector which coordinate can only assume values 1, -1, 0
     */
    public final V2D getSingVector() {
        int x = getValueSign(this.getX());
        int y = getValueSign(this.getY());
        return new V2D(x, y);
    }

    private int getValueSign(final double value) {
        if (value > 0) {
            return 1;
        } else if (value < 0) {
            return -1;
        } else {
            return  0;
        }
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    public final Vec2d getVec2d() {
        return new Vec2d(this.getX(), this.getY());
    }

    /**
     * Print position coordinates.*/
    @Override
    public String toString() {
        return MessageFormat.format("Position'{'x={0}, y={1}'}'", x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof V2D)) {
            return false;
        }
        V2D v2D = (V2D) o;
        return Double.compare(v2D.getX(), getX()) == 0 && Double.compare(v2D.getY(), getY()) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    /**
     * Checks if the argument is adjacent.
     * @param other the other {@link V2D}
     * @return true if the difference between the two points
     * is either (-1,0) (0,-1) (1,0) or (0,1)
     */
    public boolean isAdj(final V2D other) {
        return Stream.of(-1, 1).flatMap(e -> Stream.of(new V2D(e, 0), new V2D(0, e))).anyMatch(e -> e.equals(other.sum(this.scalarMul(-1))));
    }
}
