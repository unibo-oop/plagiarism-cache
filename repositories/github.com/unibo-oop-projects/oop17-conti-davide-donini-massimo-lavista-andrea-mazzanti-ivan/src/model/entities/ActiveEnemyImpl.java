package model.entities;

import java.util.Arrays;
import java.util.List;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.entities.properties.Position;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;
import model.utilities.Shapes;

/**
 * 
 * An enemy that can shoot.
 *
 */
public class ActiveEnemyImpl extends PassiveEnemy implements ActiveEnemy {

    private static final int ENEMY_BULLET_SPEED = 400;
    /**
     * Enemy's bullet damage.
     */
    public static final int DAMAGE = 1;
    /**
     * Enemy's bullet radius.
     */
    public static final double RADIUS = 16.0;
    private static final double SCORE_MULTIPLICATOR = 1.5;

    private final Velocity bulletVelocity;

    private int fireRate;

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
     * @param bulletVelocity
     *            of the enemy.
     */
    public ActiveEnemyImpl(final Velocity velocity, final Shape shape, final double life, final double maxLife,
            final double collisionDamage, final int fireRate, final Velocity bulletVelocity) {
        super(velocity, shape, life, maxLife, collisionDamage);
        this.fireRate = fireRate;
        this.bulletVelocity = bulletVelocity;
    }

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
     */
    public ActiveEnemyImpl(final Velocity velocity, final Shape shape, final double life, final double maxLife,
            final double collisionDamage, final int fireRate) {
        this(velocity, shape, life, maxLife, collisionDamage, fireRate,
                new VelocityImpl(velocity.getX() / 2, ENEMY_BULLET_SPEED));
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
     */
    public ActiveEnemyImpl(final Velocity velocity, final Shape shape, final double life, final int fireRate) {
        this(velocity, shape, life, life, 1, fireRate);
    }

    /**
     * @return list of the new bullets.
     */
    @Override
    public List<Bullet> shoot() {
        super.setCounterElapsed(0);

        final Position p = Shapes.getEntityCenter(this);
        return Arrays.asList(new BulletImpl(new Circle(p.getX(), p.getY(), RADIUS), this.getBulletVelocity(), DAMAGE,
                this.getBulletType()));
    }

    /**
     * Getter for the rate of fire.
     * 
     * @return fire rate
     * 
     */
    @Override
    public int getFireRate() {
        return this.fireRate;
    }

    /**
     * Setter for the rate of fire.
     * 
     * @param fireRate to set.
     */
    @Override
    public void setFireRate(final int fireRate) {
        this.fireRate = fireRate;
    }

    /**
     * @return true if the entity can shoot, otherwise false.
     */
    @Override
    public boolean canShoot() {
        return super.getCounterElapsed() >= this.fireRate;
    }

    /**
     * Update the entity's position according to velocity and position.
     */
    @Override
    public void update(final int time) {
        super.update(time);
        if (super.getCounterElapsed() < this.fireRate) {
            super.setCounterElapsed(super.getCounterElapsed() + time);
        }
    }

    /**
     * 
     * @return the bullet velocity
     */
    @Override
    public Velocity getBulletVelocity() {
        return this.bulletVelocity;
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
        return BulletType.ACTIVE_ENEMY;
    }
}
