package it.dpg.gamecycleTests;

import it.dpg.maingame.controller.gamecycle.playercontroller.CpuPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.controller.gamecycle.turnmanagement.GameState;
import it.dpg.maingame.controller.gamecycle.turnmanagement.GameStateImpl;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.view.grid.GridView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import it.dpg.maingame.model.character.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class CpuPlayerControllerTest {

    private final GameState state = new GameStateImpl();
    private PlayerController pc;

    @BeforeEach
    public void setup() {
        GridView view = mock(GridView.class);
        Character character = mock(Character.class);
        pc = new CpuPlayerController(state, view, character, Difficulty.NORMAL);
        doAnswer(inv -> Set.<Pair<Integer, Integer>>of(
                new ImmutablePair<>(4, 8),
                new ImmutablePair<>(3, 9),
                new ImmutablePair<>(4, 9))).when(character).getAdjacentPositions();
    }


    @Test
    public void testDiceThrow() {
        state.newTurn();
        pc.throwDice();
        assertTrue(state.wasDiceThrown());
    }

    @Test
    public void testDirectionChoice() {
        state.newTurn();
        var choices = Set.<Pair<Integer, Integer>>of(
                new ImmutablePair<>(4, 8),
                new ImmutablePair<>(3, 9),
                new ImmutablePair<>(4, 9));
        pc.chooseDirection();
        assertTrue(state.getLastDirectionChoice().isPresent());
        Pair<Integer, Integer> temp = state.getLastDirectionChoice().get();
        assertTrue(choices.contains(temp));
        assertFalse(state.isChoosing());
    }
}
