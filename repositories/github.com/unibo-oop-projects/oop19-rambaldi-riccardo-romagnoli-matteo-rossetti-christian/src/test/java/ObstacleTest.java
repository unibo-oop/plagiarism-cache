import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.obstacle.CircularObstacle;
import model.obstacle.ObstacleBehavior;
import model.obstacle.RectangularObstacle;
import model.world.GameWorld;
import model.world.GameWorldImpl;

public class ObstacleTest {

    private static final double X = 0;
    private static final double Y = 4;
    private static final Double ANGLE = 0.;

    //Base test of RectangularObstacle
    @Test
    void testRectangularObstacleCreation() {

        //Create a rectangular obstacle
        final RectangularObstacle rectangle = new RectangularObstacle(Pair.of(X, Y), ANGLE);

        //Check default parameter
        assertEquals(rectangle.getBehavior(), ObstacleBehavior.BLU);
        assertTrue(rectangle.getMeasures().get(2).equals(ANGLE));
        assertFalse(rectangle.hit());

        //Set new behavior
        rectangle.setObstacleBehavior(ObstacleBehavior.PURPLE);
        assertNotNull(rectangle.getBehavior());
        assertEquals(rectangle.getBehavior(), ObstacleBehavior.PURPLE);
        rectangle.setObstacleBehavior(ObstacleBehavior.GREEN);
        assertNotNull(rectangle.getBehavior());
        assertEquals(rectangle.getBehavior(), ObstacleBehavior.GREEN);
    }

    //Base test of CircularObstacle
    @Test
    void testCircularObstacleCreation() {

        //Create a circular obstacle
        final CircularObstacle circle = new CircularObstacle(Pair.of(X, Y));

        //Test default parameter
        assertEquals(circle.getBehavior(), ObstacleBehavior.BLU);
        assertFalse(circle.hit());

        //Set new behavior
        circle.setObstacleBehavior(ObstacleBehavior.PURPLE);
        assertNotNull(circle.getBehavior());
        assertEquals(circle.getBehavior(), ObstacleBehavior.PURPLE);
        circle.setObstacleBehavior(ObstacleBehavior.GREEN);
        assertNotNull(circle.getBehavior());
        assertEquals(circle.getBehavior(), ObstacleBehavior.GREEN);
    }

    @Test
    void testCircularObstacleCollisionDetection() {

        //Create a circular obstacle and the world
        final GameWorld world = new GameWorldImpl();
        final CircularObstacle circle = new CircularObstacle(Pair.of(X, Y));

        //Initialize the world and add the ball
        world.addObstacles(List.of(circle));
        world.setNewTurn();
        world.getBallLauncher().launch();

        //Check if the ball hit the obstacle
        while (!circle.hit()) {
            world.update(1, Pair.of(0., 1.));
        }
        assertTrue(circle.hit());
    }

    @Test
    void testRectangularObstacleCollisionDetection() {

        //Create a circular obstacle and the world
        final GameWorld world = new GameWorldImpl();
        final RectangularObstacle rectangle = new RectangularObstacle(Pair.of(X, Y), ANGLE);

        //Initialize the world and add the ball
        world.addObstacles(List.of(rectangle));
        world.setNewTurn();
        world.getBallLauncher().launch();

        //Check if the ball hit the obstacle
        while (!rectangle.hit()) {
            world.update(1, Pair.of(0., 1.));
        }
        assertTrue(rectangle.hit());
    }

}
