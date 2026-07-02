package snakerunner.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import snakerunner.commons.Point2D;
import snakerunner.model.Direction;
import snakerunner.model.Snake;

class SnakeTest {

    private static final int START_X = 10;
    private static final int START_Y = 10;
    private static final int EXPECTED_SIZE = 5;

    private static final int MOVE_START_X = 5;
    private static final int MOVE_START_Y = 5;

    private static final int EXPECTED_RIGHT_X = 6;
    private static final int EXPECTED_RIGHT_Y = 5;

    private static final int EXPECTED_UP_X = 6;
    private static final int EXPECTED_UP_Y = 4;

    @Test
    void mySnakeTest() {

        final Point2D<Integer, Integer> startPos = new Point2D<>(START_X, START_Y);
        final Snake testSnake = new Snake(startPos);

        assertEquals(START_X, testSnake.getHead().getX());
        assertEquals(START_Y, testSnake.getHead().getY());

        final int size = testSnake.getFullBody().size();
        assertEquals(EXPECTED_SIZE, size);
    }

    @Test
    void movementTest() {
        final Point2D<Integer, Integer> p = new Point2D<>(MOVE_START_X, MOVE_START_Y);
        final Snake s = new Snake(p);

        s.move();

        assertEquals(EXPECTED_RIGHT_X, s.getHead().getX());
        assertEquals(EXPECTED_RIGHT_Y, s.getHead().getY());

        s.setDirection(Direction.UP);
        s.move();

        assertEquals(EXPECTED_UP_X, s.getHead().getX());
        assertEquals(EXPECTED_UP_Y, s.getHead().getY());
    }

    @Test
    void testWrongTurn() {
        final Snake s = new Snake(new Point2D<>(MOVE_START_X, MOVE_START_Y));

        s.setDirection(Direction.LEFT);

        final Direction current = s.getCurrentDirection();
        assertEquals(Direction.RIGHT, current);
    }
}


