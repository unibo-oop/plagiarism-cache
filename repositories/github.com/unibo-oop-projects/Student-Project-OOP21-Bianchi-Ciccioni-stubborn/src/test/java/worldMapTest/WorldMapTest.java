package worldMapTest;

import org.junit.jupiter.api.Test;

import models.Entity;
import models.MOVEMENT;
import models.Player;
import models.Point2D;
import models.RandomSpawnStrategy;
import models.SpawnStrategy;
import models.WorldMap;
import models.WorldMapImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class WorldMapTest {

    private static int WIDTH = 51;
    private static int HEIGHT = 51;
    private static int NUM_ENEMIES = 5;
    private static int NUM_COLLECTABLES = 15;
    private static int EXPECTED_SIZE = 5;

    private SpawnStrategy randomStrategy = new RandomSpawnStrategy();
    private WorldMap worldMap = new WorldMapImpl(WIDTH, HEIGHT, NUM_ENEMIES, NUM_COLLECTABLES, randomStrategy);
    private Point2D startPlayerPos = new Point2D(WIDTH / 2, HEIGHT / 2);
    private Point2D randomPos = new Point2D(3, EXPECTED_SIZE);


    @Test
    public void testRandomSpawnStrategy() {
        int width = 10;
        int height = 10;
        int numEntities = 4;
        Set<Point2D> set1 = this.randomStrategy.getSpawnPoints(width, height, numEntities);
        set1.add(randomPos);
        Set<Point2D> set2 = this.randomStrategy.getSpawnPoints(width, height, numEntities);
        set2.add(randomPos);
        Set<Point2D> allSet = this.randomStrategy.getDoubleSpawnPoints(width, height, set1, set2);
        assertEquals(EXPECTED_SIZE, set1.size());
        assertEquals(EXPECTED_SIZE * 2, allSet.size());
        assertTrue(allSet.containsAll(set1));
        assertTrue(allSet.containsAll(set2));
    }

    @Test
    public void testWorldMapCreation() {
        Map<Point2D, Optional<Entity>> board = worldMap.getBoard();
        long count = board.values().stream().filter(v -> v.isPresent()).count();
        assertEquals(WIDTH * HEIGHT, board.size());
        assertTrue(board.get(startPlayerPos).get() instanceof Player);
        assertEquals(NUM_ENEMIES + NUM_COLLECTABLES + 1, count);
    }

    @Test
    public void testMovePlayer() {
        worldMap.movePlayer(MOVEMENT.LEFT);
        worldMap.movePlayer(MOVEMENT.UP);
        Map<Point2D, Optional<Entity>> board = worldMap.getBoard();
        Point2D leftPosition = new Point2D(24, 26);
        assertTrue(board.get(leftPosition).isPresent());
        assertTrue(board.get(leftPosition).get() instanceof Player);
        assertTrue(board.get(startPlayerPos).isEmpty()); 
    }
}
