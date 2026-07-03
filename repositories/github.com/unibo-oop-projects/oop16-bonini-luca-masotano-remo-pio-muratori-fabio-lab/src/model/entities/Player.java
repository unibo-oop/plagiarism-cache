package model.entities;

import model.ai.AI;
import model.hitbox.HitboxCircle;

/**
 * 
 * Represents the player.
 *
 */
public class Player extends AbstractCharacter {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 1228468980197930438L;

    /**
     * The constructor of the class Player.
     * 
     * @param h
     *            The HitboxCircle of the player. Movable entities have only
     *            HitboxCircle.
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of the player.
     * @param collisionDamage
     *            The damage made by the player on collision with other
     *            entities.
     * @param life
     *            The health of the player.
     * @param ai
     *            The AI chosen for the player.
     * @param knockBackDelay
     *            This delay makes the player invulnerable, for the amount of time
     *            provided, after that it has been damaged.
     * @param fireRate
     *            The fire rate of the player
     * @param bulletDamage
     *            The damage made by bullets shot by the player.
     * @param bulletRange
     *            The range made by bullets shot by the player.
     * @param bulletSteps
     *            The speed of the bullets shot by the player.
     */
    public Player(final HitboxCircle h, final AI ai, final double steps, final double collisionDamage,
            final double life, final double knockBackDelay, final double fireRate, final double bulletDamage,
            final double bulletRange, final double bulletSteps) {
        super(h, ai, steps, collisionDamage, life, knockBackDelay, fireRate, bulletDamage, bulletRange, bulletSteps);
    }

    @Override
    public void move(final double delta) {
        if (super.getKnockbackDelay() > 0) {
            super.decrementKnockbackDelay(delta);
        }
        super.move(delta);
    }
}
