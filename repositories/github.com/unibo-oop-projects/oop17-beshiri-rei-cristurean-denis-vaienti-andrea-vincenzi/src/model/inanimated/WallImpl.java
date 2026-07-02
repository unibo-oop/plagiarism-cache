package model.inanimated;

import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Walls class.
 */
public class WallImpl extends AbstractInanimated implements Wall {

    private final ImageType imgWall;

    /**
     * Constructor for this class.
     * 
     * @param hb
     *            HitBox
     * @param open
     *            always false.
     * @param imgWall
     *            image of the wall.
     */
    public WallImpl(final HitBox hb, final boolean open, final ImageType imgWall) {
        super(hb, open);
        this.imgWall = imgWall;
    }

    /**
     * Cannot set HitBox after creating the wall.
     */
    @Override
    public void setHitBox(final HitBox h) {
    }

    /**
     * Get Image type.
     */
    @Override
    public ImageType getImageType() {
        return this.imgWall;
    }
}
