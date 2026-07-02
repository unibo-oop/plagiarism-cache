package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import model.object.Projectile;
import model.object.ProjectileImpl;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Test class for the projectile.
 */
public class TestProjectile {

    private static final int PROJECTILE_POSITION = 6;

    /**
     * Tests the initial state of a projectile.
     */
    @Test
    public void testInitialStateProjectile() {
        final Projectile projectile = new ProjectileImpl(new Pair<>(10.0, 10.0), 25, 4);
        assertNotNull(projectile);
        assertEquals(projectile.getPosition().getFirst().intValue(), 10);
        assertEquals(projectile.getPosition().getSecond().intValue(), 10);
    }

    /**
     * Tests the bounce of a projectile.
     */
    @Test
    public void testBounceProjectile() {
        final Projectile projectile = new ProjectileImpl(new Pair<>(10.0, 10.0), 0, 4);
        projectile.bounce(Direction.RIGHT);
        projectile.update();
        assertEquals(projectile.getPosition().getFirst().intValue(), PROJECTILE_POSITION);
        projectile.update();
        assertEquals(projectile.getPosition().getFirst().intValue(), 2);
    }

}
