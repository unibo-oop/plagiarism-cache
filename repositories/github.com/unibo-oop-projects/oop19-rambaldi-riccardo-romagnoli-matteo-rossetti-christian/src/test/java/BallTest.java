import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.ball.Ball;
import model.ball.BallImpl;
import model.balllaucher.BallLauncher;
import model.balllaucher.BallLauncherImpl;
import model.obstacle.Obstacle;
import model.obstacle.RectangularObstacle;
import model.world.GameWorld;
import model.world.GameWorldImpl;

class BallTest {

    @Test
    void moveBall() {
        final Ball ball = new BallImpl();
        final Pair<Double, Double> newPosition =  Pair.of(10., 10.);
        ball.move(newPosition);
        assertEquals(newPosition, ball.getPosition());
    }

    @Test
    void createCustomBall() {
        final Ball b = new BallImpl(100);
        assertEquals(100, b.getRadius());
    }

    @Test
    void ballOut() {
        final GameWorld world = new GameWorldImpl();
        world.setNewTurn();
        world.getBallLauncher().launch();
        Stream.iterate(0, i -> i + 1).limit(1000).forEach(o -> world.update(1, Pair.of(0., 1.)));
        assertTrue(world.getBall().isOut());
    }

    @Test
    void ballStuck() {
        final GameWorld world = new GameWorldImpl();
        final Obstacle obstacle = new RectangularObstacle(Pair.of(0., 10.), 0);
        world.addObstacles(List.of(obstacle));
        world.setNewTurn();
        world.getBallLauncher().launch();
        Stream.iterate(0, i -> i + 1).limit(1000).forEach(o -> world.update(1, Pair.of(0., 1.)));
        assertTrue(world.getBall().isStuck());
        assertTrue(!world.getBall().isOut());
    }

}
