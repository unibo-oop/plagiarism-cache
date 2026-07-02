package model.level;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bounding_box.BoundingBox;
import model.bounding_box.RectBoundingBox;
import model.entities.zombie.Zombie;
import model.level.types.LevelTutorial;
import model.level.types.Level;

import model.physics.physics_level.PhysicsLevelComponent;
import model.physics.physics_level.PhysicsLevelTutComponent;
import org.apache.commons.lang3.tuple.Pair;

public class LevelTest {

    private Level level;
    private final double width = 4000;
    private final double height = 2500;

    @BeforeEach
    void setUp() throws Exception {
        BoundingBox bbox = new RectBoundingBox(
                Pair.of(0.0, height),
                Pair.of(width, 0.0));
        PhysicsLevelComponent physics = new PhysicsLevelTutComponent();
        level = new LevelTutorial(width, height, bbox, physics);
    }

    @Test
    void testLevelDimensions() {
        assertEquals(width, level.getLevelWidth());
        assertEquals(height, level.getLevelHeight());
    }

    @Test
    void testLevelBBoxNotNull() {
        assertNotNull(level.getLevelBBox());
    }

    @Test
    void testSurvivorInitialized() {
        assertNotNull(level.getSurvivorOnLevel(), "Survivor should be initialized by LevelManagerBase");
    }

    @Test
    void testZombieListInitializedWithAtLeastOneZombie() {
        List<Zombie> zombies = level.getZombieOnLevel();
        assertNotNull(zombies);
        assertFalse(zombies.isEmpty(), "Initial zombie list should not be empty");
    }

    @Test
    void testProjectilesInitiallyEmpty() {
        assertTrue(level.getProjectilesOnLevel().isEmpty(), "Projectile list should be empty initially");
    }

    @Test
    void testLevelInitiallyNotCompleted() {
        assertFalse(level.isLevelCompleted());
    }

    @Test
    void testLevelCompletionAfterWaves() {

        int secondsBetweenWaves = 10;
        int maxWave = 3;
        int millisInSecond = 1000;
        int step = 500;

        int totalTimeMs = (secondsBetweenWaves + 1) * millisInSecond * maxWave;

        for (int time = 0; time < totalTimeMs; time += step) {
            level.updateLevelState(step);
            level.getZombieOnLevel().clear();
        }

        assertTrue(level.isLevelCompleted(), "Level should be completed after all waves and no zombies");
    }

    @Test
    void testUpdateDoesNotCrash() {
        assertDoesNotThrow(() -> level.updateLevelState(100));
    }
}
