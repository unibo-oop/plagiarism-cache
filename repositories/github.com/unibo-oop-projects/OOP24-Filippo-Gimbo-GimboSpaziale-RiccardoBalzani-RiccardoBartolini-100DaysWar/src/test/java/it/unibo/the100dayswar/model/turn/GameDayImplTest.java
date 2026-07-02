package it.unibo.the100dayswar.model.turn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.turn.api.GameDay;
import it.unibo.the100dayswar.model.turn.impl.GameDayImpl;

class GameDayImplTest {

    private GameDay gameDay;
    private Player player;

    @BeforeEach
    void setUp() {
        gameDay = new GameDayImpl();
        player = new HumanPlayerImpl("player1", new CellImpl(new PositionImpl(4, 4), true, true));
        gameDay.attach(player);
    }

    @Test
    void testIncreaseDayAndNotifyObservers() {
        assertEquals(1, gameDay.getCurrentDay());
        final int initialBalance = player.getBankAccount().getBalance();
        gameDay.increaseDay();
        assertTrue(initialBalance < player.getBankAccount().getBalance());
        assertEquals(2, gameDay.getCurrentDay());
    }
}
