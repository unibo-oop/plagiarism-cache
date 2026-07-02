package it.unibo;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import it.unibo.controller.PuyoExplosionController;
import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;

class PuyoExplosionControllerTest {
    @Test
    void testDeathClockCountdown() {
        PuyoInterface puyo = mock(PuyoInterface.class);
        when(puyo.getDeathClock()).thenReturn(java.util.Optional.of(2));
        Grid grid = mock(Grid.class);
        when(grid.getRows()).thenReturn(1);
        when(grid.getCols()).thenReturn(1);
        when(grid.getPuyo(0, 0)).thenReturn(puyo);
        PuyoExplosionController controller = new PuyoExplosionController(grid);
        controller.onTick();
    }

    @Test
    void testPuyoRemoval() {
        PuyoInterface puyo = mock(PuyoInterface.class);
        when(puyo.getDeathClock()).thenReturn(java.util.Optional.of(1));
        Grid grid = mock(Grid.class);
        when(grid.getRows()).thenReturn(1);
        when(grid.getCols()).thenReturn(1);
        when(grid.getPuyo(0, 0)).thenReturn(puyo);
        PuyoExplosionController controller = new PuyoExplosionController(grid);
        controller.onTick();
    }

    @Test
    void testNoPuyo() {
        Grid grid = mock(Grid.class);
        when(grid.getRows()).thenReturn(1);
        when(grid.getCols()).thenReturn(1);
        when(grid.getPuyo(0, 0)).thenReturn(null);
        PuyoExplosionController controller = new PuyoExplosionController(grid);
        controller.onTick();
    }

    @Test
    void testNormalPuyo() {
        PuyoInterface puyo = mock(PuyoInterface.class);
        when(puyo.getDeathClock()).thenReturn(java.util.Optional.empty());
        Grid grid = mock(Grid.class);
        when(grid.getRows()).thenReturn(1);
        when(grid.getCols()).thenReturn(1);
        when(grid.getPuyo(0, 0)).thenReturn(puyo);
        PuyoExplosionController controller = new PuyoExplosionController(grid);
        controller.onTick();
    }
}
