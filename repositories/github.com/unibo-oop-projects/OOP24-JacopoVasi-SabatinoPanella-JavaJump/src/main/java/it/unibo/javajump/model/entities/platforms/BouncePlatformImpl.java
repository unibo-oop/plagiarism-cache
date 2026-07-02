package it.unibo.javajump.model.entities.platforms;

/**
 * The type Bounce platform.
 */
public final class BouncePlatformImpl extends PlatformImpl implements BouncePlatform {
    private final float bounceFactor;

    /**
     * Instantiates a new Bounce platform.
     *
     * @param x            the x
     * @param y            the y
     * @param width        the width
     * @param height       the height
     * @param bounceFactor the bounce factor
     */
    public BouncePlatformImpl(final float x, final float y, final float width, final float height, final float bounceFactor) {
        super(x, y, width, height);
        this.bounceFactor = bounceFactor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getBounceFactor() {
        return bounceFactor;
    }




}
