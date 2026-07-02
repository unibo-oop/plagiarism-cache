package model.utilities;

import java.io.Serializable;

/**
 * Represents a vector for the direction in the plane.
 */
public class DirVector implements Serializable {

    private static final long serialVersionUID = -5517208558432027459L;
    private final double x;
    private final double y;

    public DirVector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x of the vector
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y of the vector
     */
    public double getY() {
        return y;
    }

    /**
     * @param v vector to sum 
     * @return sum vector
     */
    public DirVector sum(final DirVector v) {
        return new DirVector(x + v.x, y + v.y);
    }

    /**
     * 
     * @return the hypotenuse (vector) of the two catheti (x, y) using the Pythagorean theorem
     */
    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * 
     * @return normalize the vector
     */
    public DirVector getNormalized() {
        final double module = (double) Math.sqrt(x * x + y * y);
        return new DirVector(x / module, y / module);
    }

    /**
     * 
     * @param number used to multiply the vector
     * @return the resulting vector
     */
    public DirVector mul(final double number) {
        return new DirVector(x * number, y * number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DirVector(" + x + "," + y + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DirVector other = (DirVector) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        } else if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }


}
