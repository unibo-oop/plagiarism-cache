package model.ai;

import java.util.Collection;

import model.entities.Bullet;
import model.hitbox.HitboxImpl;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;
import model.strategies.MovementStrategy;
import model.strategies.ShootingType;

/**
 * 
 */
public class StandardAI implements AI {

    /**
     * 
     */
    private static final long serialVersionUID = 5386263395916819220L;
    private MovementStrategy currentMS;
    private ShootingType currentST;

    /**
     * Construct a new instance of Standard AI.
     * 
     * @param ms
     *            Movement Strategy
     * @param st
     *            Shooting Type
     */
    public StandardAI(final MovementStrategy ms, final ShootingType st) {
        this.currentMS = ms;
        this.currentST = st;
    }

    @Override
    public void decide(final int currentHealth) {

    }

    @Override
    public Collection<Bullet> shoot(final Hitbox from, final double delta, final double fireRate, final double damage,
            final double range, final double steps) {
        return currentST.shoot(from, delta, fireRate, damage, range, steps);
    }

    @Override
    public HitboxImpl move(final HitboxCircle h, final double steps, final double delta) {
        return currentMS.movement(h, steps, delta);
    }

    /**
     * Sets a new MovementStrategy.
     * 
     * @param ms
     *            movement strategy
     */
    protected void setMovement(final MovementStrategy ms) {
        currentMS = ms;
    }

    /**
     * Sets a new ShootingType.
     * 
     * @param st
     *            shooting type
     */
    protected void setShoot(final ShootingType st) {
        currentST = st;
    }

}
