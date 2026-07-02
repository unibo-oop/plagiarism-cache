package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.controller.Controller;
import frogger.controller.GameControllerImpl;

final class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new GameControllerImpl();
    }

    @Test
    void inPixelTest() {
        final int a = 5; // Example x-coordinate in game units
        assertEquals(
            (double) Math.round(Constants.FRAME_WIDTH / 2.0 + a * Constants.BLOCK_WIDTH),
            controller.getXinPixel(a)
        );

        assertEquals(
            (double) Math.round(Constants.FRAME_HEIGHT / 2.0 - Constants.BLOCK_HEIGHT / 2.0 - a * Constants.BLOCK_HEIGHT),
            controller.getYinPixel(a)
        );
    }
}
