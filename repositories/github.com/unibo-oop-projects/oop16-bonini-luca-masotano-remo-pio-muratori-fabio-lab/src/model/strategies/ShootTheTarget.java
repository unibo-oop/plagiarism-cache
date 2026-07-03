package model.strategies;

import java.util.ArrayList;
import java.util.Collection;

import model.entities.Bullet;
import model.entities.BulletImpl;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;
import model.utils.ModelVariables;

/**
 * 
 * Defines the ShootTheTarget strategy. ShootTheTarget is used to shoot the
 * player.
 */
public class ShootTheTarget implements ShootingType {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 3781962509028827625L;
    private double limitfireRate;

    /**
     * 
     * Constructs a new instance of ShootTheTarget.
     */
    public ShootTheTarget() {
        limitfireRate = 1;
    }

    @Override
    public Collection<Bullet> shoot(final Hitbox from, final double delta, final double fireRate, final double damage,
            final double range, final double steps) {
        final Collection<Bullet> cb = new ArrayList<>();

        if (this.limitfireRate <= 0) {
            this.limitfireRate = fireRate;
            final double angle = Math.toDegrees(Math.atan2(ModelVariables.getPlayerHitbox().getX() - from.getX(),
                    -(ModelVariables.getPlayerHitbox().getY() - from.getY())));

            cb.add(new BulletImpl(new HitboxCircle(from.getX(), from.getY(), 10), steps, damage,
                    new BulletMovement(angle), range));

        } else {
            this.limitfireRate -= delta;
        }

        return cb;
    }

}