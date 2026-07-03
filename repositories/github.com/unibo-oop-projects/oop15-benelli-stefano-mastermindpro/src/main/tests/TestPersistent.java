package main.tests;

import java.io.File;
import java.util.List;
import controller.data.Persistent;
import controller.data.PersistentException;
import controller.data.PersistentFileSystem;
import model.games.Game;
import model.games.GameImpl;
import model.games.Round;
import model.players.Decoder;

import org.junit.Assert;
import org.junit.Test;

public class TestPersistent {
	
	@Test
	/**
	 * This Test class tests the persistent Engine
	 */
	public void test() {
		
		Game game = new GameImpl();
		game.loadDefaults();
		game.initializeGame();
		
		Persistent o = new PersistentFileSystem();
		
		File file = new File(PersistentFileSystem.PENDING_FILEPATH);
		
		try {
			
			//persistent
			o.savePendingGame(game);
			Assert.assertTrue(file.exists());
			
			o.clearPendingGame();
			Assert.assertFalse(file.exists());
		
			o.savePendingGame(game);
			
			Game gameLoaded = o.loadPendingGame().get();
			
			CompareGames(game, gameLoaded);
		}
		catch(PersistentException e) {
			Assert.fail("Persistent Exception Thrown");
		}
		
		try {
			//archive
			o.archiveGame(game);
			o.archiveGame(game);
			o.archiveGame(game);
			o.archiveGame(game);
			
			List<Game> gameArchived = o.loadArchivedGames();

			//controllo che gli ultimi 4 game dell'archivio siano
			CompareGames(gameArchived.get(gameArchived.size()-1), game);
			CompareGames(gameArchived.get(gameArchived.size()-2), game);
			CompareGames(gameArchived.get(gameArchived.size()-3), game);
			CompareGames(gameArchived.get(gameArchived.size()-4), game);
			
		} catch (PersistentException e) {
			Assert.fail("Persistent Exception Thrown");
		}
	}
	
	private void CompareGames(Game g1, Game g2) {
		
		Assert.assertEquals(g1.getGameCompleted() , g2.getGameCompleted());
		Assert.assertEquals(g1.getDecoders().size(), g2.getDecoders().size());

		for(Decoder d1 : g1.getDecoders()) {
			
			for(Decoder d2 : g2.getDecoders()) {
				
				Assert.assertEquals(d1.getRounds().length, d2.getRounds().length);
				Assert.assertEquals(d1.getRoundsSubmitted(), d2.getRoundsSubmitted());
				
				for(Round r1: d1.getRounds()) {
					for(Round r2: d2.getRounds()) {
						Assert.assertArrayEquals(r1.getChoices(), r2.getChoices());
						Assert.assertArrayEquals(r1.getHints(), r2.getHints());
					}
				}
			}
		}
	}
}
