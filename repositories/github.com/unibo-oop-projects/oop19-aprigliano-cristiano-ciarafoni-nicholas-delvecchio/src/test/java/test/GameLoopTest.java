package test;

import model.mario.MarioModel;
import model.mario.MarioModelImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the Movement of Mario.
 */
class GameLoopTest {

    private static final double POSY_MOVEMENT = 6;
    private static final double TEST = 200;

    @Test
    void marioFall() {
        double expected;
        final MarioModel mario = new MarioModelImpl();
        expected = (mario.getMarioPosY() + POSY_MOVEMENT);
        mario.setPosY(TEST + POSY_MOVEMENT);
        assertEquals(expected, mario.getMarioPosY());
    }

    @Test
    void marioJump() {
        double expected;
        final MarioModel mario = new MarioModelImpl();
        expected = (mario.getMarioPosY() - POSY_MOVEMENT);
        mario.setPosY(TEST - POSY_MOVEMENT);
        assertEquals(expected, mario.getMarioPosY());
    }
}
