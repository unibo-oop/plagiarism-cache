package it.dpg.gamecycleTests;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnManager;
import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnManagerImpl;
import it.dpg.maingame.controller.gamecycle.turnmanagement.GameState;
import it.dpg.maingame.controller.gamecycle.turnmanagement.GameStateImpl;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.model.character.CharacterImpl;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.grid.Grid;
import it.dpg.maingame.view.grid.GridView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class TurnManagerImplTest {

    private final GameState state = new GameStateImpl();
    private final Dice defaultDice = Dice.D6;
    private final List<Dice> rewardDice = List.of(Dice.D10, Dice.D8, Dice.D6);
    Grid gridMock = mock(Grid.class);
    GridView view = mock(GridView.class);
    private TurnManager manager;
    private Character c1;
    private Character c2;
    private Character c3;

    @BeforeEach
    void setup() {
        create();
        state.newTurn();
    }

    void create() {
        c1 = new CharacterImpl(1, "Franco", gridMock);
        c2 = new CharacterImpl(2, "Alberto", gridMock);
        c3 = new CharacterImpl(3, "CPU1", gridMock);
        PlayerController p1 = mock(PlayerController.class);
        PlayerController p2 = mock(PlayerController.class);
        PlayerController p3 = mock(PlayerController.class);
        doAnswer(invocation -> c1).when(p1).getCharacter();
        doAnswer(invocation -> c2).when(p2).getCharacter();
        doAnswer(invocation -> c3).when(p3).getCharacter();
        doAnswer(invocation -> {
            c1.setMinigameScore(10);
            return null;
        }).when(p1).playMinigame(any());
        doAnswer(invocation -> {
            c2.setMinigameScore(20);
            return null;
        }).when(p2).playMinigame(any());
        doAnswer(invocation -> {
            c3.setMinigameScore(30);
            return null;
        }).when(p3).playMinigame(any());
        manager = new TurnManagerImpl(defaultDice, rewardDice, 3, Set.of(p1, p2, p3), state);
    }

    @Test
    void startTest1() { //test that every player gets the same dice at the start
        for (PlayerController player : manager.getPlayers()) {
            assertEquals(defaultDice, player.getCharacter().getDice());
        }
    }

    void basicTestTurn(PlayerController first, PlayerController second, PlayerController third) {
        Iterator<PlayerController> it = manager.getPlayersIterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertTrue(it.hasNext());
        assertEquals(second, it.next());
        assertTrue(it.hasNext());
        assertEquals(third, it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void basicTestGame() {
        List<PlayerController> players = manager.getPlayers();
        Optional<PlayerController> temp = players.stream().filter(p -> p.getCharacter().getTurn() == 0).findAny();
        assertTrue(temp.isPresent());
        PlayerController first = temp.get();
        temp = players.stream().filter(p -> p.getCharacter().getTurn() == 1).findAny();
        assertTrue(temp.isPresent());
        PlayerController second = temp.get();
        temp = players.stream().filter(p -> p.getCharacter().getTurn() == 2).findAny();
        assertTrue(temp.isPresent());
        PlayerController third = temp.get();

        for (int i = 0; i < 2; i++) {
            basicTestTurn(first, second, third);
            assertTrue(manager.hasNextTurn());
            manager.nextTurn();
        }
        assertEquals(Dice.D10, c3.getDice());
        assertEquals(Dice.D8, c2.getDice());
        assertEquals(Dice.D6, c1.getDice());
        assertFalse(manager.hasNextTurn());
        assertThrows(IllegalStateException.class, () -> manager.nextTurn());
    }
}
