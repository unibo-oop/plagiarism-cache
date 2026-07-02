package tests;

import org.junit.Test;

import model.obstacle.CentersIterator;
import model.obstacle.GameObjectImpl.GameObjectType;
import model.obstacle.TurtlePositionStrategy;
import model.world.World;
import model.world.WorldImpl;

/**
 * Unit tests for Turtles.
 */
public class TestTurtle {

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
                .addRiverWithTurtle(2, 3)
                .addRiver(2, GameObjectType.MEDIUM_LOG, 2)
                .addRiver(4, GameObjectType.SMALL_LOG, 4)
                .addRiverWithTurtle(3, 2)
                .addRiver(4, GameObjectType.SMALL_LOG, 4)
                .build();

        CentersIterator c = new TurtlePositionStrategy(2);

        System.out.println(c.next());
        System.out.println(c.next());
        System.out.println(c.hasNext());



    }
}
