package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.GameImpl;
import model.ID;
import model.PlayerImpl;
import model.AbstractEnemy;
import model.BasicEnemy;
import model.BossEnemy;
import model.DirEnemy;
import model.ShotEnemyImpl;
import utility.Pair;

/**
 * The Class TestEnemy.
 */
public class TestEnemy {
	
	/** The Constant EXPECTED. */
	private static final int EXPECTED = 6;
	
	/**
	 * Test basic enemy.
	 */
	@Test
	public void testBasicEnemy() {
		final GameImpl game = new GameImpl();
		final AbstractEnemy basic = new BasicEnemy(game);
		final PlayerImpl player = new PlayerImpl(ID.PLAYER, game);
		basic.createThisEnemy();
		assertNotNull(basic);
		final Pair<Integer, Integer> position = basic.getPosition();
		assertNotSame(position, player.getPosition());
		assertNotSame(position, new Pair<Integer, Integer> (0, 0));
		basic.setDead();
		assertTrue(basic.isDead());
		basic.setAlive();
		assertFalse(basic.isDead());
	}
	
	/**
	 * Test boss enemy.
	 */
	@Test
	public void testBossEnemy() {
		final GameImpl game = new GameImpl();
		final AbstractEnemy boss = new BossEnemy(game);
		final PlayerImpl player = new PlayerImpl(ID.PLAYER, game);
		boss.createThisEnemy();
		assertNotNull(boss);
		final Pair<Integer, Integer> position = boss.getPosition();
		assertNotSame(position, player.getPosition());
		assertNotSame(position, new Pair<Integer, Integer> (0, 0));
		boss.update();
	
			
		
		boss.setDead();
		assertTrue(boss.isDead());
		boss.setAlive();
		assertFalse(boss.isDead());
	

	}
	
	/**
	 * Test shoot.
	 */
	@Test
	public void testShoot() {
		final ShotEnemyImpl shotenemy = new ShotEnemyImpl(6, 6, DirEnemy.DOWN);
		assertNotNull(shotenemy);
		assertEquals(new Pair<Integer, Integer> (0, 3), shotenemy.getSpeed());
		assertEquals(new Pair<Integer, Integer> (EXPECTED, EXPECTED), shotenemy.getPosition());
		shotenemy.update();
		shotenemy.update();

		 assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED*2), shotenemy.getPosition());
	        shotenemy.setDir(DirEnemy.D_R);
	        shotenemy.update();
	        shotenemy.update();
	        assertEquals(new Pair<Integer, Integer>(EXPECTED*2, EXPECTED*3), shotenemy.getPosition());
	        shotenemy.setDir(DirEnemy.D_L);
	        shotenemy.update();
	        shotenemy.update();
	        
	       
	        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED*4), shotenemy.getPosition());
	        shotenemy.setDead();
	        assertTrue(shotenemy.isDead());
	        shotenemy.setAlive();
	        assertFalse(shotenemy.isDead());
	        shotenemy.update();
	        shotenemy.update();
	        shotenemy.update();
	        shotenemy.update();

	        assertTrue(shotenemy.isDead());
	}
}
