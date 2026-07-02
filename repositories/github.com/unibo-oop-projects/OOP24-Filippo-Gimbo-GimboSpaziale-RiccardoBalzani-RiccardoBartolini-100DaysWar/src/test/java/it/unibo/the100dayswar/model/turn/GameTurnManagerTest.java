package it.unibo.the100dayswar.model.turn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

class GameTurnManagerTest {

    private GameTurnManager gameTurnManager;
    private Player playerHuman;
    private Player playerBot;

    @BeforeEach
    void setUp() {
        playerBot = new HumanPlayerImpl("playerName1", new CellImpl(new PositionImpl(4, 4), true, true));
        playerHuman = new HumanPlayerImpl("playerName1", new CellImpl(new PositionImpl(0, 0), true, true));
        final List<Player> listPlayers = new ArrayList<>();
        listPlayers.add(playerHuman);
        listPlayers.add(playerBot);
        gameTurnManager = new GameTurnManagerImpl(listPlayers);
    }

    @Test
    void testSwitchTurn() {
        gameTurnManager.switchTurn();
        assertEquals(2, gameTurnManager.getCurrentTurn());
        assertEquals(playerBot, gameTurnManager.getCurrentPlayer());
        assertEquals(1, gameTurnManager.getCurrentPlayerIndex());
        gameTurnManager.switchTurn();
        assertEquals(3, gameTurnManager.getCurrentTurn());
        assertEquals(playerHuman, gameTurnManager.getCurrentPlayer());
        assertEquals(0, gameTurnManager.getCurrentPlayerIndex());
    }

    @Test
    void testOnDayPassed() {
        assertEquals(1, gameTurnManager.getDay());
        gameTurnManager.onDayPassed();
        assertEquals(2, gameTurnManager.getDay());
        gameTurnManager.onDayPassed();
        gameTurnManager.onDayPassed();
        gameTurnManager.onDayPassed();
        assertEquals(playerBot, gameTurnManager.getCurrentPlayer());
        gameTurnManager.onDayPassed();
        assertEquals(2, gameTurnManager.getCurrentTurn());
    }
}
