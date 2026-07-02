package it.unibo.oop.manpac.model.score;

import java.util.Objects;

/**
 * Implementation of Points interface (positive/negative value).<br>
 * N.B: This class implements equals and hashCode by calling the equals and
 * hashCodes of the X type: make sure that these methods are implemented in the
 * class used in the generic X.
 * 
 * @param <X> Type of points. The use of an immutable type (eg Integer, String,
 *            etc.) is highly recommended.<br>
 *            The type MUST extend java.io.Serializable.
 */
public class PointsImpl<X extends java.io.Serializable> implements Points<X> {

    // serial version of ImplPoints
    private static final long serialVersionUID = -4122727339689804855L;

    // value of Points
    private X value;

    /**
     * Constructor of PointsImpl.
     * 
     * @param value points of entity
     */
    public PointsImpl(final X value) {
        if (checkCorrectInput(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}<br>
     * WARNING: the copy of {@link Points} returned does not have
     * a copy of the value, but the value itself! Be careful if the value is not
     * "immutable".
     */
    @Override
    public Points<X> getCopy() {
        return new PointsImpl<X>(this.value);
    }

    // checker for null parameters
    private boolean checkCorrectInput(final X value) {
        return (Objects.nonNull(value));
    }

    /**
     * To get the hash code.
     * 
     * @return a hash code value of {@link Points}
     */
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    /**
     * Indicates whether some other {@link Points} is "equal to" this one.
     * 
     * @param obj the reference {@link Points} with which to compare
     * @return true if this {@link Points} is the same value, false otherwise
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        if (Objects.nonNull(obj) && this.getClass() == obj.getClass()) {
            return this.value.equals(((Points<X>) obj).getValue());
//          return this.points == ((Points) obj).getPoints();
        }
        return false;
    }

    /**
     * Returns a string representation of the {@link Points}.
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Points [points = ").append(this.value).append("]").toString();
    }

    /**
     * Implementation of Mutable points. The only difference from {@link PointsImpl}
     * is that it is not immutable and the value can be changed. See
     * {@link PointsImpl} for more description.
     * 
     * @param <X> Type of points. See {@link PointsImpl} for more description.
     */
    public static class Mutable<X extends java.io.Serializable> extends PointsImpl<X> {

        // serial version of ImplPoints.Mutable
        private static final long serialVersionUID = 5165674865735879707L;

        /**
         * Constructor of Mutable points.
         * 
         * @param value points of entity
         */
        public Mutable(final X value) {
            super(value);
        }

        /**
         * Setter of points.
         * 
         * @param value set points
         */
        public void setPoints(final X value) {
            if (super.checkCorrectInput(value)) {
                super.value = value;
            } else {
                throw new IllegalArgumentException();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Points<X> getCopy() {
            return new PointsImpl.Mutable<X>(super.value);
        }
    }
}
