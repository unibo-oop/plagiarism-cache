package model.strategies;

import java.util.ArrayList;
import java.util.Collection;

import model.entities.Bullet;
import model.entities.BulletImpl;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;
import model.utils.Direction;
import model.utils.ModelVariables;

/**
 * 
 * Defines the shooting type followed by the player.
 *
 */
public class InputShoot implements ShootingType {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 5877810118595591688L;
    private double limitFireRate;

    /**
     * Constructs a new instance of InputShoot.
     */
    public InputShoot() {
        this.limitFireRate = 1;
    }

    @Override
    public Collection<Bullet> shoot(final Hitbox from, final double delta, final double fireRate, final double damage, final double range, final double steps) {
        final Collection<Bullet> cb = new ArrayList<>();

        if (this.limitFireRate <= 0) {

            final HitboxCircle c = new HitboxCircle(from.getX(), from.getY(), 10);
            if (!ModelVariables.getShootDirection().isEmpty()) {
                this.limitFireRate = fireRate;

                final Direction[] d = ModelVariables.getShootDirection().toArray(new Direction[4]);
                final int size = ModelVariables.getShootDirection().size() - 1;
                final double angle = d[size].getAngle();

                cb.add(new BulletImpl(c, steps, damage, new BulletMovement(angle), range));
            }

        } else {
            this.limitFireRate -= delta;
        }

        return cb;
    }

}
