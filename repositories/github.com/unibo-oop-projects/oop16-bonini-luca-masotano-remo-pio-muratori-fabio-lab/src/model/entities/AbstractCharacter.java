package model.entities;

import java.util.Collection;

import model.ai.AI;
import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 * Base abstract class that defines all the needed methods to represent all the
 * playable and enemy characters.
 *
 */
public abstract class AbstractCharacter extends MovableEntity implements Character {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 1371676753424457720L;
    private static final double MINDAMAGE = 1;
    private double life;
    private final double knockbackDelay;
    private final AI ai;
    private double limitKnockbackDelay;
    private double fireRate;
    private double bulletDamage;
    private double bulletRange;
    private final double bulletSteps;

    /**
     * 
     * @param h
     *            The HitboxCircle of this character.
     * @param ai
     *            The AI chosen for this character.
     * @param steps
     *            The number of steps performed during the movement. Represents
     *            the speed of this character.
     * @param collisionDamage
     *            The damage made by this character on collision with other
     *            entities.
     * @param life
     *            The health of this character.
     * @param knockBackDelay
     *            This delay makes the character invulnerable, for the amount of
     *            time provided, after that it has been damaged.
     * @param fireRate
     *            The fire rate of this character
     * @param bulletDamage
     *            The damage made by bullets shot by this character.
     * @param bulletRange
     *            The range made by bullets shot by this character.
     * @param bulletSteps
     *            The speed of the bullets shot by this character.
     */
    public AbstractCharacter(final HitboxCircle h, final AI ai, final double steps, final double collisionDamage,
            final double life, final double knockBackDelay, final double fireRate, final double bulletDamage,
            final double bulletRange, final double bulletSteps) {
        super(h, steps, collisionDamage);
        this.life = life;
        this.knockbackDelay = knockBackDelay;
        this.ai = ai;
        this.fireRate = fireRate;
        this.bulletDamage = bulletDamage;
        this.bulletRange = bulletRange;
        this.bulletSteps = bulletSteps;
        this.limitKnockbackDelay = 0;
    }

    @Override
    protected HitboxImpl performMove(final double delta) {
        ai.decide((int) this.getLife());
        return ai.move(super.getHitbox(), super.getSteps(), delta);
    }

    @Override
    public Collection<Bullet> shoot(final double delta) {
        ai.decide((int) this.getLife());
        return ai.shoot(super.getHitbox(), delta, this.fireRate, this.bulletDamage, this.bulletRange, this.bulletSteps);
    }

    /**
     * Getter for the limit knockback delay.
     * 
     * @return The knockbackDelay
     */
    protected double getLimitKnockbackDelay() {
        return limitKnockbackDelay;
    }

    /**
     * Decrement the value of the knockback delay by delta.
     * 
     * @param delta
     *            The delta of time.
     */
    protected void decrementKnockbackDelay(final double delta) {
        this.limitKnockbackDelay -= delta;
    }

    /**
     * Reset the knockback delay to its original value.
     */
    protected void resetKnockbackDelay() {
        this.limitKnockbackDelay = knockbackDelay;
    }

    @Override
    public void takeDamage(final double dmg) {
        if (this.getLimitKnockbackDelay() <= 0 && this.life > 0) {
            this.life -= dmg;
            this.resetKnockbackDelay();
        }
    }

    @Override
    public void pickUpItem(final Item item) {
        switch (item.getItemType().getProperty()) {
        case DAMAGE:
            this.bulletDamage += item.getValue();
            break;
        case FIRERATE:
            this.fireRate += item.getValue();
            break;
        case LIFE:
            this.life += item.getValue();
            break;
        case RANGE:
            this.bulletRange += item.getValue();
            break;
        default:
            break;

        }

        if (this.bulletDamage < MINDAMAGE) {
            this.bulletDamage = MINDAMAGE;
        }
    }

    @Override
    public double getFireRate() {
        return fireRate;
    }

    @Override
    public double getLife() {
        return life;
    }

    @Override
    public double getKnockbackDelay() {
        return knockbackDelay;
    }
}
