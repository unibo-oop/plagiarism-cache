package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import boxhead.model.entities.Player;
import boxhead.model.entities.gun.GunUpgradeManager;
import boxhead.model.score.Score;
import boxhead.model.score.ScoreImpl;
import boxhead.view.GameView;

/**
 * Test for score.
 */
public class ScoreTest {

	@Test
	public void testScore() {
		final Player player = new Player();
		final GunUpgradeManager gunManager = new GunUpgradeManager(new GameView(), player);
		final Score score = new ScoreImpl(gunManager);
		
		assertTrue(Integer.valueOf(score.getKills()).equals(0));
		score.addKill();
		score.addKill();
		assertTrue(Integer.valueOf(score.getKills()).equals(2));
		assertTrue(Integer.valueOf(score.getStreak()).equals(2));
		//It wasn't necessary to test the GunUpgradeManager because it was easier by testing it with the GUI.
		score.setGameEnd();
		assertTrue(Integer.valueOf(score.getTimePlayed().compareTo("00:00:00")).equals(0));
	}
}
