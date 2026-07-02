package model.strategy;

import static utility.Command.DOWN;
import static utility.Command.LEFT;
import static utility.Command.RIGHT;
import static utility.Command.UP;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import model.animated.Bullet;
import model.animated.BulletImpl;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Shoots a bullet in the direction/s given.
 *
 */
public class FourWayProjectile implements ProjectileType {

    private final double delta;

    /**
     * Constructor for this class.
     * @param delta to be summed to four basic direction angles.
     */
    public FourWayProjectile(final double delta) {
        this.delta = delta;
    }

    /**
     * Create collection of bullets.
     */
    @Override
    public Collection<Bullet> shoot(final HitBox sender, final double range, final double vel,
            final ImageType bulletImg, final int damage, final double radius) {
        return Arrays.asList(UP, DOWN, LEFT, RIGHT).stream()
                .map(x -> new BulletImpl(new CircleHitBox(sender.getX(), sender.getY(), radius), vel,
                        new BulletMovement(x.getAngle() + delta), range, bulletImg, damage))
                .collect(Collectors.toList());
    }

}
