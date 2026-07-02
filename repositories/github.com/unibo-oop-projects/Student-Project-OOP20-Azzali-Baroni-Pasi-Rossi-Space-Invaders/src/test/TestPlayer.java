package test;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.GameImpl;
import model.ID;
import model.PlayerImpl;
import utility.Pair;

/**
 * The Class TestPlayer.
 */
public class TestPlayer {
	
	/**
	 * Test player.
	 */
	@Test
    public void testPlayer() {
        final GameImpl gameTest = new GameImpl();
        final PlayerImpl testPlayer = new PlayerImpl(ID.PLAYER, gameTest);
        assertNotNull(testPlayer);
        final Pair<Integer, Integer> position = testPlayer.getPosition();
        testPlayer.setSpeed(5, 5);
        testPlayer.update();
        assertTrue(testPlayer.getPosition().equals(position));
    }
}
