package it.unibo.the100dayswar.model.savedata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;
import java.util.stream.Collectors;

/**
 * Test suite for GameDataImpl class.
 */
class GameDataTest extends AbstractGameTest {

    /**
     * Test to verify the constructor initializes the fields correctly.
     */
    @Test
    void testConstructorInitializesFields() {
        assertNotNull(getMockGameData().getHumanData());
        assertNotNull(getMockGameData().getBotData());
        assertNotNull(getMockGameData().getMapManager());
        assertNotNull(getMockGameData().getGameTurnManager());
    }

    /**
     * Test to verify getHumanData returns a deep copy.
     */
    @Test
    void testGetPlayerDataReturnsDeepCopy() {
        final Player copy = getMockGameData().getHumanData();
        assertNotSame(getMockHumanPlayer(), copy);

        assertEquals(getMockHumanPlayer().getBankAccount(), copy.getBankAccount());
        assertEquals(getMockHumanPlayer().getSoldiers(), copy.getSoldiers());
        assertEquals(getMockHumanPlayer().getSpawnPoint(), copy.getSpawnPoint());
        assertEquals(getMockHumanPlayer().getTowers(), copy.getTowers());
        assertEquals(getMockHumanPlayer().getUnits(), copy.getUnits());
        assertEquals(getMockHumanPlayer().getUsername(), copy.getUsername());
    }

    /**
     * Test to verify getBotData returns a deep copy.
     */
    @Test
    void testGetBotDataReturnsDeepCopy() {
        final Player copy = getMockGameData().getBotData();

        assertNotSame(getMockBotPlayer(), copy);

        assertEquals(getMockBotPlayer().getBankAccount(), copy.getBankAccount());
        assertEquals(getMockBotPlayer().getSoldiers(), copy.getSoldiers());
        assertEquals(getMockBotPlayer().getSpawnPoint(), copy.getSpawnPoint());
        assertEquals(getMockBotPlayer().getTowers(), copy.getTowers());
        assertEquals(getMockBotPlayer().getUnits(), copy.getUnits());
        assertEquals(getMockBotPlayer().getUsername(), copy.getUsername());
    }

    /**
     * Test to verify getMapManager returns a deep copy.
     */
    @Test
    void testGetGameMapReturnsDeepCopy() {
        final MapManager copy = getMockGameData().getMapManager();

        assertNotSame(getMockGameMapManager(), copy);

        assertEquals(
            getMockGameMapManager().getMapAsAStream().collect(Collectors.toList()),
            copy.getMapAsAStream().collect(Collectors.toList())
        );

        assertEquals(getMockGameMapManager().getPlayersCells(), copy.getPlayersCells());
    }

    /**
     * Test to verify getGameTurnManager returns the same instance.
     */
    @Test
    void testGetGameTurnManagerReturnsSameInstance() {
        assertSame(getMockGameTurnManager(), getMockGameData().getGameTurnManager());
    }
}
