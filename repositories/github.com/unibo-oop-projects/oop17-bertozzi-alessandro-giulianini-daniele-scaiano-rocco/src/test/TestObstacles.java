package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import game.ID;
import game.obstacles.AbstractObstacle;
import game.obstacles.ObstacleDecorator;
import game.obstacles.ObstacleFactory;
import game.obstacles.ObstacleFactoryImpl;
import utilities.Pair;

/**
 * Test class to test ObstacleFactoryImpl critic aspects.
 */
public class TestObstacles {

    private static final ObstacleFactory OF = new ObstacleFactoryImpl();

    /**
     * Test matching between name of factory methods and ID of obstacles assumed to be created.
     */
    @Test
    public void testObstacleFactoryImplIDcorrispondence() {
        assertEquals("ID and method name should match", OF.createSimpleObstacle().getID(), ID.SMP_OBSTACLE);
        assertEquals("ID and method name should match", OF.createBouncingObstacle().getID(), ID.BNC_OBSTACLE);
        assertEquals("ID and method name should match.", OF.createEnlargingObstacle().getID(), ID.ENL_OBSTACLE);
        assertEquals("ID and method name should match.", OF.createTimeLimitedObstacle().getID(), ID.TML_OBSTACLE);
        assertEquals("ID and method name should match.", OF.createEnlargingBouncingObstacle().getID(), ID.ENL_BNC_OBSTACLE);
    }

    /**
     * Test whether BouncingObstacleInstance swaps its velocity after colliding with boundaries.
     */
    @Test
    public void testBouncingObstacle() {
        final ObstacleDecorator.BouncingObstacle bo = new ObstacleDecorator.BouncingObstacle(createObstacleCrossingGameArea());
        final Pair<Integer, Integer> velocityBeforeCollision = bo.getVelocity();     //to change in Integer
        bo.update();
        final Pair<Integer, Integer> velocityAfterCollision = bo.getVelocity();
        assertEquals("velocity direction should be switched.", velocityBeforeCollision.getY().intValue(), -velocityAfterCollision.getY().intValue());     //poi da togliere il delta..
    }

    private AbstractObstacle createObstacleCrossingGameArea() {
        final int xCoordNearBoundary = 5;
        final int yCoordNearBoundary = 5;
        final int velocityX = -10;
        final int velocityY = 8;
        final Pair<Integer, Integer> position = new Pair<>(xCoordNearBoundary, yCoordNearBoundary);
        return new AbstractObstacle.SimpleObstacle(position, 0, velocityX, velocityY);
    }

    /**
     * Test whether TimeLimitedObstacle instance dies after spending its life-time.
     */
    @Test
    public void testTimeLimitedObstacle() {
        final AbstractObstacle to = OF.createTimeLimitedObstacle();
        final int timeLife = 600;
        Stream.iterate(0, i -> i + 1).limit(timeLife).forEach(x -> to.update());
        assertTrue("Obstacle should be dead.", to.isDead());
    }

    /**
     * Test whether EnlargingOstacle instance increase its size.
     */
    @Test
    public void testEnlargingObstacle() {
        final AbstractObstacle eo = OF.createEnlargingObstacle();
        final Rectangle previousHitbox = eo.getHitbox();
        final int timeRate = 200;
        Stream.iterate(0, i -> i + 1).limit(timeRate).forEach(x -> eo.update());
        final Rectangle followingHitBox = eo.getHitbox();
        assertFalse("HitBox should be changed.", previousHitbox.equals(followingHitBox));
    }

    /**
     * Test whether TimeLimitedEnlargingOstacle instance increase its size.
     */
    @Test
    public void testTimeLimitedEnlargingObstacle() {
        final AbstractObstacle eo = OF.createTimeLimitedEnlargingObstacle();
        final Rectangle previousHitbox = eo.getHitbox();
        final int time = 500;
        Stream.iterate(0, i -> i + 1).limit(time).forEach(x -> eo.update());
        final Rectangle followingHitBox = eo.getHitbox();
        assertFalse("HitBox should be changed.", previousHitbox.equals(followingHitBox));
        assertTrue("Obstacle should be dead.", eo.isDead());
    }

}
