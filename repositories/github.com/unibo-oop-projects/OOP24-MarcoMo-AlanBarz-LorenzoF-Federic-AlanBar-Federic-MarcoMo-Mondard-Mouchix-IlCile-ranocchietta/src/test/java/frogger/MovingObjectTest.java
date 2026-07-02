package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.MovingObjectImpl;
import frogger.model.interfaces.MovingObject;

final class MovingObjectTest {
    private MovingObject movingObject;

    @BeforeEach
    void setUp() {
        final float speed = Constants.MIN_SPEED;
        final Direction dir = Direction.LEFT;
        final Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        final Position pos = new Position(100, 100);

        movingObject = new MovingObjectImpl(pos, dim, speed, dir);
    }

    @Test
    void moveTest() {
        final Position newPos = new Position(5, 5);

        //move left
        movingObject.setDirection(Direction.LEFT);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(newPos.x() - movingObject.getSpeed(), newPos.y()));

        //move right
        movingObject.setDirection(Direction.RIGHT);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(newPos.x() + movingObject.getSpeed(), newPos.y()));

        //move up
        movingObject.setDirection(Direction.UP);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(newPos.x(), newPos.y() + movingObject.getSpeed()));

        //move down
        movingObject.setDirection(Direction.DOWN);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(newPos.x(), newPos.y() - movingObject.getSpeed()));
    }

    @Test
    void checkRestartTest() {
        Position newPos;

        //check right
        newPos = new Position(Constants.MAX_X + 1, 0);
        movingObject.setDirection(Direction.RIGHT);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(Constants.MIN_X - 1, newPos.y()));

        //check top
        newPos = new Position(0, Constants.MAX_Y + 1);
        movingObject.setDirection(Direction.UP);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(newPos.x(), Constants.MIN_Y - 1));

        //check left
        newPos = new Position(Constants.MIN_X - 1, 0);
        movingObject.setDirection(Direction.LEFT);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(Constants.MAX_X + 1, newPos.y()));

        //check bottom
        newPos = new Position(0, Constants.MIN_Y - 1);
        movingObject.setDirection(Direction.DOWN);
        movingObject.setPos(newPos);
        movingObject.move();
        assertEquals(movingObject.getPos(), new Position(newPos.x(), Constants.MAX_Y + 1));
    }
}
