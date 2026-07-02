package entitiesTest;

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

public class PlayerTest {
    static int WIDTH = 51;
    static int HEIGHT = 51;
    static int NUM_ENEMIES = 5;
    static int NUM_COLLECTABLES = 15;
    static int EXPECTED_SIZE = 5;

    private SpawnStrategy randomStrategy = new RandomSpawnStrategy();
    private WorldMap worldMap = new WorldMapImpl(WIDTH, HEIGHT, NUM_ENEMIES, NUM_COLLECTABLES, randomStrategy);
    private Point2D startPlayerPos = new Point2D(WIDTH / 2, HEIGHT / 2);

    @Test
    public void testMovePlayer() {
        worldMap.movePlayer(MOVEMENT.LEFT);
        worldMap.movePlayer(MOVEMENT.UP);
        Map<Point2D, Optional<Entity>> board = worldMap.getBoard();
        Point2D leftPosition = new Point2D(24,26);
        assertTrue(board.get(leftPosition).isPresent());
        assertTrue(board.get(leftPosition).get() instanceof Player);
        assertTrue(board.get(startPlayerPos).isEmpty());
    }
}
