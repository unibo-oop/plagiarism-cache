package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.spaccabolle.Game;
import dev.spaccabolle.input.KeyManager;
import dev.spaccabolle.input.MouseManager;

class GameTest {

	int width = 840;
	int height = 920;
	Game game = new Game("SPACCABOLLE", width, height);
	
	@Test
	public void testRun() {
		game.run();
		Thread thread = new Thread(game);
		thread.start();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testStop() {
		Thread thread = new Thread(game);
		thread.stop();
	}
	
	@Test
	public void testgetKeyManager(){
		 @SuppressWarnings("unused")
		KeyManager keyManagerGame = game.getKeyManager();
		// assertEquals(game.keyManager, keyManagerGame);
	}
	
	@Test
	public void testgetMouseManager(){
		 @SuppressWarnings("unused")
		MouseManager mouseManagerGame = game.getMouseManager();
		 //assertEquals(game.mouseManager, mouseManagerGame);
	}
	
	@Test
	public void testgetWidth() {
		int gameWidth = game.getWidth();
		assertEquals(width, gameWidth);
	}

	@Test
	public void testgetHeight() {
		int gameHeight = game.getHeight();
		assertEquals(height, gameHeight);
	}

}
