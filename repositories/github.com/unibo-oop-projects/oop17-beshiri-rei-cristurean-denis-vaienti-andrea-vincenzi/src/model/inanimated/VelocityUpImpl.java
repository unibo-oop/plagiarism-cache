package model.inanimated;

import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Velocity power up item.
 */
public class VelocityUpImpl extends AbstractInanimated implements VelocityUp {

    private static final int COST = 5000;
    private static final double VEL = 10;

    private final ImageType imgVelocity;

    /**
     * Constructor for this class.
     * 
     * @param h
     *            hb.
     * @param enable
     *            Velocity power up status (true).
     */
    public VelocityUpImpl(final HitBox h, final boolean enable) {
        super(h, enable);
        imgVelocity = ImageType.SPEED_UP;
    }

    /**
     * @return the img of the item.
     */
    @Override
    public ImageType getImageType() {
        return imgVelocity;
    }

    /**
     * @return the cost of the item.
     */
    @Override
    public int getCost() {
        return COST;
    }

    /**
     * @return the speed to increase.
     */
    @Override
    public double getVelocity() {
        return VEL;
    }
}
