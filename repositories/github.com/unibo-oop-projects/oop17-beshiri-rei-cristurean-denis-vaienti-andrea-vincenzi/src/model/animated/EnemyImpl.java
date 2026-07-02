package model.animated;

import java.util.Collection;

import model.ai.AI;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Class that represent generic enemy.
 *
 */
public class EnemyImpl extends AbstractCharacter implements Enemy {

    private final int points;
    private final ImageType bulletImg;

    /**
     * Constructor for enemy.
     * 
     * @param v
     *            Vel.
     * @param life
     *            Life.
     * @param h
     *            hitBox.
     * @param ai
     *            AI.
     * @param points
     *            Points.
     * @param img
     *            image for this enemy.
     * @param ratio
     *            shoot ratio for this enemy.
     * @param bulletImage
     *            Bullet Image for this enemy.
     * @param bulletRadius
     *            Radius of bullet for this entity.
     * @param bulletVel
     *            Bullet vel for this entity.
     * @param bulletRange
     *            Bullet range for this entity.
     * @param bulletDamage
     *            Bullet damage for this entity.
     */
    public EnemyImpl(final double v, final int life, final HitBox h, final AI ai, final int points, final ImageType img,
            final double ratio, final ImageType bulletImage, final double bulletRadius, final double bulletVel,
            final double bulletRange, final int bulletDamage) {
        super(v, life, h, ai, img, ratio, bulletRadius, bulletVel, bulletRange, bulletDamage);
        this.points = points;
        this.bulletImg = bulletImage;
    }

    /**
     * Return points.
     */
    @Override
    public int getPoint() {
        return points;
    }

    /**
     * Perform enemy movement.
     */
    @Override
    protected void move(final double dt) {
        super.setHitBox(super.getAI().move(dt, super.getVel(), (CircleHitBox) super.getHitBox()));
    }

    /**
     * Shot bullet/bullets.
     */
    @Override
    protected Collection<Bullet> shoot() {
        return super.getAI().shoot(super.getHitBox(), super.getBulletVel(), super.getBulletRange(), bulletImg,
                super.getBulletDamage(), super.getBulletRadius());
    }
}
