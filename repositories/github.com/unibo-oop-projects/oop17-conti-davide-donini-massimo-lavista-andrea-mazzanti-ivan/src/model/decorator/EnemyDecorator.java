package model.decorator;

import java.util.List;
import java.util.Optional;

import javafx.scene.shape.Shape;
import model.entities.ActiveEnemy;
import model.entities.Bullet;
import model.entities.BulletType;
import model.entities.Enemy;
import model.entities.powerup.PowerUpType;
import model.entities.properties.Position;
import model.entities.properties.Velocity;

/**
 * 
 * Abstract class for enemy's decorations.
 *
 */

public abstract class EnemyDecorator implements ActiveEnemy {

    /**
     * Zero approximated.
     */
    public static final double ZERO = 0.00000001;
    private final Enemy enemy;

    /**
     * 
     * @param enemy
     *            decorated.
     */
    public EnemyDecorator(final Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * 
     * @return the enemy decorated.
     */
    public final Enemy getDecoratedEnemy() {
        return this.enemy;
    }

    /**
     * 
     * @return the damage issued from the collision with other entity.
     */
    @Override
    public double getCollisionDamage() {
        return this.enemy.getCollisionDamage();
    }

    /**
     * 
     * @return the sum of the elapsed.
     */
    @Override
    public int getCounterElapsed() {
        return this.enemy.getCounterElapsed();
    }

    /**
     * Set the variable 'counterElapsed' to a certain value.
     * 
     * @param counterElapsed
     *            to set
     */
    @Override
    public void setCounterElapsed(final int counterElapsed) {
        this.enemy.setCounterElapsed(counterElapsed);
    }

    /**
     * Get the health of this enemy.
     * 
     * @return the health
     */
    @Override
    public double getLife() {
        return this.enemy.getLife();
    }

    /**
     * Get the max health of this enemy.
     * 
     * @return the max health
     */
    @Override
    public double getMaxLife() {
        return this.enemy.getMaxLife();
    }

    /**
     * The method called when this enemy has been damaged.
     * 
     * @param decrease
     *            The quantity of life to decrease
     */
    @Override
    public void decreaseLife(final double decrease) {
        this.enemy.decreaseLife(decrease);
    }

    /**
     * The method called when this entity regenerate the health.
     * 
     * @param increment
     *            The quantity of life to increment
     */
    @Override
    public void incrementLife(final double increment) {
        this.enemy.incrementLife(increment);
    }

    /**
     * 
     * @return enemy's position
     */
    @Override
    public Position getPosition() {
        return this.enemy.getPosition();
    }

    /**
     * 
     * @return enemy's velocity
     */
    @Override
    public Velocity getVelocity() {
        return this.enemy.getVelocity();
    }

    /**
     * 
     * @return enemy's shape
     */
    @Override
    public Shape getShape() {
        return this.enemy.getShape();
    }

    /**
     * 
     * @param velocity
     *            to set
     */
    @Override
    public void setVelocity(final Velocity velocity) {
        this.enemy.setVelocity(velocity);
    }

    /**
     * 
     * @param shape
     *            to set
     */
    @Override
    public void setShape(final Shape shape) {
        this.enemy.setShape(shape);
    }

    /**
     * Update enemy's status.
     * 
     * @param time
     *            time elapsed from last update
     */
    @Override
    public void update(final int time) {
        this.enemy.update(time);
    }

    /**
     * @return list of the new bullets.
     */
    @Override
    public List<Bullet> shoot() {
        if (this.enemy instanceof ActiveEnemy) {
            return ((ActiveEnemy) this.enemy).shoot();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Getter for the rate of fire.
     * 
     * @return fire rate
     * 
     */
    @Override
    public int getFireRate() {
        if (this.enemy instanceof ActiveEnemy) {
            return ((ActiveEnemy) this.enemy).getFireRate();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Setter for the rate of fire.
     * 
     * @param fireRate
     *            to set.
     */
    @Override
    public void setFireRate(final int fireRate) {
        if (this.enemy instanceof ActiveEnemy) {
            ((ActiveEnemy) this.enemy).setFireRate(fireRate);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param position
     *            the new enemy's position.
     */
    @Override
    public void setPosition(final Position position) {
        this.enemy.setPosition(position);
    }

    /**
     * 
     * @return true if the entity can shoot, otherwise false.
     */
    @Override
    public boolean canShoot() {
        if (this.enemy instanceof ActiveEnemy) {
            return ((ActiveEnemy) this.enemy).canShoot();
        }
        return false;
    }

    /**
     * 
     * @return the bullet's velocity.
     */
    @Override
    public Velocity getBulletVelocity() {
        if (this.enemy instanceof ActiveEnemy) {
            return ((ActiveEnemy) this.enemy).getBulletVelocity();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * @return the power up that may spawn after the enemy death.
     */
    @Override
    public Optional<PowerUpType> getPowerUp() {
        return this.enemy.getPowerUp();
    }

    /**
     * 
     * @return the bullet's type.
     */
    @Override
    public BulletType getBulletType() {
        if (this.enemy instanceof ActiveEnemy) {
            return ((ActiveEnemy) this.enemy).getBulletType();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Template method used to get the score of the decoration.
     * 
     */
    @Override
    public final int getScore() {
        return (int) (this.enemy.getScore() * this.getScoreMultiplicator());
    }

    /**
     * 
     * @return the score multiplicator
     */
    public abstract double getScoreMultiplicator();
}
