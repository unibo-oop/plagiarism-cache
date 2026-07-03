package model.decorator;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Circle;
import model.entities.ActiveEnemy;
import model.entities.ActiveEnemyImpl;
import model.entities.Bullet;
import model.entities.BulletImpl;
import model.entities.BulletType;
import model.entities.Enemy;
import model.entities.properties.Position;
import model.entities.properties.Velocity;
import model.utilities.Shapes;
import model.utilities.StaticVelocity;

/**
 * 
 * Decorator for enemies that shoot 3 bullets at time.
 *
 */
public final class X3ShootEnemy extends EnemyDecorator {

    private static final double SCORE_MULTIPLICATOR = 3.0;

    private static final int DEGREE_DISTANCE = 15;
    private final ActiveEnemy enemy;

    /**
     * 
     * @param enemy
     *            decorated.
     */
    public X3ShootEnemy(final Enemy enemy) {
        super(enemy);

        if (!(enemy instanceof ActiveEnemy) || enemy instanceof StarShootEnemy) {
            throw new UnsupportedOperationException();
        }
            this.enemy = ((ActiveEnemy) enemy);
    }

    @Override
    public List<Bullet> shoot() {
        super.setCounterElapsed(0);
        final Position p = Shapes.getEntityCenter(this);

        final List<Bullet> bullets = new ArrayList<>();
        bullets.add(new BulletImpl(new Circle(p.getX(), p.getY(), ActiveEnemyImpl.RADIUS),
                this.enemy.getBulletVelocity(), ActiveEnemyImpl.DAMAGE, this.getBulletType()));

        final Velocity lower = StaticVelocity.velocityByDegree(this.enemy.getBulletVelocity(), -DEGREE_DISTANCE);
        final Velocity upper = StaticVelocity.velocityByDegree(this.enemy.getBulletVelocity(), DEGREE_DISTANCE);

        bullets.add(new BulletImpl(new Circle(p.getX(), p.getY(), ActiveEnemyImpl.RADIUS), lower,
                ActiveEnemyImpl.DAMAGE, this.getBulletType()));

        bullets.add(new BulletImpl(new Circle(p.getX(), p.getY(), ActiveEnemyImpl.RADIUS), upper,
                ActiveEnemyImpl.DAMAGE, this.getBulletType()));

        return bullets;
    }

    @Override
    public double getScoreMultiplicator() {
        return SCORE_MULTIPLICATOR;
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.X3_ENEMY;
    }
}
