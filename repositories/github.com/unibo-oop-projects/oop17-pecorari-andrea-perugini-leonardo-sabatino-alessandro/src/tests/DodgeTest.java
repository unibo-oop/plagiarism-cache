package tests;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Random;

import dodge.*;
import enemies.Enemy;
import enemies.Henchman;
import enemies.Meteorite;
import enemies.Nitro;
import enemies.RockBall;
import enemies.TNT;
import powerups.AkuAku;
import powerups.Life;
import powerups.PowerUp;
import powerups.WumpaFruit;
import characters.*;

public class DodgeTest {

	@Test
	public void testInit() {
		Dodge dodge = new DodgeImpl(new PositionImpl(50, 50));
		assertEquals(5, dodge.getLife());
	}

	@Test
	public void testCollisionWithPowerUp() {

		int rndX = rndNumber();
		int rndY = rndNumber();
		PowerUp powerup = new WumpaFruit(new PositionImpl(rndX, rndY));
		DodgeImpl dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		dodge.setX(100);
		dodge.setY(100);
		powerup.setX(0);
		powerup.setY(0);
		assertFalse(powerup.collidesWith(dodge));

		// Testing WumpaFruit
		rndX = rndNumber();
		rndY = rndNumber();
		powerup = new WumpaFruit(new PositionImpl(rndX, rndY));
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		assertTrue(powerup.collidesWith(dodge));
		powerup.collides(dodge);
		assertEquals(1, dodge.getWumpas());
		powerup.collides(dodge);
		powerup.collides(dodge);
		powerup.collides(dodge);
		powerup.collides(dodge);
		powerup.collides(dodge);
		powerup.collides(dodge);
		powerup.collides(dodge);
		powerup.collides(dodge);
		assertEquals(9, dodge.getWumpas());
		powerup.collides(dodge);
		assertEquals(0, dodge.getWumpas());
		assertEquals(6, dodge.getLife());

		// Testing Life
		rndX = rndNumber();
		rndY = rndNumber();
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		powerup = new Life(new PositionImpl(rndX, rndY));
		assertTrue(powerup.collidesWith(dodge));
		powerup.collides(dodge);
		assertEquals(6, dodge.getLife());

		// Testing AkuAku
		rndX = rndNumber();
		rndY = rndNumber();
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		powerup = new AkuAku(new PositionImpl(rndX, rndY));
		assertTrue(powerup.collidesWith(dodge));
		powerup.collides(dodge);
		assertTrue(dodge.getImmunity());

	}

	@Test
	public void testCollisionWithEnemy() {

		// Testing Henchman
		int rndX = rndNumber();
		int rndY = rndNumber();
		Enemy enemy = new Henchman(new PositionImpl(rndX, rndY));
		DodgeImpl dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		assertTrue(enemy.collidesWith(dodge));
		enemy.collides(dodge);
		assertEquals(4, dodge.getLife());

		// Testing RockBall
		rndX = rndNumber();
		rndY = rndNumber();
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		enemy = new RockBall(new PositionImpl(rndX, rndY));
		assertTrue(enemy.collidesWith(dodge));
		enemy.collides(dodge);
		assertEquals(3, dodge.getLife());

		// Testing TNT
		rndX = rndNumber();
		rndY = rndNumber();
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		enemy = new TNT(new PositionImpl(rndX, rndY));
		assertTrue(enemy.collidesWith(dodge));
		enemy.collides(dodge);
		assertEquals(1, dodge.getLife());
		enemy.collides(dodge);
		assertFalse(dodge.getLife() > 0);

		// Testing Meteorite
		rndX = rndNumber();
		rndY = rndNumber();
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		enemy = new Meteorite(new PositionImpl(rndX, rndY));
		assertTrue(enemy.collidesWith(dodge));
		enemy.collides(dodge);
		assertEquals(2, dodge.getLife());

		// Testing Nitro
		rndX = rndNumber();
		rndY = rndNumber();
		dodge = new DodgeImpl(new PositionImpl(rndX, rndY));
		enemy = new Nitro(new PositionImpl(rndX, rndY));
		assertTrue(enemy.collidesWith(dodge));
		enemy.collides(dodge);
		assertEquals(0, dodge.getLife());
	}

	private int rndNumber() {
		Random random = new Random();
		return random.nextInt();
	}

}
