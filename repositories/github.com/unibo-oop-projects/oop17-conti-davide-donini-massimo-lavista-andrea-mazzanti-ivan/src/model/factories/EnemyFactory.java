package model.factories;

import javafx.scene.shape.Shape;
import model.decorator.ComeBackEnemy;
import model.decorator.KamikazeEnemy;
import model.decorator.StarShootEnemy;
import model.decorator.StopEnemy;
import model.decorator.X3ShootEnemy;
import model.entities.ActiveEnemy;
import model.entities.AimEnemy;
import model.entities.Enemy;
import model.entities.PassiveEnemy;
import model.entities.Spaceship;
import model.entities.boss.EnemyBoss;
import model.entities.properties.Velocity;

/**
 * 
 * Interface that represents an enemy factory.
 *
 */
public interface EnemyFactory {

    /**
     * @param velocity
     *            of the enemy.
     * @param shape
     *            of the enemy.
     * @param life
     *            of the enemy.
     * @return a passive enemy.
     */
    PassiveEnemy createBasicPassive(Velocity velocity, Shape shape, int life);

    /**
     * @param velocity
     *            of the enemy.
     * @param shape
     *            of the enemy.
     * @param life
     *            of the enemy.
     * @param fireRate
     *            of the enemy.
     * @return an active enemy.
     */
    ActiveEnemy createBasicActive(Velocity velocity, Shape shape, int life, int fireRate);

    /**
     * @param velocity
     *            of the enemy.
     * @param shape
     *            of the enemy.
     * @param life
     *            of the enemy.
     * @param fireRate
     *            of the enemy.
     * @param spaceship
     *            that the enemy will aim.
     * @return an active enemy that aims at the spaceship.
     */
    AimEnemy createAimEnemy(Velocity velocity, Shape shape, int life, int fireRate, Spaceship spaceship);

    /**
     * @param enemy
     *            decorated.
     * @param spaceship
     *            that the kamikaze will aim.
     * @return a kamikaze enemy.
     */
    KamikazeEnemy createKamikaze(Enemy enemy, Spaceship spaceship);

    /**
     * @param enemy
     *            decorated.
     * @param time
     *            before stop.
     * @return an enemy that stops after a while.
     */
    StopEnemy createStopEnemy(Enemy enemy, int time);

    /**
     * 
     * @param enemy
     *            decorated.
     * @return an active enemy that shoots 3 bullets at time.
     */
    X3ShootEnemy createX3ShootEnemy(Enemy enemy);

    /**
     * 
     * @param enemy
     *            decorated.
     * @return an active enemy that shoot in all directions.
     */
    StarShootEnemy createStarShootEnemy(Enemy enemy);

    /**
     * 
     * @param enemy
     *            decorated.
     * @param bulletNumber
     *            .
     * @return an active enemy that shoot in all directions.
     */
    StarShootEnemy createStarShootEnemy(Enemy enemy, int bulletNumber);

    /**
     * 
     * @param enemy
     *            decorated.
     * @param timeBeforeStop
     *            .
     * @param timeStopped
     *            .
     * @return an enemy comes back after a while.
     */
    ComeBackEnemy createComeBackEnemy(Enemy enemy, int timeBeforeStop, int timeStopped);

    /**
     * @param spaceship
     *            of the player.
     * 
     * @return the enemy boss.
     */
    EnemyBoss createEnemyBoss(Spaceship spaceship);

    /**
     * 
     * @param positionX
     *            of the enemy.
     * @param positionY
     *            of the enemy.
     * @param spaceship
     *            that the enemy will aim.
     * @return Aim enemy's prototype.
     */
    AimEnemy createAimEnemyPrototype(int positionX, int positionY, Spaceship spaceship);

    /**
     * 
     * @param positionX
     *            of the enemy.
     * @param positionY
     *            of the enemy.
     * @param velocity
     *            of the enemy's bullet.
     * @return active enemy's prototype.
     */
    ActiveEnemy createActivePrototype(int positionX, int positionY, Velocity velocity);

}
