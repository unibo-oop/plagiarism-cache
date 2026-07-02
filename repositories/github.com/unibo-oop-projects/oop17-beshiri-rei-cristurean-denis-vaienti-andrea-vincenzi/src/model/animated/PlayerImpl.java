package model.animated;

import static utility.ImageType.PLAYER_BULLET;

import java.util.Collection;
import model.ai.AI;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Implementation of the Player.
 */
public class PlayerImpl extends AbstractCharacter implements Player {

    /**
     * 
     * @param v
     *            Player's velocity.
     * @param life
     *            Player's life.
     * @param h
     *            HitBox.
     * @param ai
     *            Artificial Intelligence.
     * @param img
     *            Image for player.
     * @param ratio
     *            shoot ratio for payer.
     * @param bulletRadius
     *            Radius of bullet for this entity.
     * @param bulletVel
     *            Bullet vel for this entity.
     * @param bulletRange
     *            Bullet range for this entity.
     * @param bulletDamage
     *            Bullet damage for this entity.
     */
    public PlayerImpl(final double v, final int life, final HitBox h, final AI ai, final ImageType img,
            final double ratio, final double bulletRadius, final double bulletVel, final double bulletRange,
            final int bulletDamage) {
        super(v, life, h, ai, img, ratio, bulletRadius, bulletVel, bulletRange, bulletDamage);
    }

    /**
     * Player Movement.
     */
    @Override
    public void move(final double dt) {
        // Safe-casting.
        super.setHitBox(super.getAI().move(dt, super.getVel(), (CircleHitBox) super.getHitBox()));
    }

    /**
     * Player shoot.
     */
    @Override
    public Collection<Bullet> shoot() {
        return super.getAI().shoot(super.getHitBox(), super.getBulletVel(), super.getBulletRange(), PLAYER_BULLET,
                super.getBulletDamage(), super.getBulletRadius());
    }
}
