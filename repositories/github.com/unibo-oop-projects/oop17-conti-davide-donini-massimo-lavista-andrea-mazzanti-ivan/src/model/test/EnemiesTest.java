package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.scene.shape.Circle;
import model.entities.ActiveEnemy;
import model.entities.ActiveEnemyImpl;
import model.entities.BulletType;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;

/**
 * 
 * Enemies' test.
 *
 */
public class EnemiesTest {

    private static final double INITIAL_POSITION_AXIS_X = 10.0;
    private static final double INITIAL_POSITION_AXIS_Y = 10.0;
    private static final double VELOCITY_AXIS_X = 500.0;
    private static final double VELOCITY_AXIS_Y = 500.0;
    private static final Velocity INITIAL_VELOCITY = new VelocityImpl(VELOCITY_AXIS_X, VELOCITY_AXIS_Y);
    private static final double MARGIN_0F_ERROR = 0.0;
    private static final int SECONDS_ELAPSED = 1;
    private static final double TRANSFORMATION_FROM_MILLISECOND_TO_SECOND = 0.001;
    private static final double TRAVELLED_X = INITIAL_VELOCITY
            .mul(SECONDS_ELAPSED * TRANSFORMATION_FROM_MILLISECOND_TO_SECOND).getX();
    private static final double TRAVELLED_Y = INITIAL_VELOCITY
            .mul(SECONDS_ELAPSED * TRANSFORMATION_FROM_MILLISECOND_TO_SECOND).getY();
    private static final int FIRE_RATE = 50;
    private static final int INITIAL_LIFE = 4;

    private final ActiveEnemy enemy = new ActiveEnemyImpl(INITIAL_VELOCITY,
            new Circle(INITIAL_POSITION_AXIS_X, INITIAL_POSITION_AXIS_Y, 1), INITIAL_LIFE, FIRE_RATE);

    /**
     * Enemy position test.
     */
    @Test
    public void testPosition() {
        assertEquals(this.enemy.getPosition().getX(), INITIAL_POSITION_AXIS_X, MARGIN_0F_ERROR);
        assertEquals(this.enemy.getPosition().getY(), INITIAL_POSITION_AXIS_Y, MARGIN_0F_ERROR);
        this.enemy.update(SECONDS_ELAPSED);
        assertEquals(this.enemy.getPosition().getX(), INITIAL_POSITION_AXIS_X + TRAVELLED_X, MARGIN_0F_ERROR);
        assertEquals(this.enemy.getPosition().getY(), INITIAL_POSITION_AXIS_Y + TRAVELLED_Y, MARGIN_0F_ERROR);
        this.enemy.update(SECONDS_ELAPSED);
        assertEquals(this.enemy.getPosition().getX(), INITIAL_POSITION_AXIS_X + (TRAVELLED_X * 2), MARGIN_0F_ERROR);
        assertEquals(this.enemy.getPosition().getY(), INITIAL_POSITION_AXIS_Y + (TRAVELLED_Y * 2), MARGIN_0F_ERROR);
        this.enemy.update(SECONDS_ELAPSED);
        assertEquals(this.enemy.getPosition().getX(), INITIAL_POSITION_AXIS_X + (TRAVELLED_X * 3), MARGIN_0F_ERROR);
        assertEquals(this.enemy.getPosition().getY(), INITIAL_POSITION_AXIS_Y + (TRAVELLED_Y * 3), MARGIN_0F_ERROR);
        this.enemy.update(SECONDS_ELAPSED);
        assertEquals(this.enemy.getPosition().getX(), INITIAL_POSITION_AXIS_X + (TRAVELLED_X * 4), MARGIN_0F_ERROR);
        assertEquals(this.enemy.getPosition().getY(), INITIAL_POSITION_AXIS_Y + (TRAVELLED_Y * 4), MARGIN_0F_ERROR);
    }

    /**
     * Shoot test.
     */
    @Test
    public void testShoot() {
        this.enemy.shoot();
        assertFalse(this.enemy.canShoot());
        this.enemy.setCounterElapsed(FIRE_RATE);
        assertTrue(this.enemy.canShoot());
        assertEquals(this.enemy.shoot().get(0).getDamage(), 1.0, MARGIN_0F_ERROR);
        assertEquals(this.enemy.getCollisionDamage(), 1.0, MARGIN_0F_ERROR);
        assertSame(this.enemy.shoot().get(0).getBulletType(), BulletType.ACTIVE_ENEMY);
        assertFalse(this.enemy.canShoot());
    }

    /**
     * Life test.
     */
    @Test
    public void testLife() {
        assertEquals(this.enemy.getLife(), INITIAL_LIFE, MARGIN_0F_ERROR);
        this.enemy.decreaseLife(1.0);
        assertEquals(this.enemy.getLife(), 3.0, MARGIN_0F_ERROR);
        this.enemy.decreaseLife(2.0);
        assertEquals(this.enemy.getLife(), 1.0, MARGIN_0F_ERROR);
        this.enemy.decreaseLife(2.0);
        assertEquals(this.enemy.getLife(), 0.0, MARGIN_0F_ERROR);
    }
}
