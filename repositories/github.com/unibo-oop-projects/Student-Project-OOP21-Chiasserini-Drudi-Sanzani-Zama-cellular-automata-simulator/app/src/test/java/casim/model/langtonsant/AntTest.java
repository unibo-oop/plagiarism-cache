package casim.model.langtonsant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import casim.utils.Direction;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * Test class for {@link Ant}.
 */
class AntTest {

    private static final Coordinates2D<Integer> STARTINGPOSITION = CoordinatesUtil.of(0, 0);
    private static final Coordinates2D<Integer> NORTHRESULT = CoordinatesUtil.of(0, 1);
    private static final Coordinates2D<Integer> EASTRESULT = CoordinatesUtil.of(1, 0);
    private static final Ant ANT = new Ant(Direction.NORTH, STARTINGPOSITION);

    /**
     * Test for {@link Ant#move()} method.
     */
    @Test
    void testMove() {
        ANT.move();
        assertEquals(NORTHRESULT, ANT.getPosition());
        ANT.setDirection(Direction.SOUTH);
        ANT.move();
        assertEquals(STARTINGPOSITION, ANT.getPosition());
        ANT.setDirection(Direction.EAST);
        ANT.move();
        assertEquals(EASTRESULT, ANT.getPosition());
    }

    /**
     * Test for {@link Ant#turn(LangtonsAntCellState)} method.
     */
    @Test
    void testTurn() {
        ANT.setDirection(Direction.NORTH);
        ANT.turn(LangtonsAntCellState.OFF);
        assertEquals(Direction.EAST, ANT.getDirection());
        ANT.setDirection(Direction.NORTH);
        ANT.turn(LangtonsAntCellState.ON);
        assertEquals(Direction.WEST, ANT.getDirection());
        ANT.setDirection(Direction.WEST);
        ANT.turn(LangtonsAntCellState.OFF);
        assertEquals(Direction.NORTH, ANT.getDirection());
    }

}
