package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.Car;
import frogger.model.implementations.Ground;
import frogger.model.implementations.MovingObjectFactoryImpl;
import frogger.model.implementations.River;
import frogger.model.implementations.Road;
import frogger.model.implementations.Trunk;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;

/**
 * Test class for the concept of Lane.
 */
final class LaneTest {
    private MovingObject ob1;
    private MovingObject ob2;
    private float speed;
    private Direction dir;

    @BeforeEach
    void setUp() {
        speed = Constants.MIN_SPEED;
        dir = Direction.LEFT;
        final Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        final Position pos = new Position(0, 0);
        final MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();

        ob1 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Trunk.class);
        ob2 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Car.class);
    }

    @Test
    void riverTest() {
        final Lane l1 = new River(speed, dir);
        l1.addMovingObject(ob2);
        l1.addMovingObject(ob1);
        assertEquals(Set.of(ob1), l1.getLaneObstacles());
    }

    @Test
    void roadTest() {
        final Lane l1 = new Road(speed, dir);
        l1.addMovingObject(ob1);
        l1.addMovingObject(ob2);
        assertEquals(Set.of(ob2), l1.getLaneObstacles());
    }

    @Test
    void groundTest() {
        final Lane l1 = new Ground();
        l1.addMovingObject(ob1);
        l1.addMovingObject(ob2);
        assertEquals(Set.of(), l1.getLaneObstacles());
    }
}
