package fargoal.map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fargoal.commons.api.Position;
import fargoal.model.core.GameEngine;
import fargoal.model.manager.api.MatchType;
import fargoal.model.manager.impl.FloorManagerImpl;
import fargoal.model.map.api.FloorGenerator;
import fargoal.model.map.api.FloorMap;
import fargoal.model.map.impl.FloorGeneratorImpl;

class TestFloorGeneration {

    private static final int MAXIMUM_MILLIS_PER_MAP = 2000;
    private static final int NUMBER_OF_MAP_CREATIONS = 1000;

    private static FloorGenerator fc = new FloorGeneratorImpl();
    private static FloorMap map;

    @BeforeAll
    static void init() {
        map = fc.createFloor(new FloorManagerImpl(new GameEngine(), MatchType.NORMAL));
    }

    //CHECKSTYLE: MagicNumber OFF
    //The numbers here represent marks that I want to be true
    @Test
    void testRandom() {
        final Position pos = map.getRandomTile();
        int timesHappend = 0;
        for (int i = 0; i < 100; i++) {
            if (map.getRandomTile().equals(pos)) {
                timesHappend++;
            }
        }
        assertTrue(timesHappend < 90);
        assertTrue(timesHappend < 70);
        assertTrue(timesHappend < 50);
    }
    //CHECKSTYLE: MagicNumber ON

    //A test to see if the algorithm gets stuck or takes too long to generate a map
    //I dont want the map to take a noticeable amount of time to generate
    @Test
    void testGeneration() {
        final FloorManagerImpl managerImpl = new FloorManagerImpl(new GameEngine(), MatchType.NORMAL);
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUMBER_OF_MAP_CREATIONS; i++) {
            managerImpl.increaseFloorLevel();
        }
        final long endTime = System.currentTimeMillis() - startTime;
        assertTrue(endTime < NUMBER_OF_MAP_CREATIONS * MAXIMUM_MILLIS_PER_MAP); 
    }
}
