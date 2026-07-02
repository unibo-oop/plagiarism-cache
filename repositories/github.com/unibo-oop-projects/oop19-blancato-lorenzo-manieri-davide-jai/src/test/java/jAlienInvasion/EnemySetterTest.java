package jAlienInvasion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import model.Enemy;
import model.EnemyInterceptor;


public class EnemySetterTest {

	Enemy enemyInterceptor = new EnemyInterceptor(10, 10, 10, 10);
	
	public double health = 100;
	public double lessHealth = 80;
	public double moreHealth = 120;
	public double damage = 35;
	
	
	
	@Test
	public void healthTest() {
		enemyInterceptor.setHealth(health);
		assertEquals(health,  enemyInterceptor.getHealth(), 0);
		
	}
	
	@Test
	public void lessHealthTest() {
		enemyInterceptor.setHealth(lessHealth);
		assertTrue(health > enemyInterceptor.getHealth());
		
	}
	
	@Test
	public void maxHealthTest() {
		enemyInterceptor.setHealth(moreHealth);
		assertTrue(health < enemyInterceptor.getMaxHealth());
		
	}
	
	@Test
	public void damageTest() {
		enemyInterceptor.setDamage(damage);
		assertEquals(damage, enemyInterceptor.getDamage(), 0);
	}

	@Test
	public void explosionTest() {
		enemyInterceptor.hit(moreHealth);
		assertTrue(enemyInterceptor.isExploding());
	}
}
