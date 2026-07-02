

import controller.GameController;
import controller.GameControllerImpl;
import model.Model;
import model.ModelImpl;
import model.entities.Alien;
import model.entities.AlienGroup;
import model.entities.Player1;
import model.entities.SpecificEntityType;
import model.entitiesutil.Player;
import util.Pair;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Test that checks the correct boss creation and life.
 */
public class enemyTest {

	@Test
	public void EnemyTest() {
		
		final GameController controller = new GameControllerImpl();
		final Model game = new ModelImpl(controller);
		final Alien alien = new Alien(0 , 0 , new AlienGroup(game), game, SpecificEntityType.ALIEN_1);
		final Player player = new Player1(SpecificEntityType.PLAYER_1, 10.0 , 10.0, game);
		assertNotNull(alien);
		final Pair<Double, Double> position = alien.getPos();
		assertNotSame(position, player.getPos());
		assertNotSame(position, new Pair<Integer, Integer> (10, 10));
		assertTrue(alien.isAlive());
		alien.incHits();
		assertFalse(alien.isAlive());
	}
}
