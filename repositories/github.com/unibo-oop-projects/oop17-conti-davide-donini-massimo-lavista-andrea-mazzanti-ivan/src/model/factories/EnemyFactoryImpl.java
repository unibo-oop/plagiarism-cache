package model.factories;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.decorator.ComeBackEnemy;
import model.decorator.KamikazeEnemy;
import model.decorator.StarShootEnemy;
import model.decorator.StopEnemy;
import model.decorator.X3ShootEnemy;
import model.entities.ActiveEnemy;
import model.entities.ActiveEnemyImpl;
import model.entities.AimEnemy;
import model.entities.Enemy;
import model.entities.PassiveEnemy;
import model.entities.Spaceship;
import model.entities.boss.EnemyBoss;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Implementation of an enemy factory.
 *
 */
public final class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public PassiveEnemy createBasicPassive(final Velocity velocity, final Shape shape, final int life) {
        return new PassiveEnemy(velocity, shape, life);
    }

    @Override
    public ActiveEnemy createBasicActive(final Velocity velocity, final Shape shape, final int life,
            final int fireRate) {
        return new ActiveEnemyImpl(velocity, shape, life, fireRate);
    }

    @Override
    public AimEnemy createAimEnemy(final Velocity velocity, final Shape shape, final int life, final int fireRate,
            final Spaceship spaceship) {
        return new AimEnemy(velocity, shape, life, fireRate, spaceship);
    }

    @Override
    public KamikazeEnemy createKamikaze(final Enemy enemy, final Spaceship spaceship) {
        return new KamikazeEnemy(enemy, spaceship);
    }

    @Override
    public StopEnemy createStopEnemy(final Enemy enemy, final int time) {
        return new StopEnemy(enemy, time);
    }

    @Override
    public X3ShootEnemy createX3ShootEnemy(final Enemy enemy) {
        return new X3ShootEnemy(enemy);
    }

    @Override
    public StarShootEnemy createStarShootEnemy(final Enemy enemy) {
        return new StarShootEnemy(enemy);
    }

    @Override
    public StarShootEnemy createStarShootEnemy(final Enemy enemy, final int bulletNumber) {
        return new StarShootEnemy(enemy, bulletNumber);
    }

    @Override
    public ComeBackEnemy createComeBackEnemy(final Enemy enemy, final int timeBeforeStop, final int timeStopped) {
        return new ComeBackEnemy(enemy, timeBeforeStop, timeStopped);
    }

    @Override
    public EnemyBoss createEnemyBoss(final Spaceship spaceship) {
        return new EnemyBoss(spaceship);
    }

    @Override
    public AimEnemy createAimEnemyPrototype(final int positionX, final int positionY, final Spaceship spaceship) {
        return new AimEnemy(new VelocityImpl(0, 0), new Circle(positionX, positionY, 1), 1, 1, spaceship);
    }

    @Override
    public ActiveEnemy createActivePrototype(final int positionX, final int positionY, final Velocity velocity) {
        return new ActiveEnemyImpl(new VelocityImpl(0, 0), new Circle(positionX, positionY, 1), 1, 1, 1, 1, velocity);
    }
}
