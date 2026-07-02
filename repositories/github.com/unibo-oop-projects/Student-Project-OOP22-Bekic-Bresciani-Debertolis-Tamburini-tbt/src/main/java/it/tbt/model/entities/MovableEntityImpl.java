package it.tbt.model.entities;

/**
 * Generic entity with a variable position in space.
 */
public class MovableEntityImpl extends EntityImpl implements MovableEntity {
    private int x;
    private int y;
    private final int width;
    private final int height;

    /**
     * Default constructor.
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public MovableEntityImpl(
        final String name,
        final int x,
        final int y,
        final int width,
        final int height
    ) {
        super(name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get X coordinate.
     * @return X coordinate
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Get Y coordinate.
     * @return Y coordinate
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Set X coordinate.
     * @param x
     */
    @Override
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Set Y coordinate.
     * @param y
     */
    @Override
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Get height.
     * @return height
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Get width.
     * @return width
     */
    @Override
    public int getWidth() {
        return width;
    }
}
