package it.unibo.model.hitbox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.commons.Constants;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.CircularSawImpl;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.obstacles.PlatformImpl;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.player.MeatBoyImpl;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.entity.target.BandageGirlImpl;

/**
 * JUnit test class for testing the hitboxes of various game entities, including MeatBoy, BandageGirl,
 * Platform, and CircularSaw.
 */
class TestHitbox {

    private MeatBoy meatBoy;
    private Platform platform;
    private BandageGirl bandageGirl;
    private CircularSaw saw;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 100;
    private static final int RADIUS = 30;

    /**
     * Initializes instances of game entities for testing hitboxes.
     *
     * @throws MalformedURLException if there is an issue with URL creation.
     */
    @BeforeEach 
    public void init() throws MalformedURLException {
        this.meatBoy = new MeatBoyImpl(0, 0);
        assertNotNull(this.meatBoy);
        this.bandageGirl = new BandageGirlImpl(0, 0);
        assertNotNull(this.bandageGirl);
        this.platform = new PlatformImpl(0, 0, WIDTH, HEIGHT);
        assertNotNull(this.platform);
        this.saw = new CircularSawImpl(0, 0, RADIUS);
        assertNotNull(this.saw);
    }

    /**
     * Tests the hitbox of the MeatBoy entity.
     */
    @Test
    void testMeatBoyHitbox() {
        final RectangleHitbox meatboyHitbox = this.meatBoy.getHitbox();
        assertNotNull(meatboyHitbox);
        final Rectangle hitboxShapeMB = meatboyHitbox.getHitbox();
        assertNotNull(hitboxShapeMB);
        assertEquals(
                List.of(this.meatBoy.getX(), this.meatBoy.getY()),
                List.of(hitboxShapeMB.getX(), hitboxShapeMB.getY()));
        assertEquals(
                List.of(
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION), 
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION)),
                List.of(hitboxShapeMB.getWidth(), hitboxShapeMB.getHeight()));
        this.meatBoy.setX(HEIGHT);
        this.meatBoy.setY(HEIGHT);
        assertEquals(
                List.of(this.meatBoy.getX(), this.meatBoy.getY()),
                List.of(hitboxShapeMB.getX(), hitboxShapeMB.getY()));
        assertEquals(
                List.of(
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION), 
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION)),
                List.of(hitboxShapeMB.getWidth(), hitboxShapeMB.getHeight()));
    }

    /**
     * Tests the hitbox of the BandageGirl entity.
     */
    @Test
    void testBandageGirlHitbox() {
        final RectangleHitbox bandageGirlHitbox = this.bandageGirl.getHitbox();
        assertNotNull(bandageGirlHitbox);
        final Rectangle hitboxShapeBG = bandageGirlHitbox.getHitbox();
        assertNotNull(hitboxShapeBG);
        assertEquals(
                List.of(this.bandageGirl.getX(), this.bandageGirl.getY()),
                List.of(hitboxShapeBG.getX(), hitboxShapeBG.getY()));
        assertEquals(
                List.of(
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION), 
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION)),
                List.of(hitboxShapeBG.getWidth(), hitboxShapeBG.getHeight()));
        this.bandageGirl.setX(HEIGHT);
        this.bandageGirl.setY(HEIGHT);
        assertEquals(
                List.of(this.bandageGirl.getX(), this.bandageGirl.getY()),
                List.of(hitboxShapeBG.getX(), hitboxShapeBG.getY()));
        assertEquals(
                List.of(
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION), 
                    Math.floor(Constants.TILE_SIZE * Constants.SCALE_PROPORTION)),
                List.of(hitboxShapeBG.getWidth(), hitboxShapeBG.getHeight()));
    }

    /**
     * Tests the hitbox of the Platform entity.
     */
    @Test
    void testPlatformHitbox() {
        final RectangleHitbox platformHitbox = this.platform.getHitbox();
        assertNotNull(platformHitbox);
        final Rectangle hitboxShapeP = platformHitbox.getHitbox();
        assertNotNull(hitboxShapeP);
        assertEquals(
                List.of(this.platform.getX(), this.platform.getY()),
                List.of(hitboxShapeP.getX(), hitboxShapeP.getY()));
        assertEquals(
                List.of((double) WIDTH, (double) HEIGHT),
                List.of(hitboxShapeP.getWidth(), hitboxShapeP.getHeight()));
        this.platform.setX(HEIGHT);
        this.platform.setY(HEIGHT);
        assertEquals(
                List.of(this.platform.getX(), this.platform.getY()),
                List.of(hitboxShapeP.getX(), hitboxShapeP.getY()));
        assertEquals(
                List.of((double) WIDTH, (double) HEIGHT),
                List.of(hitboxShapeP.getWidth(), hitboxShapeP.getHeight()));
    }

    /**
     * Tests the hitbox of the CircularSaw entity.
     */
    @Test
    void testSawHitbox() {
        final CircularHitbox sawHitbox = this.saw.getHitbox();
        assertNotNull(sawHitbox);
        final Ellipse2D hitboxShapeS = sawHitbox.getHitbox();
        assertNotNull(hitboxShapeS);
        assertEquals(
                List.of(this.saw.getX(), this.saw.getY()),
                List.of(hitboxShapeS.getX(), hitboxShapeS.getY()));
        assertEquals(
                List.of((double) RADIUS, (double) RADIUS),
                List.of(hitboxShapeS.getWidth(), hitboxShapeS.getHeight()));
        this.saw.setX(HEIGHT);
        this.saw.setY(HEIGHT);
        assertEquals(
                List.of(this.saw.getX(), this.saw.getY()),
                List.of(hitboxShapeS.getX(), hitboxShapeS.getY()));
        assertEquals(
                List.of((double) RADIUS, (double) RADIUS),
                List.of(hitboxShapeS.getWidth(), hitboxShapeS.getHeight()));
    }
}
