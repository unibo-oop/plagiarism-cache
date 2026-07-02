package it.dpg.gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;

import it.dpg.maingame.controller.gamecycle.turnmanagement.GameStateImpl;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.dpg.maingame.controller.gamecycle.turnmanagement.GameState;

public class GameStateTest {

    private GameState state;

    @BeforeEach
    void Setup() {
        state = new GameStateImpl();
    }


    @Test
    void testNewTurn() {
        state.newTurn();

        assertFalse(state.wasDiceThrown());
        assertFalse(state.isChoosing());
        assertFalse(state.isPaused());
        assertTrue(state.getLastDirectionChoice().isEmpty());
    }

    @Test
    void testExeption() {
        assertThrows(IllegalStateException.class, () -> state.isChoosing());
        assertThrows(IllegalStateException.class, () -> state.setChoice(true));
        assertThrows(IllegalStateException.class, () -> state.setDiceThrown(true));
        assertThrows(IllegalStateException.class, () -> state.wasDiceThrown());
        assertThrows(IllegalStateException.class, () -> state.setLastDirectionChoice(new ImmutablePair<>(4, 8)));
        assertThrows(IllegalStateException.class, () -> state.getLastDirectionChoice());
    }

    @Test
    void testDiceThrown() {
        state.newTurn();
        assertFalse(state.wasDiceThrown());
        state.setDiceThrown(true);
        assertTrue(state.wasDiceThrown());
        state.newTurn();
        assertFalse(state.wasDiceThrown());
    }

    @Test
    void testChoice() {
        state.newTurn();
        assertFalse(state.isChoosing());
        state.setChoice(true);
        assertTrue(state.isChoosing());
        state.setChoice(false);
        assertFalse(state.isChoosing());
    }

    @Test
    void testDirectionChoice() {
        state.newTurn();
        assertTrue(state.getLastDirectionChoice().isEmpty());
        state.setLastDirectionChoice(new ImmutablePair<>(3, 10));
        assertTrue(state.getLastDirectionChoice().isPresent());
        assertEquals(new ImmutablePair<>(3, 10), state.getLastDirectionChoice().get());
        state.newTurn();
        assertTrue(state.getLastDirectionChoice().isEmpty());
    }

    @Test
    void testPause() {
        state.newTurn();
        assertFalse(state.isPaused());
        state.setTurnPause(true);
        assertTrue(state.isPaused());
        state.newTurn();
        assertFalse(state.isPaused());
    }
}
