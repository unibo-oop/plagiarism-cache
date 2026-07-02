package jAlienInvasion;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controller.ScreenManager;
import model.Player;
import model.PlayerImpl;

public class PlayerTest {
	
	@Test
	public void testPlayer() {
		assertNotNull(PlayerImpl.getInstance());
		PlayerImpl.resetPlayer();
		Player player = PlayerImpl.getInstance();
		assertNotNull(player);
		player.move(10000, 100000);
		assertTrue(player.getPositionX()+ player.getWidth() == ScreenManager.widthScreen);
		assertTrue(player.getPositionY()+ player.getHeight() == ScreenManager.heightScreen);
		player.move(-10000, -10000);
		assertTrue(player.getPositionX() == 0);
		assertTrue(player.getPositionY() == 0);
	}
}
