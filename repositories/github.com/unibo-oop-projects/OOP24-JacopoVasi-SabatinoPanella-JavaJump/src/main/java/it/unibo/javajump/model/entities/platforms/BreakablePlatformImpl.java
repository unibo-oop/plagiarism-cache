package it.unibo.javajump.model.entities.platforms;


/**
 * The type Breakable platform.
 */
public final class BreakablePlatformImpl extends PlatformImpl implements BreakablePlatform {

    private boolean broken;
    private boolean finished;

    /**
     * Instantiates a new Breakable platform.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public BreakablePlatformImpl(final float x, final float y, final float width, final float height) {
        super(x, y, width, height);
        this.broken = false;
        this.finished = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBroken() {
        return broken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void breakPlatform() {
        if (!broken) {
            broken = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinished() {
        return finished;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFinished() {
        if (!finished) {
            finished = true;
        }
    }
}
