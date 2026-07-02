

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.entities.MultiDirectionsEnemyBullet;
import model.entities.SpecificEntityType;
import util.Pair;

/**
 *  Test that checks the correct creation of the Boss bullet.
 */
public class bossBulletTest {

		@Test
	public void BossBulletTest() {
		final MultiDirectionsEnemyBullet testBullet = new MultiDirectionsEnemyBullet(0, 0, SpecificEntityType.BOSS_BULLET);
        assertNotNull(testBullet);
        final Pair<Double, Double> position = testBullet.getPos();
        testBullet.setMuX(10);
        testBullet.setMuY(10);
        testBullet.updateEntityPosition();
        assertEquals(position, new Pair<>(testBullet.getX(), testBullet.getY()));
	}
}
