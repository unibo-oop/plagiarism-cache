

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import model.entities.MonoDirectionEnemyBullet;
import model.entities.SpecificEntityType;
import util.Pair;

/**
 * Test that checks the correct creation of the enemy bullet.
 */
public class enemyBulletTest {

	@Test
	public void EnemyBulletTest() {
		final MonoDirectionEnemyBullet testBullet = new MonoDirectionEnemyBullet(0, 0, SpecificEntityType.ALIEN_BULLET);
	    assertNotNull(testBullet);
	    final Pair<Double, Double> position = testBullet.getPos();
	    testBullet.setMuX(10);
	    testBullet.setMuY(10);
	    testBullet.updateEntityPosition();
	    assertEquals(position, new Pair<>(testBullet.getX(), testBullet.getY()));
	}
	
	
}
