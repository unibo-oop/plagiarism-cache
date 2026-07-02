package model.inanimated;

import model.hitbox.HitBox;

/**
 * Abstract class for inanimate entity.
 *
 */
public abstract class AbstractInanimated implements Inanimated {

    private boolean enable;
    private HitBox hitBox;

    /**
     * Constructor for this class.
     * 
     * @param h
     *            HitBox.
     * @param enable
     *            State of the object.
     */
    public AbstractInanimated(final HitBox h, final boolean enable) {
        this.enable = enable;
        hitBox = h;
    }

    /**
     * Return if object is enable.
     * 
     * @return Return if the Object is enable or not.
     */
    protected boolean isEnable() {
        return this.enable;
    }

    /**
     * Setter for enable of object.
     * 
     * @param enable
     *            State of the object.
     */
    protected void setEnable(final boolean enable) {
        this.enable = enable;
    }

    /**
     * Return HitBox.
     */
    @Override
    public HitBox getHitBox() {
        return this.hitBox;
    }

    /**
     * Set HitBox.
     */
    @Override
    public void setHitBox(final HitBox h) {
        this.hitBox = h;
    }
}
