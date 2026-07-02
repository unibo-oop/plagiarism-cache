package collisionsTest;

import org.junit.jupiter.api.Test;

import models.Entity;
import models.MOVEMENT;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import models.WorldMapImpl;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {

    static int WIDTH = 3;
    static int HEIGHT = 3;
    static int ZERO = 0;
    static int NUM_ENEMIES = 7;
    static int NUM_COLLECTABLES = 1;

    private SpawnStrategy strategy = new RandomSpawnStrategy();

    @Test
    public void testEnemyCollision() {
        WorldMap worldMap = new WorldMapImpl(WIDTH, HEIGHT, NUM_ENEMIES + NUM_COLLECTABLES, ZERO, strategy);
        Point2D playerPos = new Point2D(WIDTH / 2, HEIGHT / 2);
        worldMap.movePlayer(MOVEMENT.LEFT);
        Point2D afterMovementPosPoint2d = worldMap.getPlayerPos();
        assertEquals(playerPos, afterMovementPosPoint2d);
    }

    @Test
    public void testBorderCollision() {
        WorldMap bigMap = new WorldMapImpl(WIDTH, HEIGHT, ZERO, ZERO, strategy);
        bigMap.movePlayer(MOVEMENT.UP);
        Point2D newPos = new Point2D(WIDTH / 2, HEIGHT - 1);
        Point2D playerPos = bigMap.getPlayerPos();
        assertEquals(newPos, playerPos);
        bigMap.movePlayer(MOVEMENT.UP);
        bigMap.movePlayer(MOVEMENT.UP);
        playerPos = bigMap.getPlayerPos();
        assertEquals(newPos, playerPos);
    }

    @Test
    public void testCollectableCollision() {
        WorldMap collMap = new WorldMapImpl(WIDTH, HEIGHT, ZERO, NUM_ENEMIES + NUM_COLLECTABLES, strategy);
        Map<Point2D, Optional<Entity>> board = collMap.getBoard();
        long count = board.values().stream().filter(v -> v.isPresent()).count();
        Point2D playerPos = Point2D.sum(new Point2D(WIDTH / 2, HEIGHT / 2), MOVEMENT.UP.movement);
        collMap.movePlayer(MOVEMENT.UP);
        assertTrue(board.get(playerPos).get() instanceof Player);
        count = board.values().stream().filter(v -> v.isPresent()).count();
        assertEquals(NUM_ENEMIES + NUM_COLLECTABLES, count);
    }
}
