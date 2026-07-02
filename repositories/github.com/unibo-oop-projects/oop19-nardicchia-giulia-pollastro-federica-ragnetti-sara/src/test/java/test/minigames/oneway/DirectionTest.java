package test.minigames.oneway;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.minigames.oneway.Direction;

/**
 * Class to test if {@link Direction} works correctly.
 *
 */
public class DirectionTest {
    private static List<Direction> steps;
    private static int counter;

    @BeforeAll
    public static final void init() {
        steps = new ArrayList<>();
        counter = 0;
    }

    /**
     * Test if steps have the correct increments.
     */
    @Test
    public void deltaTest() {
        steps.add(Direction.UP);
        this.checkPosition(-1, 0);

        steps.add(Direction.DOWN);
        this.checkPosition(1, 0);

        steps.add(Direction.LEFT);
        this.checkPosition(0, -1);

        steps.add(Direction.RIGHT);
        this.checkPosition(0, 1);
    }

    @Test
    private void checkPosition(final int x, final int y) {
        assertEquals(Direction.delta(steps.get(counter)).getX(), x);
        assertEquals(Direction.delta(steps.get(counter)).getY(), y);
        counter++;
    }
}
