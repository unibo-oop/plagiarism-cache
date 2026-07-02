import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.ball.Ball;
import model.character.BigBall;
import model.obstacle.CircularObstacle;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;
import model.world.GameWorld;
import model.world.GameWorldImpl;

class BigBallTest {
    private static final double POSX = 0;
    private static final double POSY = 4;

    @Test
    final void testUsePower() {
        // Create a world, a green obstacle and adds this to the world
        final GameWorld world = new GameWorldImpl();
        final Obstacle greenObstacle = new CircularObstacle(Pair.of(POSX, POSY));
        greenObstacle.setObstacleBehavior(ObstacleBehavior.GREEN);
        world.addObstacles(List.of(greenObstacle));

        // Starts a new turn, launches the ball and saves
        world.setNewTurn();
        world.getBallLauncher().launch();

        // Check if the ball hits the obstacle
        while (!greenObstacle.hit()) {
            world.update(1, Pair.of(0., 1.));

        }
        assertTrue(greenObstacle.hit());

        // Now that the world has a green obstacle in its list of hit obstacles saves
        // the
        // old ball reference and creates
        // an instance of BigBall
        final Ball oldball = world.getBall();
        final BigBall bigball = new BigBall();
        bigball.usePower(world);

        // Asserts that the new ball is actually double the size of the old one, that
        // they are different objects and the position differs as well
        assertEquals(oldball.getRadius() * 2, world.getBall().getRadius());
        assertNotSame(oldball, world.getBall());
        assertNotEquals(oldball.getPosition(), world.getBall().getPosition());

    }

}
