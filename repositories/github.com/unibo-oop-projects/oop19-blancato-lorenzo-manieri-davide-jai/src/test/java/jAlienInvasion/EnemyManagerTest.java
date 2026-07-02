package jAlienInvasion;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import controller.EnemyManager;
import model.Enemy;
import model.EnemyCruiser;
import model.EnemyInterceptor;

public class EnemyManagerTest {
	
	Enemy enemyInterceptor = new EnemyInterceptor(10, 10, 10, 10);
	Enemy enemyCruiser = new EnemyCruiser(10, 10, 10, 10);
	
	@Test
	public void enemyManagerTest() {
		EnemyManager.getEnemyList();
		
		EnemyManager.addEnemy(enemyCruiser);
		EnemyManager.addEnemy(enemyInterceptor);
		boolean isNotEmpty =!(EnemyManager.getEnemyList().isEmpty());
		assertTrue(isNotEmpty);

		EnemyManager.removeEnemy(enemyCruiser);
		EnemyManager.removeEnemy(enemyInterceptor);
		boolean isEmpty = EnemyManager.getEnemyList().isEmpty();
		assertTrue(isEmpty);
	}
}
