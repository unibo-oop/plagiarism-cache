package model.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import model.entities.BulletType;
import model.entities.Spaceship;
import model.entities.SpaceshipImpl;
import model.entities.powerup.PowerUpType;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import model.entities.properties.Position;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * Test for spaceship.
 */
public class SpaceshipTest {

    private static final int GAME_HEIGHT = 600;
    private static final int GAME_WIDTH = 600 * 6 / 7;
    private static final double INITIAL_POSITION_AXIS_X = 2.0;
    private static final double INITIAL_POSITION_AXIS_Y = 2.0;
    private static final double INITIAL_VELOCITY_AXIS_X = 0.0;
    private static final double INITIAL_VELOCITY_AXIS_Y = 0.0;
    private static final double VELOCITY_AXIS_X = 500.0;
    private static final double VELOCITY_AXIS_Y = 500.0;
    private static final double POSITION_AXIS_X = 2.1;
    private static final double POSITION_AXIS_Y = 2.1;
    private static final double RADIUS = 2.0;
    private static final int INITIAL_FIRE_RATE = 400;
    private static final int FINAL_FIRE_RATE = 100;
    private static final int MAX_NUM_FIRE_RATE_COLLECTED = 8;
    private static final int NUMBER_DAMAGE = 5;
    private static final int BULLET_DAMAGE_COLLECTED1 = 2;
    private static final int BULLET_DAMAGE_COLLECTED2 = 4;
    private static final int BULLET_DAMAGE_COLLECTED3 = 5;
    private static final int NUMBER_RATE_OF_FIRE = 8;
    private static final double INITIAL_BULLET_DAMAGE = 25.0;
    private static final double FINAL_BULLET_DAMAGE = 100.0;
    private static final double AVERAGE_DAMAGE_BULLET = FINAL_BULLET_DAMAGE / 2;
    private static final double BULLET_DAMAGE = 43.75;
    private static final int ELAPSED1 = 100;
    private static final int ELAPSED2 = 450;
    private static final int ELAPSED3 = 6000;
    private static final int ELAPSED4 = 10000;
    private static final double MARGIN_0F_ERROR1 = 0.0;
    private static final double MARGIN_0F_ERROR2 = 0.1;

    /**
     * Example velocity.
     */
    private static final Velocity INITIAL_VELOCITY = new VelocityImpl(INITIAL_VELOCITY_AXIS_X, 
            INITIAL_VELOCITY_AXIS_Y);
    /**
     * Example shape.
     */
    private static final Shape SHAPE = new Circle(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y, RADIUS);
    /**
     * Example life.
     */
    private static final double INITIAL_LIFE = 3.0;
    /**
     * Example max life.
     */
    private static final double MAX_LIFE = 3.0;
    /**
     * Example collision damage.
     */
    private static final double COLLISION_DAMAGE = 100.0;

    private Spaceship spacehip;

    /**
     * Used for to initialize the spaceship.
     */
    @Before
    public void initialise() {
        this.spacehip = new SpaceshipImpl(SHAPE, INITIAL_VELOCITY, INITIAL_LIFE, MAX_LIFE, 
                COLLISION_DAMAGE);
        final Circle spaceshipShape = (Circle) this.spacehip.getShape();
        spaceshipShape.setCenterX(INITIAL_POSITION_AXIS_X);
        spaceshipShape.setCenterY(INITIAL_POSITION_AXIS_Y);
    }

    /**
     * Test velocity.
     */
    @Test
    public void testVelocity() {
        this.checkVelocity(0.0, 0.0);
        this.spacehip.setVelocity(new VelocityImpl(1.0, 0.0));
        this.checkVelocity(1.0, 0.0);
        this.spacehip.setVelocity(new VelocityImpl(-1.0, 0.0));
        this.checkVelocity(-1.0, 0.0);
        this.spacehip.setVelocity(new VelocityImpl(0.0, 1.0));
        this.checkVelocity(0.0, 1.0);
        this.spacehip.setVelocity(new VelocityImpl(0.0, -1.0));
        this.checkVelocity(0.0, -1.0);
    }

    private void checkVelocity(final double x, final double y) {
        assertEquals(this.spacehip.getVelocity().getX(), x, MARGIN_0F_ERROR1);
        assertEquals(this.spacehip.getVelocity().getY(), y, MARGIN_0F_ERROR1);
    }

