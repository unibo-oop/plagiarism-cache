package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import model.lane.LaneType;
import model.obstacle.GameObjectImpl.GameObjectType;
import model.world.World;
import model.world.WorldImpl;

/**
 * JUnit tests for world.
 */
public class TestsWorld {

    private static final String MESSAGE_SAME = "Should be the same";
    private static final String MESSAGE_TRUE = "Should be the true";
    private static final String MESSAGE_FALSE = "Should be the false";

    private static final int NUMBER_OF_LANE = 12;
    private static final int SIXTH_LANE = 6;
    private static final int SENVENTH_LANE = 7;

    /**
     * WorldImpl tests.
     */
    @Test
    public void testWorldCreation() {
        final World world = new WorldImpl.Builder().addSafeLane()
                                                   .addStreet(2, GameObjectType.PURPLE_CAR, 4)
                                                   .addStreet(4, GameObjectType.RACE_CAR, 2)
                                                   .addStreet(2, GameObjectType.WHITE_CAR, 3)
                                                   .addStreet(2, GameObjectType.TRUCK, 3)
                                                   .addStreet(2, GameObjectType.TRUCK, 3)
                                                   .addSafeLane()
                                                   .addRiver(3, GameObjectType.SMALL_LOG, 4)
                                                   .addRiver(2, GameObjectType.MEDIUM_LOG, 2)
                                                   .addRiver(4, GameObjectType.SMALL_LOG, 4)
                                                   .addRiver(3, GameObjectType.BIG_LOG, 1)
                                                   .addRiver(4, GameObjectType.SMALL_LOG, 4)
                                                   .build();

        // tests for correct lane type
        assertEquals(TestsWorld.MESSAGE_SAME, world.getLane().size(), TestsWorld.NUMBER_OF_LANE);
        assertEquals(TestsWorld.MESSAGE_SAME, world.getLane().get(0).getLaneType(), LaneType.SAFE);
        assertEquals(TestsWorld.MESSAGE_SAME, world.getLane().get(1).getLaneType(), LaneType.STREET);
        assertEquals(TestsWorld.MESSAGE_SAME, world.getLane().get(TestsWorld.SIXTH_LANE).getLaneType(), LaneType.SAFE);
        assertEquals(TestsWorld.MESSAGE_SAME, world.getLane().get(TestsWorld.SENVENTH_LANE).getLaneType(), LaneType.RIVER);

        // tests for correct obstacle set
        assertTrue(TestsWorld.MESSAGE_FALSE, world.getLane().get(0).getObstacle().isEmpty());
        assertFalse(TestsWorld.MESSAGE_TRUE, world.getLane().get(1).getObstacle().isEmpty());
        assertTrue(TestsWorld.MESSAGE_FALSE, world.getLane().get(TestsWorld.SIXTH_LANE).getObstacle().isEmpty());

      // tests for correct obstacles position
        final Set<Double> obstacleSet1 = world.getLane().get(1).getObstacle().stream()
                                                                             .map(o -> o.getCenter())
                                                                             .collect(Collectors.toSet());

//        assertTrue(TestsWorld.MESSAGE_TRUE, obstacleSet1.contains(ObstacleType.PURPLE_CAR.getWidth() / 2 + 0 * ObstacleType.PURPLE_CAR.getWidth() + 0 * ObstacleType.PURPLE_CAR.getDistance()));
//        assertTrue(TestsWorld.MESSAGE_TRUE, obstacleSet1.contains(ObstacleType.PURPLE_CAR.getWidth() / 2 + 1 * ObstacleType.PURPLE_CAR.getWidth() + 1 * ObstacleType.PURPLE_CAR.getDistance()));
//        assertTrue(TestsWorld.MESSAGE_TRUE, obstacleSet1.contains(ObstacleType.PURPLE_CAR.getWidth() / 2 + 2 * ObstacleType.PURPLE_CAR.getWidth() + 2 * ObstacleType.PURPLE_CAR.getDistance()));
//        assertTrue(TestsWorld.MESSAGE_TRUE, obstacleSet1.contains(ObstacleType.PURPLE_CAR.getWidth() / 2 + 3 * ObstacleType.PURPLE_CAR.getWidth() + 3 * ObstacleType.PURPLE_CAR.getDistance()));

        final Set<Double> obstacleSet2 = world.getLane().get(2).getObstacle().stream()
                                                                             .map(o -> o.getCenter())
                                                                             .collect(Collectors.toSet());

//        assertTrue(TestsWorld.MESSAGE_TRUE, obstacleSet2.contains(ObstacleType.RACE_CAR.getWidth() / 2 + 0 * ObstacleType.RACE_CAR.getWidth() + 0 * ObstacleType.RACE_CAR.getDistance()));
//        assertTrue(TestsWorld.MESSAGE_TRUE, obstacleSet2.contains(ObstacleType.RACE_CAR.getWidth() / 2 + 1 * ObstacleType.RACE_CAR.getWidth() + 1 * ObstacleType.RACE_CAR.getDistance()));

        // tests check errors of min and max number of lane
        try {
            new WorldImpl.Builder().addSafeLane()
            .addStreet(2, GameObjectType.PURPLE_CAR, 4)
            .addStreet(4, GameObjectType.RACE_CAR, 2)
            .addStreet(2, GameObjectType.WHITE_CAR, 3)
            .addStreet(2, GameObjectType.TRUCK, 3)
            .addStreet(2, GameObjectType.TRUCK, 3)
            .addSafeLane()
            .addRiver(3, GameObjectType.SMALL_LOG, 4)
            .addRiver(2, GameObjectType.MEDIUM_LOG, 2)
            .addRiver(4, GameObjectType.SMALL_LOG, 4)
            .addRiver(3, GameObjectType.BIG_LOG, 1)
            .addRiver(4, GameObjectType.SMALL_LOG, 4)
            .addSafeLane()
            .build();
            fail("Can't add more than 12 lane");
        } catch (Exception e) {

        }

        // tests check errors of min and max number of lane
        try {
            new WorldImpl.Builder().addSafeLane()
            .addStreet(2, GameObjectType.PURPLE_CAR, 4)
            .addStreet(4, GameObjectType.RACE_CAR, 2)
            .addStreet(2, GameObjectType.WHITE_CAR, 3)
            .addStreet(2, GameObjectType.TRUCK, 3)
            .addStreet(2, GameObjectType.TRUCK, 3)
            .addSafeLane()
            .addRiver(3, GameObjectType.SMALL_LOG, 4)
            .addRiver(2, GameObjectType.MEDIUM_LOG, 2)
            .addRiver(4, GameObjectType.SMALL_LOG, 4)
            .addRiver(3, GameObjectType.BIG_LOG, 1)
            .build();
            fail("Can't add less than 12 lane");
        } catch (Exception e) {

        }

        // tests check errors of obstacles insertion
        try {
            new WorldImpl.Builder().addSafeLane()
            .addRiver(1, GameObjectType.PURPLE_CAR, 1)
            .build();
            fail("Can't add vehicles in the river");
        } catch (Exception e) {

        }

        try {
            new WorldImpl.Builder().addSafeLane()
            .addStreet(1, GameObjectType.BIG_LOG, 1)
            .build();
            fail("Can't add logs in the street");
        } catch (Exception e) {

        }

//        try {
//            new WorldImpl.Builder().addSafeLane()
//            .addRiver(1, ObstacleType.BIG_LOG, ObstacleType.BIG_LOG.getMaxPerLane() + 1)
//            .build();
//            fail("Obstacle overflow");
//        } catch (Exception e) {
//
//        }

    }

}
