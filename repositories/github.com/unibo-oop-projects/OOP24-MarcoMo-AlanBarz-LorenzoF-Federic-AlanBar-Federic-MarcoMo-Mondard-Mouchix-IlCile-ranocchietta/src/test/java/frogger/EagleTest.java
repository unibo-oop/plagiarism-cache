package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.Eagle;

final class EagleTest {
    private Eagle eagle;

    @BeforeEach
    void setUp() {
        final float speed = Constants.MIN_SPEED;
        final Direction dir = Direction.DOWN;
        final Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        final Position pos = new Position(0, 0);

        eagle = new Eagle(pos, dim, speed, dir);
    }

    @Test
    void startTest() {
        Position pos = eagle.getPos();
        eagle.move();
        assertEquals(pos, eagle.getPos()); // Should not move before starting

        eagle.start();
        eagle.move();
        assertNotEquals(pos, eagle.getPos()); // Should move after starting

        pos = eagle.getPos();
        eagle.stop();
        eagle.move();
        assertEquals(pos, eagle.getPos()); // Should not move after stopping
    }

    @Test
    void triggerTest() {
        eagle.setTrigger(0);
        assertEquals(0, eagle.getTrigger());
    }

    @Test
    void imgTest() {
        assertTrue(ImageTester.bufferedImagesEqual(LoadSave.getSprite("eagleDown.png"), eagle.getImage()));
        eagle.setDirection(Direction.UP);
        assertTrue(ImageTester.bufferedImagesEqual(LoadSave.getSprite("eagleDown.png"), eagle.getImage()));
    }
}
