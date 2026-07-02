

import controller.GameController;
import controller.GameControllerImpl;
import model.Model;
import model.ModelImpl;
import model.entities.*;
import model.entities.Player1;
import model.entities.SpecificEntityType;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.Player;
import util.Pair;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *  Test that checks the correct boss creation and life.
 */
public class bossTest {

	@Test
	public void BossTest() {
		
		final GameController controller = new GameControllerImpl();
		final Model game = new ModelImpl(controller);
		final Boss1 boss1 = new Boss1(10.0, 10.0, game);
		final Boss2 boss2 = new Boss2(10.0, 10.0, game);
		final Boss3 boss3 = new Boss3(10.0, 10.0, game);
		final Player player = new Player1(SpecificEntityType.PLAYER_1, 10.0 , 10.0, game);
	
		assertNotNull(boss1);
		assertNotNull(boss2);
		assertNotNull(boss3);
		final Pair<Double, Double> position1 = boss1.getPos();
		final Pair<Double, Double> position2 = boss2.getPos();
		final Pair<Double, Double> position3 = boss2.getPos();
		assertNotSame(position1, player.getPos());
		assertNotSame(position2, player.getPos());
		assertNotSame(position3, player.getPos());
		assertNotSame(position1, new Pair<Integer, Integer> (0, 0));
		assertNotSame(position2, new Pair<Integer, Integer> (0, 0));
		assertNotSame(position3, new Pair<Integer, Integer> (0, 0));
		boss1.updateEntityPosition();
		boss2.updateEntityPosition();
		boss3.updateEntityPosition();
	
		assertTrue(boss1.isAlive());
		while(boss1.getHits()<= EntityConstants.Boss1.MAX_HITS) {
			boss1.incHits();
		}
		assertFalse(boss1.isAlive());
		
		assertTrue(boss2.isAlive());
		while(boss2.getHits()<= EntityConstants.Boss2.MAX_HITS) {
			boss2.incHits();
		}
		assertFalse(boss2.isAlive());
		
		assertTrue(boss3.isAlive());
		while(boss3.getHits()<= EntityConstants.Boss3.MAX_HITS) {
			boss3.incHits();
		}
		assertFalse(boss3.isAlive());
	}
}
