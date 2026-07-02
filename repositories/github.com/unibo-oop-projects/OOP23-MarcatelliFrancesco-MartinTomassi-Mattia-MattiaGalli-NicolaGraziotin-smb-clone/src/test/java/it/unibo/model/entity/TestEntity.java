package it.unibo.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.CircularSawImpl;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.obstacles.PlatformImpl;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.player.MeatBoyImpl;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.entity.target.BandageGirlImpl;
import it.unibo.model.hitbox.CircularHitbox;
import it.unibo.model.hitbox.RectangleHitbox;

class TestEntity {

    @Test
    void testMeatBoy() {
        final MeatBoy meatBoy = new MeatBoyImpl(0, 0);
        assertEquals(RectangleHitbox.class, meatBoy.getHitbox().getClass());
    }

    @Test
    void testBandageGirl() {
        final BandageGirl bandageGirl = new BandageGirlImpl(0, 0);
        assertEquals(RectangleHitbox.class, bandageGirl.getHitbox().getClass());
    }

    @Test
    void testCircularSaw() {
        final CircularSaw circularSaw = new CircularSawImpl(0, 0, 10);
        assertEquals(CircularHitbox.class, circularSaw.getHitbox().getClass());
    }

    @Test
    void testPlatform() {
        final Platform platform = new PlatformImpl(0, 0, 100, 20);
        assertEquals(RectangleHitbox.class, platform.getHitbox().getClass());
    }
}
