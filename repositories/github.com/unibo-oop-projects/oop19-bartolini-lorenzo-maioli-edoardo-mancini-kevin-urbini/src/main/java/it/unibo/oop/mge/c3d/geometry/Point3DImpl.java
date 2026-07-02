package it.unibo.oop.mge.c3d.geometry;

import java.util.function.Function;

/**
 * 
 * Base implementation of Point3D.
 *
 */
public class Point3DImpl implements Point3D {

    private final double x;
    private final double y;
    private final double z;

    // package protected
    Point3DImpl(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
        final Point3DImpl other = (Point3DImpl) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return true;
    }

    @Override
    public final double getX() {
        return x;
    }

    @Override
    public final double getY() {
        return y;
    }

    @Override
    public final double getZ() {
        return z;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public final Point3D rotated(final double angleXY, final double angleYZ) {
        return this.rotatedXY(angleXY).rotatedYZ(angleYZ);
    }

    private Point3DImpl rotatedXY(final double angle) {
        return new Point3DImpl(this.getX() * Math.cos(angle) - this.getY() * Math.sin(angle),
                this.getX() * Math.sin(angle) + this.getY() * Math.cos(angle), this.getZ());
    }

    private Point3DImpl rotatedYZ(final double angle) {
        return new Point3DImpl(this.getX(), this.getY() * Math.cos(angle) - this.getZ() * Math.sin(angle),
                this.getY() * Math.sin(angle) + this.getZ() * Math.cos(angle));
    }

    @Override
    public final String toString() {
        return " [x=" + x + ", y=" + y + ", z=" + z + "] ";
    }

    @Override
    public final Point3D transformed(final Function<Double, Double> transformation) {
        return new Point3DImpl(transformation.apply(this.x), transformation.apply(this.y),
                transformation.apply(this.z));
    }

    @Override
    public final Point3D translated(final double x, final double y, final double z) {
        return new Point3DImpl(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public final Point3D translated(final Point3D vector) {
        return new Point3DImpl(this.getX() + vector.getX(), this.getY() + vector.getY(), this.getZ() + vector.getZ());
    }

}
