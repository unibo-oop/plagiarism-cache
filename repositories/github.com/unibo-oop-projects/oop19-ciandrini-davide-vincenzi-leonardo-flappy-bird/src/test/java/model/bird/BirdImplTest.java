package model.bird;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirdImplTest {

    @Test
    public void testFlappyUpdate() {
        final double n = 10;
        final double x = 60;
        double expected;

        final Bird bird = new BirdImpl();
        bird.setPosY(x);
        expected = bird.getPosY() + n;
        bird.setPosY(expected);
        assertEquals(expected, bird.getPosY());
    }
}
