package model.strategy;

import java.util.Collection;
import model.animated.Bullet;
import model.hitbox.HitBox;
import utility.ImageType;

/**
 * Represents all the type of projectiles in the game.
 *
 */
public interface ProjectileType {

    /**
     * Determines the bullets shot and the way they are shot.
     * 
     * @param sender
     *            The hitbox where the bullet starts.
     * @param range
     *            The range of the bullet.
     * @param vel
     *            The speed of the bullet.
     * @param bulletImg
     *            Bullet image.
     * @param damage
     *            Bullet damage.
     * @param radius
     *            Bullet radius.
     * @return The bullet that has been shot.
     */
    Collection<Bullet> shoot(HitBox sender, double range, double vel, ImageType bulletImg, int damage, double radius);
}
