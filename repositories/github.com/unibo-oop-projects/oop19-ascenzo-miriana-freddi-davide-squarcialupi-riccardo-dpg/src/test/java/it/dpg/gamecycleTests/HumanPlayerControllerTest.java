package it.dpg.gamecycleTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import it.dpg.maingame.controller.gamecycle.turnmanagement.GameState;
import it.dpg.maingame.controller.gamecycle.turnmanagement.GameStateImpl;
import it.dpg.maingame.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.view.grid.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;


public class HumanPlayerControllerTest {

    GridView view = mock(GridView.class);
    static Character character = mock(Character.class);

    private final GameState state = new GameStateImpl();
    private final PlayerController pc = new HumanPlayerController(state, view, character);

    @BeforeAll
    static void setup() {
        doAnswer(inv -> Set.<Pair<Integer, Integer>>of(
                new ImmutablePair<>(4, 8),
                new ImmutablePair<>(3, 9),
                new ImmutablePair<>(4, 9))).when(character).getAdjacentPositions();
    }

    @Test
    public void testDiceThrow() {
        state.newTurn();
        long waitingTime = 4000;

        Thread gameCycleMock = new Thread(() -> {
            long start = System.currentTimeMillis();
            pc.throwDice();
            long stop = System.currentTimeMillis();
            assertTrue((stop - start) >= waitingTime);
        });

        gameCycleMock.start();
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            fail();
        }

        state.setDiceThrown(true);
        synchronized (state) {
            state.notify();
        }

        try {
            gameCycleMock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDirectionChoice() {
        state.newTurn();
        long waitingTime = 4000;

        Thread gameCycleMock = new Thread(() -> {
            long start = System.currentTimeMillis();
            pc.chooseDirection();
            long stop = System.currentTimeMillis();
            assertTrue((stop - start) >= waitingTime);
        });

        gameCycleMock.start();
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            fail();
        }

        state.setChoice(false);
        synchronized (state) {
            state.notify();
        }

        try {
            gameCycleMock.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
