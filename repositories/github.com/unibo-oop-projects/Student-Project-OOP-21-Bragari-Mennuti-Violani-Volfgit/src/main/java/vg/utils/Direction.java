package vg.utils;

public enum Direction {
    /**
     * Left.
     */
    LEFT(-1, 0),
    /**
     * Right.
     */
    RIGHT(1, 0),
    /**
     * Up.
     */
    UP(0, -1),
    /**
     * Down.
     */
    DOWN(0, 1),
    /**
     * No direction. No movement.
     */
    NONE(0, 0);

    /**
     * Horizontal coordinate.
     */
    private final double x;
    /**
     * Vertical coordinate.
     */
    private final double y;
    Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    /**
     * Get direction as 2D vector.
     * @return V2D vector
     */
    public V2D getVector() {
        return new V2D(this.x, this.y);
    }
}
