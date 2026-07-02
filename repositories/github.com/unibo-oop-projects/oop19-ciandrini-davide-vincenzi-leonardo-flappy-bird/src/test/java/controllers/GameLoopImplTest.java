package controllers;

import model.bird.Bird;
import model.bird.BirdImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GameLoopImplTest {

    private static final double N = 2.75;
    private static final double TEST = 50;

    @Test
    void birdUpdateDown() {
        double expected;
        final Bird flappy = new BirdImpl();
        expected = (flappy.getPosY() + N);
        flappy.setPosY(TEST + N);
        assertEquals(expected, flappy.getPosY());
    }

    @Test
    void birdUpdateUp() {
        double expected;
        final Bird flappy = new BirdImpl();
        expected = (flappy.getPosY() - N);
        flappy.setPosY(TEST - N);
        assertEquals(expected, flappy.getPosY());
    }
}
