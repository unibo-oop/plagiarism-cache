package it.unibo.javajump.model.entities;

/**
 * Class implementation of the GameObject interface, representing the base class for all GameObjects in the game.
 */
public abstract class GameObjectImpl implements GameObject {
    // CHECKSTYLE: VisibilityModifier OFF
    // Rule disabled due to implementation design decision: The GameObjectImpl class is the base class
    // for all GameObjects in the game, and the properties x, y, width, and height are protected to help
    // with flexibility in effective GameObjects that extend this class. Making them protected ensures
    // that they can be accessed by subclasses without exposing them to external classes, but also
    // allows for easier subclass creation, as these base properties are available to the subclasses.
    /**
     * the x coordinate of the GameObject.
     */
    protected float x;
    /**
     * the y coordinate of the GameObject.
     */
    protected float y;
    /**
     * the width of the GameObject.
     */
    protected float width;
    /**
     * the height of the GameObject.
     */
    protected float height;
    // CHECKSTYLE: VisibilityModifier ON
    /**
     * {@inheritDoc}
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getHeight() {
        return height;
    }
}
