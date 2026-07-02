package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.Test;

import controller.PowerUp.HighStrategy;
import controller.PowerUp.LowStrategy;
import model.GameImpl;
import model.ID;
import model.PlayerImpl;
import model.powerup.PPowerUp;
import model.powerup.PowerUpT;
import utility.ImageLoader;
import utility.Pair;

/**
 * The Class TestPowerUp.
 */
public class TestPowerUp {
	
	/** The Constant XEXPECTED. */
	private static final int XEXPECTED = 0;
    
    /** The Constant YEXPECTED. */
    private static final int YEXPECTED= 9;
    
    /** The Constant INITIAL_H. */
    private static final int INITIAL_H = 20;
    
    /** The Constant FINAL_H. */
    private static final int FINAL_H = 40;

    /**
     * Test power up B.
     */
    @Test
	public void TestPowerUpB() {
		final PPowerUp ppu = new PPowerUp(new Pair<>(0,0),1,1,ID.POWER_UP,PowerUpT.HEALTH, new HighStrategy());
		ppu.update();
		ppu.setSpeed(1, 1);
		ppu.update();
		ppu.setSpeed(1, 1);
		ppu.update();
		assertEquals(ppu.getPosition().getX().intValue(), 0);
		assertEquals(ppu.getPosition().getY().intValue(), 3);
		ppu.setSpeed(1, 3);
	    ppu.update();
	    ppu.setSpeed(1, 3);
	    ppu.update();
	    assertEquals(ppu.getPosition().getX().intValue(), XEXPECTED);
	    assertEquals(ppu.getPosition().getY().intValue(), YEXPECTED);
	    ppu.collide(new PPowerUp(new Pair<>(0, 0), 1, 1,ID.POWER_UP, PowerUpT.SHIELD, new HighStrategy()));
	    assertFalse(ppu.isActivated());
	}
    
    /**
     * Test health power up.
     */
    @Test
    public void TestHealthPowerUp() {
    	final PlayerImpl player = new PlayerImpl(ID.PLAYER, new GameImpl());
    	final PPowerUp ppu = new PPowerUp(new Pair<>(0,0),1,1,ID.POWER_UP, PowerUpT.HEALTH,new LowStrategy());
    	player.setHealth(INITIAL_H);
    	assertSame(player.getHealth(),INITIAL_H);
    	ppu.collide(player);
    	assertSame(player.getHealth(),FINAL_H);
    	ppu.update();
    	assertTrue(ppu.isDead());
    }
    
    /**
     * Test shield power up.
     */
    @Test
    public void TestShieldPowerUp() {
    	final PlayerImpl player = new PlayerImpl(ID.PLAYER, new GameImpl());
    	final PPowerUp ppu = new PPowerUp(new Pair<>(0,0),1,1,ID.POWER_UP, PowerUpT.SHIELD,new HighStrategy());
    	ppu.collide(player);
    	assertTrue(player.getShield() > 0);
    	assertTrue(ppu.isActivated());
    	assertNotNull(ppu.getEntityStrategy());
    	IntStream.range(0,PowerUpT.SHIELD.getLifetime()+1).forEach(i ->ppu.update());
    	assertTrue(ppu.isDead());
    	assertEquals(player.getShield(),0);
    }
    
    /**
     * Test imageloader.
     */
    @Test
    public void testImageloader() {
    	final ImageLoader loader = ImageLoader.getImageLoader();
    	assertNotNull(loader);
    	loader.findImages();
    	assertFalse(loader.getEntityImages().isEmpty());
    }
}