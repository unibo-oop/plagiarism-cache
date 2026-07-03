package com.jlearn.model.utilities;

/**
 * An umodifiable, generic {@link Pair}, with getters, hashCode, equals, and toString well implemented.
 *
 * @param <X>
 *            the type of the first element
 * @param <Y>
 *            the type of the second element
 */
public final class Pair<X, Y> {

    private X x;
    private Y y;

    /**
     * @param x
     *            the first value
     * @param y
     *            the second value
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return first value
     */
    public X getX() {
        return this.x;
    }

    /**
     * @return second value
     */
    public Y getY() {
        return this.y;
    }

    /**
     * changes first value.
     *
     * @param x
     *            the first value
     */
    public void setX(final X x) {
        this.x = x;
    }

    /**
     * changes second value.
     *
     * @param y
     *            the second value
     */
    public void setY(final Y y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return this.x.hashCode() ^ this.y.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Pair ? this.x.equals(((Pair<?, ?>) obj).x) && this.y.equals(((Pair<?, ?>) obj).y) : false;
    }

    @Override
    public String toString() {
        return "<" + this.x + ", " + this.y + ">";
    }

}
