package it.unibo.oop.test.game.bullet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.actor.player.PlayerImpl;
import it.unibo.oop.crossline.game.bullet.BulletImpl;

/**
 * Test class for BulletImpl.
 */
public class BulletImplTest {

	/**
	 * Zero value.
	 */
	private static final int ZERO = 0;
	/**
	 * One value.
	 */
	private static final int ONE = 1;
	/**
	 * First bullet.
	 */
	private BulletImpl bullet1;
	/**
	 * Second bullet.
	 */
	private BulletImpl bullet2;
	/**
	 * Same as first bullet.
	 */
	private BulletImpl sameAsBullet1;

	/**
	 * Unimplemented bullet.
	 */
	private static final BulletImpl UNIMPLEMENTED_BULLET = null;
	private static final Vector2 NULL_VECTOR = new Vector2(ZERO, ZERO);
	private static final World WORLD = new World(NULL_VECTOR, true);
	private static final Actor OWNER = new PlayerImpl(WORLD, NULL_VECTOR);

	/**
	 * SetUp method for instance bullets.
	 * 
	 * @throws java.lang.Exception generic exception
	 */
	@Before
	public void setUp() throws Exception {
		bullet1 = new BulletImpl(ZERO, NULL_VECTOR, OWNER, NULL_VECTOR, ZERO, ZERO);
		bullet2 = new BulletImpl(ONE, NULL_VECTOR, OWNER, NULL_VECTOR, ONE, ONE);
		sameAsBullet1 = bullet1;
	}

	/**
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertNotEquals(bullet1.hashCode(), ZERO);
		assertNotEquals(bullet2.hashCode(), ZERO);
		assertNotEquals(bullet1.hashCode(), bullet2.hashCode());
		assertEquals("bullet1'hashcode equals to sameAsBullet1's hashcode", bullet1.hashCode(),
				sameAsBullet1.hashCode());
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#BulletImpl(float, com.badlogic.gdx.math.Vector2, it.unibo.oop.crossline.game.actor.Actor, com.badlogic.gdx.math.Vector2, float, float)}.
	 */
	@Test
	public void testBulletImpl() {
		assertNotNull("bullet1 is not null", bullet1);
		assertNotNull("bullet2 is not null", bullet2);
		assertNotNull("sameAsBullet1 is not null", sameAsBullet1);
		assertNull("UNIMPLEMENTED_BULLET is null", UNIMPLEMENTED_BULLET);
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#update()}.
	 */
	@Test
	public void testUpdate() {
		Vector2 speed = bullet1.getBody().getLinearVelocity();
		bullet1.update();
		assertEquals("bullet1's body linear velocity changed", bullet1.getBody().getLinearVelocity(), speed);
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#getBody()}.
	 */
	@Test
	public void testGetBody() {
		assertNotNull("bullet1's body is not null", bullet1.getBody());
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#getDamage()}.
	 */
	@Test
	public void testGetDamage() {
		assertNotNull("bullet1's damage is not null", bullet1.getDamage());
		assertEquals("bullet1's damage is ZERO", Double.valueOf(bullet1.getDamage()), Double.valueOf(ZERO));
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#getDirection()}.
	 */
	@Test
	public void testGetDirection() {
		assertNotNull("bullet1's direction is not null", bullet1.getDirection());
		assertEquals("bullet1's direction is NULL_VECTOR", bullet1.getDirection(), NULL_VECTOR);
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#isQueuedForDestruction()}.
	 */
	@Test
	public void testIsQueuedForDestruction() {
		assertFalse("bullet1 is queued for destruction", bullet1.isQueuedForDestruction());
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#queueForDestruction()}.
	 */
	@Test
	public void testQueueForDestruction() {
		bullet1.queueForDestruction();
		assertTrue("bullet1 is queued for destruction", bullet1.isQueuedForDestruction());
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		assertNotNull("bullet1 has owner", bullet1.getOwner());
		assertEquals("bullet1's owner is OWNER", bullet1.getOwner(), OWNER);
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#getSpeed()}.
	 */
	@Test
	public void testGetSpeed() {
		assertNotNull("bullet1 has speed", bullet1.getSpeed());
		assertEquals("bullet1's speed is ZERO", Double.valueOf(bullet1.getSpeed()), Double.valueOf(ZERO));
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#toString()}.
	 */
	@Test
	public void testToString() {
		assertNotNull("bullet1's string is not null", bullet1.toString());
	}

	/**
	 * Test method for
	 * {@link it.unibo.oop.crossline.game.bullet.BulletImpl#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		assertNotEquals(bullet1, bullet2);
		assertEquals("bullet1 equals to sameAsBullet1", bullet1, sameAsBullet1);
	}

}
