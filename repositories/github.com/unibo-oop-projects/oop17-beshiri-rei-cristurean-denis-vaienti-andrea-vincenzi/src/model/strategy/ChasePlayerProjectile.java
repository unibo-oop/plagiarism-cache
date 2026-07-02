package model.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.animated.Bullet;
import model.animated.BulletImpl;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Class that represent movement of the bullet that follow player.
 *
 */
public class ChasePlayerProjectile implements ProjectileType {

    /**
     * Shoot bullet/s.
     */
    @Override
    public Collection<Bullet> shoot(final HitBox sender, final double range, final double vel,
            final ImageType bulletImg, final int damage, final double radius) {
        final List<Bullet> list = new ArrayList<>();
        list.add(new BulletImpl(new CircleHitBox(sender.getX(), sender.getY(), radius), vel, new FollowPlayerMovement(),
                range, bulletImg, damage));
        return list;
    }

}
