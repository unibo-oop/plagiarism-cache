package model.entities;

import javafx.scene.shape.Shape;
import model.entities.properties.Velocity;
import model.utilities.StaticVelocity;

/**
 * 
 * An enemy that shoot towards the spaceship.
 *
 */
public class AimEnemy extends ActiveEnemyImpl {

    private static final int BULLET_SPEED = 448;
    private static final double SCORE_MULTIPLICATOR = 1.5;

    private final Spaceship spaceship;

    /**
     * 
     * @param velocity
     *            initial enemy's velocity.
     * @param shape
     *            initial enemy's shape.
     * @param life
     *            initial enemy's life.
     * @param maxLife
     *            maximum life that the enemy can take on.
     * @param collisionDamage
     *            damage issued from the collision with other entity.
     * @param fireRate
     *            of the enemy.
     * @param spaceship
     *            that the enemy will try to destroy.
     */
    public AimEnemy(final Velocity velocity, final Shape shape, final double life, final double maxLife,
            final double collisionDamage, final int fireRate, final Spaceship spaceship) {
        super(velocity, shape, life, maxLife, collisionDamage, fireRate);
        this.spaceship = spaceship;
    }

    /**
     * 
     * @param velocity
     *            initial entity's velocity.
     * @param shape
     *            initial entity's shape.
     * @param life
     *            initial entity's life.
     * @param fireRate
     *            of the enemy.
     * @param spaceship
     *            that the enemy will try to destroy.
     */
    public AimEnemy(final Velocity velocity, final Shape shape, final double life, final int fireRate,
            final Spaceship spaceship) {
        this(velocity, shape, life, life, 1, fireRate, spaceship);
    }

    /**
     * 
     * @return the bullet velocity
     */
    @Override
    public Velocity getBulletVelocity() {
        return StaticVelocity.setAbsoluteSpeed(this.spaceship.getPosition().sub(super.getPosition()), BULLET_SPEED);
    }

    /**
     * 
     * @return the score got after killing the enemy.
     */
    @Override
    public int getScore() {
        return (int) (super.getScore() * SCORE_MULTIPLICATOR);
    }

    /**
     * 
     * @return the bullet type.
     */
    @Override
    public BulletType getBulletType() {
        return BulletType.AIM_ENEMY;
    }
}
