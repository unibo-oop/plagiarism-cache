package model.strategies;

import java.util.ArrayList;
import java.util.Collection;

import model.entities.Bullet;
import model.entities.BulletImpl;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;

/**
 * Shoots a cross of bullets.
 */
public class ShootCross implements ShootingType {

    /**
     * 
     */
    private static final long serialVersionUID = -4198166860927528270L;
    private double limitfireRate;

    /**
     * Constructs a new instance of ShootCross.
     */
    public ShootCross() {
        limitfireRate = 1;

    }

    @Override
    public Collection<Bullet> shoot(final Hitbox from, final double delta, final double fireRate, final double damage,
            final double range, final double steps) {
        final Collection<Bullet> cb = new ArrayList<>();

        if (this.limitfireRate <= 0) {
            this.limitfireRate = fireRate;

            cb.add(new BulletImpl(new HitboxCircle(from.getX(), from.getY(), 10), steps, damage, new BulletMovement(0), range));
            cb.add(new BulletImpl(new HitboxCircle(from.getX(), from.getY(), 10), steps, damage, new BulletMovement(90), range));
            cb.add(new BulletImpl(new HitboxCircle(from.getX(), from.getY(), 10), steps, damage, new BulletMovement(90 * 2),
                    range));
            cb.add(new BulletImpl(new HitboxCircle(from.getX(), from.getY(), 10), steps, damage, new BulletMovement(90 * 3),
                    range));

        } else {
            this.limitfireRate -= delta;
        }

        return cb;
    }

}