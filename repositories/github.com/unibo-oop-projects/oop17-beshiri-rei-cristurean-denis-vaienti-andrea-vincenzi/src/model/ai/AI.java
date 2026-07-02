package model.ai;

import java.util.Collection;

import model.animated.Bullet;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import model.strategy.MovementStrategy;
import model.strategy.ProjectileType;
import utility.ImageType;

/**
 * Interface that represent AI for entity in game. This interface encapsulate
 * the movement logic and the shoot logic.
 * 
 */
public interface AI {

    /**
     * Method that realize movement.
     * 
     * @param dt
     *            DeltaTime.
     * @param vel
     *            Velocity.
     * @param hBox
     *            HitBox.
     * @return HitbBox of entity in the new position.
     */
    HitBox move(double dt, double vel, CircleHitBox hBox);

    /**
     * Return the collections of bullet shoted by entity.
     * 
     * @param hBox
     *            HitBox of entity where the bullet start.
     * @param vel
     *            Velocity.
     * @param range
     *            Range of bullet.
     * @param bulletImg
     *            Image of the bullet.
     * @param damage
     *            Damage of the bullet.
     * @param bulletRadius
     *            Radius of the bullet.
     * @return The collection of bullet shoted by entity.
     */
    Collection<Bullet> shoot(HitBox hBox, double vel, double range, ImageType bulletImg, int damage,
            double bulletRadius);

    /**
     * Set new type of movement.
     * 
     * @param mS
     *            New movement strategy.
     */
    void setMovementStrategy(MovementStrategy mS);

    /**
     * Set new Projectile type.
     * 
     * @param pT
     *            New projectile type.
     */
    void setProjectileType(ProjectileType pT);

    /**
     * Decide next phase.
     * 
     * @param life
     *            Remain life.
     */
    void nextPhaseStrategy(int life);

    /**
     * Getter for actual projectile type.
     * 
     * @return proj type.
     */
    ProjectileType getProjType();

    /**
     * Getter for actual movement strategy.
     * 
     * @return actual movement strategy.
     */
    MovementStrategy getMovementStrategy();
}
