package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import model.BulletImpl;
import model.ID;
import model.MeteorImpl;
import utility.Pair;

/**
 * The Class MeteorTest.
 */
public class MeteorTest {

	/** The Constant LENGTH. */
	private static final int LENGTH = 50;
	
	/** The Constant XEXPECTED. */
	private static final int XEXPECTED = 0;
	
	/** The Constant YEXPECTED. */
	private static final int YEXPECTED = 3;
	
	/**
	 * Test meteor.
	 */
	@Test
	public void testMeteor() {
		final MeteorImpl meteorimpl = new MeteorImpl(new Pair<>(0,0), 1, 1, LENGTH, ID.METEOR);
		meteorimpl.update();
		meteorimpl.setSpeed(1, 1);
		meteorimpl.update();
		meteorimpl.setSpeed(1, 1);
		meteorimpl.update();
		assertEquals(meteorimpl.getPosition().getX().intValue(), XEXPECTED);
		assertEquals(meteorimpl.getPosition().getY().intValue(), YEXPECTED);	
	}
	
	/**
	 * Test collide meteor with bullet.
	 */
	@Test
	public void testCollideMeteorWithBullet() {
		final MeteorImpl meteorimpl = new MeteorImpl(new Pair<>(0,0), 1, 1, LENGTH, ID.METEOR);
		final BulletImpl bulletplayer = new BulletImpl(0, 0, ID.PLAYER_BULLET);
		meteorimpl.collide(bulletplayer);
		assertFalse(meteorimpl.isDead());
		meteorimpl.collide(bulletplayer);
		meteorimpl.collide(bulletplayer);
		assertTrue(meteorimpl.isDead());	
	}

}
