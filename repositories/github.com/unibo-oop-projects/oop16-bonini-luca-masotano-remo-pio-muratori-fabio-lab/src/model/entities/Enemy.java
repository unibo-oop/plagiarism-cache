package model.entities;

import model.ai.AI;
import model.hitbox.HitboxCircle;

/**
 * 
 * Represents all the types of enemy.
 *
 */
public class Enemy extends AbstractCharacter {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 7584451913576603917L;

    /**
     * The constructor of the class Enemy.
     * 
     * @param h
     *            The HitboxCircle of this enemy. Movable entities have only
     *            HitboxCircle.
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of this enemy.
     * @param collisionDamage
     *            The damage made by this enemy on collision with other
     *            entities.
     * @param life
     *            The health of this enemy.
     * @param ai
     *            The AI chosen for this enemy.
     * @param knockBackDelay
     *            This delay makes the enemy invulnerable, for the amount of time
     *            provided, after that it has been damaged.
     * @param fireRate
     *            The fire rate of this enemy
     * @param bulletDamage
     *            The damage made by bullets shot by this enemy.
     * @param bulletRange
     *            The range made by bullets shot by this enemy.
     * @param bulletSteps
     *            The speed of the bullets shot by this enemy.
     */
    public Enemy(final HitboxCircle h, final AI ai, final double steps, final double collisionDamage, final double life,
            final double knockBackDelay, final double fireRate, final double bulletDamage, final double bulletRange,
            final double bulletSteps) {
        super(h, ai, steps, collisionDamage, life, knockBackDelay, fireRate, bulletDamage, bulletRange, bulletSteps);
    }

    @Override
    public void pickUpItem(final Item item) {

    }

}
