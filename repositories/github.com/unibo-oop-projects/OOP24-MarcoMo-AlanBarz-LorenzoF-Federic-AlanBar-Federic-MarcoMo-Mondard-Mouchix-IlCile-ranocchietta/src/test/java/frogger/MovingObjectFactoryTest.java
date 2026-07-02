package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.Car;
import frogger.model.implementations.Eagle;
import frogger.model.implementations.MovingObjectFactoryImpl;
import frogger.model.implementations.Trunk;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;

final class MovingObjectFactoryTest {

    private final MovingObjectFactory factory = new MovingObjectFactoryImpl();

    @Test
    void instance() {
        final float speed = Constants.MIN_SPEED;
        final Direction dir = Direction.LEFT;
        final Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        final Position pos = new Position(100, 100);
        MovingObject ob;

        //Car
        ob = factory.createMovingObject(pos, dim, speed, dir, Car.class);
        assertEquals(Car.class, ob.getClass());

        //Trunk
        ob = factory.createMovingObject(pos, dim, speed, dir, Trunk.class);
        assertEquals(Trunk.class, ob.getClass());

        //Eagle
        ob = factory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        assertEquals(Eagle.class, ob.getClass());

    }
}
