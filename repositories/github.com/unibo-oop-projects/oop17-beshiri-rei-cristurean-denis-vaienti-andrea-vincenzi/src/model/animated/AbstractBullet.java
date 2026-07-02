package model.animated;

import java.util.Collection;
import java.util.Collections;
import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Abstract class that acts as a filter between Animated and BulletImpl.
 *
 */
public abstract class AbstractBullet implements Animated {

    private double vel;
    private HitBox hb;
    private final ImageType img;

    /**
     * Constructor that initialize the common variables.
     * @param vel
     *          The speed of the entity.
     * @param hb
     *          The HitBox of the entity.
     * @param img
     *          Image for the bullet.
     */
    public AbstractBullet(final double vel, final HitBox hb, final ImageType img) {
       this.vel = vel;
       this.hb = hb;
       this.img = img;
    }

    /**
     * Getter for HitBox.
     */
    @Override
    public HitBox getHitBox() {
        return hb;
    }

    /**
     * Setter for HitBox.
     */
    @Override
    public void setHitBox(final HitBox hb) {
        this.hb = hb;
    }

    /**
     * Getter for velocity of bullet.
     */
    @Override
    public double getVel() {
        return vel;
    }

    /**
     * Setter for velocity.
     */
    @Override
    public void setVel(final double vel) {
        this.vel = vel;

    }

    /**
     * Traveled distance during the update method.
     * @param dt
     *          Delta Time.
     * @return
     *          The HitBox in the new position.
    */
    protected abstract HitBox deltaDistance(double dt);

    /**
     * Shot bullets of entity. (Template Method).
     * Empty because bullet can't shoot.
     */
    @Override
    public Collection<Bullet> shot() { 
        return Collections.emptyList();
    }

    /**
     * Update method used to perform move and chenge bullet position.
     */
    @Override
    public void update(final double dt) {
        final HitBox position = deltaDistance(dt);
        this.hb.changePosition(position.getX(), position.getY());
    }

    /**
     * Getter for image that represent bullet.
     */
    @Override
    public ImageType getImageType() {
        return this.img;
    }
}
