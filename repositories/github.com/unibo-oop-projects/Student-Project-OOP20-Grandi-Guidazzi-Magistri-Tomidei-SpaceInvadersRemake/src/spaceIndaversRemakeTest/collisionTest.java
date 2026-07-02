

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controller.GameController;
import controller.GameControllerImpl;
import model.Model;
import model.ModelImpl;
import model.entities.Alien;
import model.entities.AlienGroup;
import model.entities.Player1;
import model.entities.PlayerBullet;
import model.entities.SpecificEntityType;
import model.entitiesutil.Player;
import model.physics.EntityCollision;
import model.physics.EntityCollisionImpl;

/**
 * Test that checks for collisions between entities.
 */
public class collisionTest {

	
	final GameController controller = new GameControllerImpl();
	final Model game = new ModelImpl(controller);
	final Player player = new Player1(SpecificEntityType.PLAYER_1, 5.0 , 5.0, game);
	final PlayerBullet testBullet = new PlayerBullet(0, 0);
	final Alien alien = new Alien(10 , 10 , new AlienGroup(game), game, SpecificEntityType.ALIEN_1);
	final EntityCollision collision = new EntityCollisionImpl(game.getWorld()); 
	
	@Test
	public void CollisionTest(){
		collision.collision(player, alien);
		assertFalse(player.isAlive());
		assertTrue(alien.isAlive());
		
		collision.collision(testBullet, alien);
		assertFalse(testBullet.isAlive());
		assertFalse(alien.isAlive());
	}
}
