package model.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.entities.Spaceship;
import model.entities.SpaceshipImpl;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;
import model.factories.EnemyFactory;
import model.factories.EnemyFactoryImpl;

/**
 * 
 * Test for the enemies' decorations.
 *
 */
public class EnemyDecorationsTest {

    private static final String EXPECTED = "Should raise a UnsupportedOperationException";
    private static final String NOT_EXPECTED = "Shouldn't raise any Exception";

    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final Velocity velocity = new VelocityImpl(0, 0);
    private final Shape shape = new Circle(0, 0, 0);
    private final Spaceship spaceship = new SpaceshipImpl();

    /**
     * 
     * Test for the unsupported mix of decoration.
     */
    @Test
    public void notAllowedDecorationsTest() {
        try {
            this.enemyFactory.createStarShootEnemy(this.enemyFactory.createBasicPassive(this.velocity, this.shape, 0));
            fail(EXPECTED);
        } catch (UnsupportedOperationException e) {
            System.out.println("STAR(PASSIVE): X");
        } catch (Exception e) {
            fail(EXPECTED);
        }

        try {
            this.enemyFactory.createStarShootEnemy(
                    this.enemyFactory.createX3ShootEnemy(this.enemyFactory.createActivePrototype(0, 0, this.velocity)));
            fail(EXPECTED);
        } catch (UnsupportedOperationException e) {
            System.out.println("STAR(X3(ACTIVE)): X");
        } catch (Exception e) {
            fail(EXPECTED);
        }

        try {
            this.enemyFactory.createX3ShootEnemy(this.enemyFactory.createBasicPassive(this.velocity, this.shape, 0));
            fail(EXPECTED);
        } catch (UnsupportedOperationException e) {
            System.out.println("X3(PASSIVE): X");
        } catch (Exception e) {
            fail(EXPECTED);
        }

        try {
            this.enemyFactory.createX3ShootEnemy(this.enemyFactory
                    .createStarShootEnemy(this.enemyFactory.createActivePrototype(0, 0, this.velocity)));
            fail(EXPECTED);
        } catch (UnsupportedOperationException e) {
            System.out.println("X3(STAR(ACTIVE)): X");
        } catch (Exception e) {
            fail(EXPECTED);
        }

        try {
            this.enemyFactory.createKamikaze(this.enemyFactory.createComeBackEnemy(
                    this.enemyFactory.createBasicPassive(this.velocity, this.shape, 0), 0, 0), this.spaceship);
            fail(EXPECTED);
        } catch (UnsupportedOperationException e) {
            System.out.println("KAMIKAZE(COME_BACK(PASSIVE)): X");
        } catch (Exception e) {
            fail(EXPECTED);
        }

        try {
            this.enemyFactory.createKamikaze(this.enemyFactory.createStopEnemy(
                    this.enemyFactory.createBasicPassive(this.velocity, this.shape, 0), 0), this.spaceship);
            fail(EXPECTED);
        } catch (UnsupportedOperationException e) {
            System.out.println("KAMIKAZE(STOP(PASSIVE)): X");
        } catch (Exception e) {
            fail(EXPECTED);
        }
    }

    /**
     * 
     * Test for the supported mix of decoration.
     */
    @Test
    public void allowedDecorationsTest() {
        try {
            this.enemyFactory.createStarShootEnemy(this.enemyFactory.createActivePrototype(0, 0, this.velocity));
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        try {
            this.enemyFactory.createStarShootEnemy(this.enemyFactory.createAimEnemyPrototype(0, 0, this.spaceship));
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        try {
            this.enemyFactory.createX3ShootEnemy(this.enemyFactory.createActivePrototype(0, 0, this.velocity));
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        try {
            this.enemyFactory.createX3ShootEnemy(this.enemyFactory.createAimEnemyPrototype(0, 0, this.spaceship));
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        try {
            this.enemyFactory.createKamikaze(this.enemyFactory.createX3ShootEnemy(
                    this.enemyFactory.createAimEnemyPrototype(0, 0, this.spaceship)), this.spaceship);
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        try {
            this.enemyFactory.createStopEnemy(this.enemyFactory
                    .createX3ShootEnemy(this.enemyFactory.createAimEnemyPrototype(0, 0, this.spaceship)), 0);
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }

        try {
            this.enemyFactory.createComeBackEnemy(this.enemyFactory
                    .createX3ShootEnemy(this.enemyFactory.createAimEnemyPrototype(0, 0, this.spaceship)), 0, 0);
        } catch (Exception e) {
            fail(NOT_EXPECTED);
        }
    }
}
