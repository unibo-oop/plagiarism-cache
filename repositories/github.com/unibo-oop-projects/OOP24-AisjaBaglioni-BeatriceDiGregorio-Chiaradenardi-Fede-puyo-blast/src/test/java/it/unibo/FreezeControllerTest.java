package it.unibo;

import static org.mockito.Mockito.*;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.controller.FreezeController;

/**
 * Unit test for {@link FreezeController} class.
 * This test ensures that the freeze mechanics are correctly applied to Puyo elements in the grid.
 */
class FreezeControllerTest {
    
    private Grid grid;
    private FreezeController freezeController;
    private PuyoInterface puyo;

    /**
     * Sets up the test environment before each test execution.
     * Uses Mockito to create a mock Grid and a mock PuyoInterface.
     */
    @BeforeEach
    void setUp() {
        grid = mock(Grid.class); 
        puyo = mock(PuyoInterface.class); 
        freezeController = new FreezeController(grid); 
    }

    /**
     * Tests that a Puyo's freeze duration decreases on each tick.
     * If a Puyo is frozen, its freeze counter should decrement until it reaches zero.
     */
    @Test
    void testFreezeDurationDecrements() {
        when(grid.getRows()).thenReturn(1);
        when(grid.getCols()).thenReturn(1);
        when(grid.getPuyo(0, 0)).thenReturn(puyo);
        when(puyo.getDeathClock()).thenReturn(Optional.empty());
        when(puyo.getFreezeClock()).thenReturn(Optional.of(5));

        freezeController.onTick();

        verify(puyo).setFreezeClock(Optional.of(4));
    }

    /**
     * Tests that a Puyo is unfrozen when its freeze timer reaches zero.
     * Once the freeze duration expires, the Puyo should return to its normal state.
     */
    @Test
    void testPuyoUnfreezes() {
        when(grid.getRows()).thenReturn(1);
        when(grid.getCols()).thenReturn(1);
        when(grid.getPuyo(0, 0)).thenReturn(puyo);
        when(puyo.getDeathClock()).thenReturn(Optional.empty());
        when(puyo.getFreezeClock()).thenReturn(Optional.of(1));

        freezeController.onTick();

        verify(puyo).setFreezeClock(Optional.empty());
    }
}

