package model.inanimated;

import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Range Power Up item.
 */
public class RangeUpImpl extends AbstractInanimated implements RangeUp {

    private static final int COST = 5000;
    private static final double RANGE = 200;

    private final ImageType imgRangeUp;

    /**
     * Constructor for this class.
     * 
     * @param h
     *            hitBox.
     * @param enable
     *            .
     */
    public RangeUpImpl(final HitBox h, final boolean enable) {
        super(h, enable);
        imgRangeUp = ImageType.BULLET_RANGE_UP;
    }

    /**
     * @return the image of the power up.
     */
    @Override
    public ImageType getImageType() {
        return imgRangeUp;
    }

    /**
     * The cost of the power up.
     */
    @Override
    public int getCost() {
        return COST;
    }

    /**
     * The range to increase.
     */
    @Override
    public double getRangeUp() {
        return RANGE;
    }
}
