package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.Coin;
import frogger.model.implementations.Eagle;
import frogger.model.implementations.LevelImpl;
import frogger.model.implementations.MovingObjectFactoryImpl;
import frogger.model.implementations.PickableObjectFactory;
import frogger.model.implementations.PowerUpImpl;
import frogger.model.implementations.River;
import frogger.model.implementations.Road;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.MovingObjectFactory;
import frogger.model.interfaces.PickableObject;

/**
 * Test class for the concept of Level.
 */
final class LevelTest {

    private Level level;

    @BeforeEach
    void setUp() {
        level = new LevelImpl();
    }

    @Test
    void laneTest() {
        final float speed = Constants.MIN_SPEED;
        final Direction dir = Direction.DOWN;

        final Lane l1 = new Road(speed, dir);
        final Lane l2 = new Road(speed, dir);
        final Lane l3 = new River(speed, dir);
        final Lane l4 = new River(speed, dir);

        level.addLane(l1);
        level.addLane(l2);
        level.addLane(l3);
        level.addLane(l4);
        assertEquals(List.of(l1, l2, l3, l4), level.getLanes());
    }

    @Test
    void pickableObjectTest() {
        final Pair dim = new Pair(Constants.PICKABLE_OBJECT_WIDTH, Constants.PICKABLE_OBJECT_HEIGHT);
        final Position pos = new Position(0, 0);

        final PickableObject p1 = PickableObjectFactory.createPickableObject(PowerUpImpl.class, pos, dim);
        final PickableObject p2 = PickableObjectFactory.createPickableObject(PowerUpImpl.class, pos, dim);
        final PickableObject c1 = PickableObjectFactory.createPickableObject(Coin.class, pos, dim);
        final PickableObject c2 = PickableObjectFactory.createPickableObject(Coin.class, pos, dim);

        level.addPickableObject(p1);
        level.addPickableObject(p2);
        level.addPickableObject(c1);
        level.addPickableObject(c2);
        assertEquals(List.of(p1, p2, c1, c2), level.getPickableObjects());
    }

    @Test
    void eagleTest() {
        final Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        final MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        final Position pos = new Position(0, 0);
        final float speed = Constants.MIN_SPEED;
        final Direction dir = Direction.DOWN;

        final Eagle e1 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        final Eagle e2 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        final Eagle e3 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        final Eagle e4 = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);

        level.addEagle(e1);
        level.addEagle(e2);
        level.addEagle(e3);
        level.addEagle(e4);
        assertEquals(List.of(e1, e2, e3, e4), level.getEagles());
        assertEquals(List.of(e1, e2, e3, e4), level.getAllObstacles());
    }
}
