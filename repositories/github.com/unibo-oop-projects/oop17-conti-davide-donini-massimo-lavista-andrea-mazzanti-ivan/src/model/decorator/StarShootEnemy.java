package model.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javafx.scene.shape.Circle;
import model.entities.ActiveEnemyImpl;
import model.entities.ActiveEnemy;
import model.entities.Bullet;
import model.entities.BulletImpl;
import model.entities.BulletType;
import model.entities.Enemy;
import model.utilities.StaticVelocity;

/**
 * 
 * Decorator for enemies that shoot a certain number of bullets at time
 * equidistant from each other.
 *
 */
public final class StarShootEnemy extends EnemyDecorator {

    private static final double SCORE_MULTIPLICATOR = 3.0;

    private static final int SHOOTS_NUMBER = 16;
    private final ActiveEnemy enemy;
    private final int shootNumber;

    /**
     * 
     * @param enemy
     *            decorated.
     */
    public StarShootEnemy(final Enemy enemy) {
        this(enemy, SHOOTS_NUMBER);
    }

    /**
     * 
     * @param enemy
     *            decorated.
     * @param shootNumber
     *            .
     */
    public StarShootEnemy(final Enemy enemy, final int shootNumber) {
        super(enemy);

        if (enemy instanceof ActiveEnemy && !(enemy instanceof X3ShootEnemy)) {
            this.enemy = (ActiveEnemy) enemy;
            this.shootNumber = shootNumber;
        } else {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public List<Bullet> shoot() {
        super.setCounterElapsed(0);

        final List<Bullet> bullets = new ArrayList<>();

        IntStream.iterate(1, i -> ++i).limit(this.shootNumber).forEach(i -> {
            bullets.add(new BulletImpl(
                    new Circle(super.getPosition().getX(), super.getPosition().getY(), ActiveEnemyImpl.RADIUS),
                    StaticVelocity.velocityByDegree(this.enemy.getBulletVelocity(), i * 360 / this.shootNumber),
                    ActiveEnemyImpl.DAMAGE, this.getBulletType()));
        });

        return bullets;
    }

    @Override
    public double getScoreMultiplicator() {
        return SCORE_MULTIPLICATOR;
    }

    @Override
    public BulletType getBulletType() {
        return BulletType.STAR_ENEMY;
    }
}
