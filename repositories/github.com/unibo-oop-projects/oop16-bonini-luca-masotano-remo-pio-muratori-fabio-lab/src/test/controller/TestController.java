package test.controller;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import controller.ControllerImpl;
import controller.GameLoop;
import controller.GameLoopImpl;
import model.GameModel;
import model.GameModelImpl;
import model.utils.Direction;

/**
 * 
 */
public class TestController {
    /**
     * tests controller methods.
     */
    @Test
    public void testController() {
        final GameModel gm = new GameModelImpl(false);
        final GameLoop gl = new GameLoopImpl(gm);

        assertFalse(gl.isRunning());

        assertFalse(ControllerImpl.get().isASavedGame());
        ControllerImpl.get().save();
        assertTrue(ControllerImpl.get().isASavedGame());
        try {
            assertTrue(Files.deleteIfExists(Paths.get("save.bin")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ControllerImpl.get().addMovements(Direction.UP);
        assertTrue(ControllerImpl.get().getObserverMovements().contains(Direction.UP));
        ControllerImpl.get().removeMovements(Direction.UP);
        assertFalse(ControllerImpl.get().getObserverMovements().contains(Direction.UP));

        ControllerImpl.get().addShoots(Direction.UP);
        assertTrue(ControllerImpl.get().getObserverShoot().contains(Direction.UP));
        ControllerImpl.get().removeShoots(Direction.UP);
        assertFalse(ControllerImpl.get().getObserverShoot().contains(Direction.UP));

    }

}