    /**
     * Test position.
     */
    @Test
    public void testPosition() {
        this.checkPosition(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
        this.spacehip.update(ELAPSED1);
        this.checkPosition(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
        this.modifyPosition(new VelocityImpl(1.0, 0.0));
        this.checkPosition(POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
        this.modifyPosition(new VelocityImpl(-1.0, 0.0));
        this.checkPosition(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
        this.modifyPosition(new VelocityImpl(0.0, 1.0));
        this.checkPosition(INITIAL_POSITION_AXIS_X, POSITION_AXIS_Y);
        this.modifyPosition(new VelocityImpl(0.0, -1.0));
        this.checkPosition(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
    }

    /**
     * Test down boundary.
     */
    @Test
    public void testDownBoundary() {
        this.spacehip.setVelocity(new VelocityImpl(INITIAL_VELOCITY_AXIS_X, VELOCITY_AXIS_Y));
        this.spacehip.update(ELAPSED4);
        this.checkPosition(INITIAL_POSITION_AXIS_X, GAME_HEIGHT - RADIUS);
    }

    /**
     * Test up boundary.
     */
    @Test
    public void testUpBoundary() {
        this.spacehip.setVelocity(new VelocityImpl(INITIAL_VELOCITY_AXIS_X, -VELOCITY_AXIS_Y));
        this.spacehip.update(ELAPSED4);
        this.checkPosition(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
    }

    /**
     * Test left boundary.
     */
    @Test
    public void testLeftBoundary() {
        this.spacehip.setVelocity(new VelocityImpl(-VELOCITY_AXIS_X, INITIAL_VELOCITY_AXIS_Y));
        this.spacehip.update(ELAPSED4);
        this.checkPosition(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y);
    }

    /**
     * Test right boundary.
     */
    @Test
    public void testRightBoundary() {
        this.spacehip.setVelocity(new VelocityImpl(VELOCITY_AXIS_X, INITIAL_VELOCITY_AXIS_Y));
        this.spacehip.update(ELAPSED4);
        this.checkPosition(GAME_WIDTH - RADIUS, INITIAL_POSITION_AXIS_Y);
    }

    private void checkPosition(final double x, final double y) {
        final Position spaceshipPosition = this.spacehip.getPosition();
        assertEquals(spaceshipPosition.getX(), x, MARGIN_0F_ERROR1);
        assertEquals(spaceshipPosition.getY(), y, MARGIN_0F_ERROR1);
    }

    private void modifyPosition(final Velocity velocity) {
        this.spacehip.setVelocity(velocity);
        this.spacehip.update(ELAPSED1);
    }

    /**
     * Test shape.
     */
    @Test
    public void testShape() {
        if (this.spacehip.getShape() instanceof Circle) {
            final Circle circle = (Circle) this.spacehip.getShape();
            assertEquals(circle.getRadius(), RADIUS, MARGIN_0F_ERROR1);
        } else {
            fail();
        }
    }

    /**
     * Test life.
     */
    @Test
    public void testLife() {
        assertEquals(this.spacehip.getLife(), INITIAL_LIFE, MARGIN_0F_ERROR1);
        this.spacehip.decreaseLife(1.0);
        assertEquals(this.spacehip.getLife(), 2.0, MARGIN_0F_ERROR2);
        this.spacehip.decreaseLife(1.0);
        assertEquals(this.spacehip.getLife(), 1.0, MARGIN_0F_ERROR2);
        this.spacehip.decreaseLife(2.0);
        assertEquals(this.spacehip.getLife(), 0.0, MARGIN_0F_ERROR2);
        this.spacehip.incrementLife(2.0);
        assertEquals(this.spacehip.getLife(), 2.0, MARGIN_0F_ERROR2);
        this.spacehip.incrementLife(4.0);
        assertEquals(this.spacehip.getLife(), INITIAL_LIFE, MARGIN_0F_ERROR2);
    }

    /**
     * Test shoot.
     */
    @Test
    public void testShoot() {
        assertEquals(this.spacehip.getFireRate(), INITIAL_FIRE_RATE);
        assertFalse(this.spacehip.canShoot());
        this.spacehip.setCounterElapsed(ELAPSED2);
        this.spacehip.setFire(true);
        assertTrue(this.spacehip.canShoot());
        assertEquals(this.spacehip.shoot().size(), 1);
        this.checkUpgradeShoot(2, BULLET_DAMAGE_COLLECTED3, AVERAGE_DAMAGE_BULLET);
        this.checkUpgradeShoot(3, BULLET_DAMAGE_COLLECTED2, AVERAGE_DAMAGE_BULLET);
        this.checkUpgradeShoot(4, BULLET_DAMAGE_COLLECTED2, AVERAGE_DAMAGE_BULLET);
        this.checkUpgradeShoot(4, BULLET_DAMAGE_COLLECTED2, FINAL_BULLET_DAMAGE);
        this.checkDowngradeShoot(3);
        this.checkDowngradeShoot(2);
        this.checkDowngradeShoot(1);
        this.checkDowngradeShoot(1);
        assertSame(this.spacehip.shoot().get(0).getBulletType(), BulletType.FRIENDLY);
        this.spacehip.setFire(false);
        assertFalse(this.spacehip.canShoot());
    }

    private void checkUpgradeShoot(final int numBulletsShoot, final int numBulletsCollected, final double bulletDamage) {
        IntStream.iterate(0, i -> ++i).limit(numBulletsCollected)
                                      .forEach(i -> this.spacehip.pickUpPowerUp(PowerUpType.DAMAGE));
        assertEquals(this.spacehip.shoot().size(), numBulletsShoot);
        assertEquals(this.spacehip.getSpaceshipDamage(), bulletDamage, MARGIN_0F_ERROR2);
    }

    private void checkDowngradeShoot(final int numBulletsShoot) {
        this.spacehip.decreaseLife(1);
        assertEquals(this.spacehip.shoot().size(), numBulletsShoot);
        assertEquals(this.spacehip.getSpaceshipDamage(), FINAL_BULLET_DAMAGE, MARGIN_0F_ERROR1);
    }

    /**
     * Test pick up powerUpLife.
     */
    @Test
    public void testPickUpPowerUpLife() {
        this.spacehip.pickUpPowerUp(PowerUpType.LIFE);
        assertEquals(this.spacehip.getLife(), INITIAL_LIFE, MARGIN_0F_ERROR1);
        this.spacehip.decreaseLife(2.0);
        this.spacehip.pickUpPowerUp(PowerUpType.LIFE);
        assertEquals(this.spacehip.getLife(), 2.0, MARGIN_0F_ERROR2);
    }

    /**
     * Test pick up powerUpDefence.
     */
    @Test
    public void testPickUpPowerUpDefence() {
        IntStream.iterate(0, i -> ++i).limit(NUMBER_DAMAGE)
                                      .forEach(i -> this.spacehip.pickUpPowerUp(PowerUpType.DAMAGE));
        this.spacehip.pickUpPowerUp(PowerUpType.DEFENCE);
        this.spacehip.decreaseLife(1.0);
        assertEquals(this.spacehip.getLife(), INITIAL_LIFE, MARGIN_0F_ERROR2);
        assertEquals(this.spacehip.shoot().size(), 2);
        this.spacehip.update(ELAPSED3);
        this.spacehip.decreaseLife(1.0);
        assertEquals(this.spacehip.getLife(), 2.0, MARGIN_0F_ERROR2);
        assertEquals(this.spacehip.shoot().size(), 1);
    }

    /**
     * Test pick up powerUpAttack.
     */
    @Test
    public void testPickUpPowerUpAttack() {
        assertEquals(this.spacehip.getSpaceshipDamage(), INITIAL_BULLET_DAMAGE, MARGIN_0F_ERROR2);
        this.spacehip.pickUpPowerUp(PowerUpType.DAMAGE);
        assertEquals(this.spacehip.getSpaceshipDamage(), BULLET_DAMAGE, MARGIN_0F_ERROR2);
        this.spacehip.pickUpPowerUp(PowerUpType.DAMAGE);
        IntStream.iterate(0, i -> ++i).limit(BULLET_DAMAGE_COLLECTED1)
                                      .forEach(i -> this.spacehip.pickUpPowerUp(PowerUpType.DAMAGE));
        assertEquals(this.spacehip.getSpaceshipDamage(), FINAL_BULLET_DAMAGE, MARGIN_0F_ERROR2);
    }

    /**
     * Test pick up powerUpRateOfFire.
     */
    @Test
    public void testPickUpPowerUpRateOfFire() {
        assertEquals(this.spacehip.getFireRate(), INITIAL_FIRE_RATE, MARGIN_0F_ERROR1);
        this.spacehip.pickUpPowerUp(PowerUpType.RATE_OF_FIRE);
        assertEquals(this.spacehip.getFireRate(), INITIAL_FIRE_RATE
                - (INITIAL_FIRE_RATE - FINAL_FIRE_RATE) / MAX_NUM_FIRE_RATE_COLLECTED, MARGIN_0F_ERROR2);
        IntStream.iterate(0, i -> ++i).limit(NUMBER_RATE_OF_FIRE)
                                      .forEach(i -> this.spacehip.pickUpPowerUp(PowerUpType.RATE_OF_FIRE));
        assertEquals(this.spacehip.getFireRate(), FINAL_FIRE_RATE, MARGIN_0F_ERROR2);
        this.spacehip.pickUpPowerUp(PowerUpType.RATE_OF_FIRE);
        assertEquals(this.spacehip.getFireRate(), FINAL_FIRE_RATE, MARGIN_0F_ERROR2);
    }
}
