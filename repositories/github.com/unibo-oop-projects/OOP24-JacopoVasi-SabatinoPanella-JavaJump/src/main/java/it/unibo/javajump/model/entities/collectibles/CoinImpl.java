package it.unibo.javajump.model.entities.collectibles;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.platforms.Platform;

import static it.unibo.javajump.utility.Constants.OFFSET_INIT;

/**
 * Coin class, implementing the Coin interface, representing the collectible coin entity.
 */
public final class CoinImpl extends GameObjectImpl implements Coin {

    /**
     * Field representing the state of the coin.
     */
    private CoinState state;
    /**
     * Field to store the platform to which the coin is attached.
     */
    private Platform attachedPlatform;
    /**
     * Field to store the X offset.
     */
    private float offsetX;

    /**
     * Constructor for the CoinImpl class.
     *
     * @param x      the x-coordinate of the coin
     * @param y      the y-coordinate of the coin
     * @param width  the width of the coin
     * @param height the height of the coin
     */
    public CoinImpl(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = CoinState.IDLE;
        this.attachedPlatform = null;
        this.offsetX = OFFSET_INIT;
    }

    /**
     * {@inheritDoc}
     * In this case, if the Coin is attached to a platform, the coin moves with the platform during gameplay.
     */
    @Override
    public void update(final float deltaTime) {
        if (attachedPlatform != null) {
            this.x = attachedPlatform.getX() + offsetX;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final GameObject other) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CoinState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collect() {
        if (this.state == CoinState.IDLE) {
            this.state = CoinState.COLLECTING;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markAsDone() {
        this.state = CoinState.FINISHED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform getAttachedPlatform() {
        return attachedPlatform;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachToPlatform(final Platform platform) {
        this.attachedPlatform = platform;
        this.offsetX = this.x - platform.getX();
    }
}
