package frogger;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.model.implementations.Ground;
import frogger.model.implementations.RandomLevelFactory;
import frogger.model.implementations.River;
import frogger.model.implementations.Road;
import frogger.model.interfaces.GameObject;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;

/**
 * Test class for RandomLevelFactory.
 */
final class RandomLevelFactoryTest {

    private Level level;

    @BeforeEach
    void setUp() {
        final LevelFactory fact = new RandomLevelFactory();
        level = fact.createLevel();
    }

    @Test
    void testRandomLevelNotNull() {
        assertNotNull(level, "The level should not be null.");
    }

    @Test
    void laneTest() {
        assertEquals(level.getLanes().size(), Constants.ROAD_LANES + Constants.RIVER_LANES
        + Constants.GROUND_LANES);

        assertTrue(level.getLanes().get(0) instanceof Ground,
        "The first lane should be Ground.");
        assertTrue(level.getLanes().get(Constants.ROAD_LANES + 1) instanceof Ground,
        "The mid lane should be Ground.");
        assertTrue(level.getLanes().get(level.getLanes().size() - 1) instanceof Ground,
        "The last lane should be Ground.");

        for (int i = 1; i <= Constants.ROAD_LANES; i++) {
            assertTrue(level.getLanes().get(i) instanceof Road,
            "Lanes from 1 to Road_Lanes should be Road.");
        }
        for (int i = Constants.ROAD_LANES + 2; i < level.getLanes().size() - 1; i++) {
            assertTrue(level.getLanes().get(i) instanceof River,
            "Lanes from Road_Lanes + 2 to the penultimate should be River.");
        }
    }

    @Test
    void obstaclesTest() {
        level.getLanes().forEach(lane -> {
            if (!(lane instanceof Ground)) {
                assertTrue(lane.getLaneObstacles().size() <= Constants.MAX_OBSTACLES_NUMBER 
                && lane.getLaneObstacles().size() >= Constants.MIN_OBSTACLES_NUMBER);
            }
        });
    }

    @Test
    void eagleTest() {
        assertTrue(level.getEagles().size() <= Constants.MAX_EAGLES_NUMBER
        && level.getEagles().size() >= Constants.MIN_EAGLES_NUMBER);
    }

    @Test
    void pickableObjectsCountTest() {
        final int pickableCount = level.getPickableObjects().size();
        final int min = Constants.MIN_POWER_UP_NUMBER + Constants.MIN_COIN_NUMBER;
        final int max = Constants.MAX_POWER_UP_NUMBER + Constants.MAX_COIN_NUMBER;
        assertTrue(pickableCount >= min && pickableCount <= max);
    }

    @Test
    void pickableObjectsNoOverlapTest() {
        final var positions = level.getPickableObjects().stream()
            .map(GameObject::getPos)
            .collect(Collectors.toSet());
        assertEquals(level.getPickableObjects().size(), positions.size());
    }

}
