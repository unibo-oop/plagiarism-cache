package main.tests;

import org.junit.Assert;
import org.junit.Test;

import model.exceptions.InvalidConfigException;
import model.games.Game;
import model.games.GameImpl;
import model.games.Round;
import model.players.Decoder;

public class TestGame {
	
	@Test
	/**
	 * This test class tests the Game Initialization
	 */
	public void testInitialization() {

		Game game = new GameImpl();
		game.loadDefaults();
		
		game.getGameConfig().setNoOfChoices(4);
		game.getGameConfig().setCodeLength(5);
		game.getGameConfig().setNoOfRounds(8);
		
		try {
			game.validateConfig();
			Assert.fail("InvalidConfigException expected but not thrown");
		} catch (InvalidConfigException e) {
			
		}

		game.getGameConfig().setNoOfChoices(6);
		game.getGameConfig().setCodeLength(4);
		
		game.initializeGame();
		
		Assert.assertEquals(game.getGameConfig().getCodeLength(),game.getEncoder().getSecretCode().length);
		for(Decoder d : game.getDecoders()) {
			Assert.assertEquals(d.getRounds().length, game.getGameConfig().getNoOfRounds());
			for(Round r : d.getRounds()) {
				Assert.assertEquals(r.getChoices().length, game.getGameConfig().getCodeLength());
				Assert.assertEquals(r.getHints().length, game.getGameConfig().getCodeLength());
			}
		}
	}
}
