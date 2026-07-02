package test;

import model.mario.MarioModel;
import model.mario.MarioModelImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarioModelTest {
    @Test
    void marioUpdate() {
        final double MOVEMENT = 6;
        final double INITIAL_POSY = 200;

        double expected;
        final MarioModel mario = new MarioModelImpl();
        mario.setPosY(INITIAL_POSY);
        expected = mario.getMarioPosY() + MOVEMENT;
        mario.setPosY(expected);
        assertEquals(expected, mario.getMarioPosY());
    }
}
