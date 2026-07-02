package it.unibo.javajump.model.entities.platforms;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;

/**
 * The implementation PlatformImpl, implements Platform interface.
 */
public class PlatformImpl extends GameObjectImpl implements Platform {
    private boolean touched;

    /**
     * Instantiates a new Platform.
     *
     * @param x      the x position
     * @param y      the y position
     * @param width  the width
     * @param height the height
     */
    public PlatformImpl(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.touched = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void triggerTouched() {
        touched = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean consumeTouched() {
        if (touched) {
            touched = false;
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final float deltaTime) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final GameObject other) {
    }

}
