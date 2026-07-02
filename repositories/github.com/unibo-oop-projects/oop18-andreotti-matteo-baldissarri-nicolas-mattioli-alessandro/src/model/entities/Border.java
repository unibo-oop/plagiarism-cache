package model.entities;

import java.util.Optional;

/**
 * Models the movement boundary of an entity.
 */
public final class Border {

    private final Optional<Double> top;
    private final Optional<Double> bottom;
    private final Optional<Double> left;
    private final Optional<Double> right;

    /**
     * @param top    The optional upper border
     * @param bottom The optional lower border
     * @param left   The optional left border
     * @param right  The optional right border
     */
    public Border(final Optional<Double> top, final Optional<Double> bottom, final Optional<Double> left, final Optional<Double> right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;

    }

    /**
     * @return The optional upper border
     */
    public Optional<Double> getTop() {
        return this.top;
    }

    /**
     * @return The optional lower border
     */
    public Optional<Double> getBottom() {
        return this.bottom;
    }

    /**
     * @return The optional left border
     */
    public Optional<Double> getLeft() {
        return this.left;
    }

    /**
     * @return The optional right border
     */
    public Optional<Double> getRight() {
        return this.right;
    }

}
