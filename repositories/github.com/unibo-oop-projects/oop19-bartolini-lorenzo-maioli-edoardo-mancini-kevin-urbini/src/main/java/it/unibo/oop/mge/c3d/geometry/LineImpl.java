package it.unibo.oop.mge.c3d.geometry;

/**
 * 
 * Base implementation of Line.
 *
 */
public class LineImpl implements Line {
    private final double m;
    private final double q;
    private final double zero;

    // package protected
    LineImpl(final Point2D a, final Point2D b) {

        if (a.getX() == b.getX()) {
            this.m = Double.POSITIVE_INFINITY;
            this.q = 0;
            this.zero = a.getX();
        } else {
            this.m = (a.getY() - b.getY()) / (a.getX() - b.getX());
            this.q = a.getY() - this.m * a.getX();
            if (m == 0) {
                this.zero = Double.NaN;
            } else {
                this.zero = -this.q / this.m;
            }
        }
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineImpl other = (LineImpl) obj;
        if (Double.doubleToLongBits(m) != Double.doubleToLongBits(other.m)) {
            return false;
        }
        if (Double.doubleToLongBits(q) != Double.doubleToLongBits(other.q)) {
            return false;
        }
        if (Double.doubleToLongBits(zero) != Double.doubleToLongBits(other.zero)) {
            return false;
        }
        return true;
    }

    @Override
    public final double getM() {
        return m;
    }

    @Override
    public final double getQ() {
        return q;
    }

    @Override
    public final double getZero() {
        return zero;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(m);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(q);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(zero);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public final double solveFor(final double x) {
        return this.m * x + this.q;
    }

}
