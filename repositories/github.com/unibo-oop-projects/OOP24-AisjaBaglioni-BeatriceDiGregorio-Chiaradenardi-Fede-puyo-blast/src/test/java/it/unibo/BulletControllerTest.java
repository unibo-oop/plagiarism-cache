package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.controller.BulletController;
import it.unibo.controller.ProgressBarController;
import it.unibo.controller.ScoreController;
import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.Grid;
import it.unibo.model.KeyboardModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Puyo;
import it.unibo.model.Scale;
import it.unibo.view.CannonView;

import static org.mockito.Mockito.*;

import java.awt.event.KeyEvent;

class BulletControllerTest {
    @Test
    void test() {
        Scale scale = new Scale(1000);
        BulletModel bulletModel = new BulletModel();
        Grid grid = new Grid(2, 2);
        grid.addPuyo(new Puyo("red", 0, 0), 0, 0);
        grid.addPuyo(new Puyo("red", 1, 0), 1, 0);
        grid.addPuyo(new Puyo("red", 0, 1), 0, 1);
        grid.addPuyo(new Puyo("blue", 1, 1), 1, 1);
        KeyboardModel keyboardModel = mock(KeyboardModel.class);
        when(keyboardModel.isKeyPressed(KeyEvent.VK_SPACE)).thenReturn(true);
        ProgressBarController progressBarController = mock(ProgressBarController.class);
        when(progressBarController.resetBar()).thenReturn(false);
        CannonModel cannonModel = new CannonModel();
        for (int i = 0; i < 100; i++) {
            cannonModel.moveLeft();
            cannonModel.aimUp();
        }
        assertEquals(cannonModel.getX(), 0);
        assertEquals(cannonModel.getAngle(), 1);
        CannonView cannonView = mock(CannonView.class);
        when(cannonView.getCannonModel()).thenReturn(cannonModel);
        when(cannonView.getCenter()).thenReturn(new Point2DI(0, 0));
        ScoreController scoreController = mock(ScoreController.class);
        BulletController bulletController = new BulletController(bulletModel, grid, keyboardModel,
                progressBarController, null, scoreController, scale);
        bulletController.setCannonView(cannonView);
        for (int i = 0; i < 15 + 1; i++) {
            bulletController.onTick();
        }
        assertTrue(grid.getPuyo(0, 0).getDeathClock().isPresent());
        assertTrue(grid.getPuyo(0, 1).getDeathClock().isPresent());
        assertTrue(grid.getPuyo(1, 0).getDeathClock().isPresent());
        assertFalse(grid.getPuyo(1, 1).getDeathClock().isPresent());
    }
}
