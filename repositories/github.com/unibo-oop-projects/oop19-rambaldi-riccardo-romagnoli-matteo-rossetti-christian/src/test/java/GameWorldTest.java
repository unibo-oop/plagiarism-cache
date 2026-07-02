import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.obstacle.Obstacle;
import model.obstacle.RectangularObstacle;
import model.world.GameWorld;
import model.world.GameWorldImpl;

class GameWorldTest {

    @Test
    void newTurnTest() {
        final GameWorld world = new GameWorldImpl();
        world.setNewTurn();
        assertTrue(world.getBall().isReadyToLaunch());
    }

    @Test
    void obsatacleManagementTest() {
        final GameWorld world = new GameWorldImpl();
        //test correct insertion of obstacles
        List<Obstacle> obstacles = List.of(
                new RectangularObstacle(Pair.of(0., 10.), Math.toDegrees(Math.PI / 4)),
                new RectangularObstacle(Pair.of(0., 10.), -Math.toDegrees(Math.PI / 4))
        );
        world.addObstacles(obstacles);
        assertEquals(world.getNotHitObstacles(), obstacles);
        //test correct management of hit obstacles
        world.setNewTurn();
        world.getBallLauncher().launch();
        Stream.iterate(0, i -> i + 1).limit(1000).forEach(o -> world.update(1, Pair.of(0., 1.)));
        assertEquals(world.getHitObstacles(), obstacles);
        //test correct deletion of hit obstacles
        world.setNewTurn();
        assertTrue(world.getHitObstacles().isEmpty());
    }
}
