package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.model.implementations.PlayerObjectImpl;
import frogger.model.interfaces.PlayerObject;

class PlayerObjectTest {

    private static final int INITIAL_LIVES = 3;
    private static final int POINTS_5 = 5;
    private static final int POINTS_10 = 10;
    private static final int POINTS_15 = 15;

    private PlayerObject player;

    @BeforeEach
    void setUp() {
        player = new PlayerObjectImpl(new Pair(1, 1), "ranocchietta.png");
    }

    @Test
    void testInitialState() {
        assertEquals(INITIAL_LIVES, player.getLives());
        assertEquals(0, player.getScore());
        assertEquals(Direction.UP, player.getDirection());
        assertFalse(player.isAttached());
        assertFalse(player.isDead());
    }

    @Test
    void testAddLife() {
        player.addLife();
        assertEquals(INITIAL_LIVES + 1, player.getLives());
    }

    @Test
    void testPlayerHitAndRespawn() {
        player.playerHit();
        assertEquals(INITIAL_LIVES - 1, player.getLives());
        assertTrue(player.isDead());
        player.respawn();
        assertFalse(player.isDead());
        assertEquals(Direction.UP, player.getDirection());
    }

    @Test
    void testDirectionSetters() {
        player.setLookingRight();
        assertEquals(Direction.RIGHT, player.getDirection());
        player.setLookingLeft();
        assertEquals(Direction.LEFT, player.getDirection());
        player.setLookingDown();
        assertEquals(Direction.DOWN, player.getDirection());
        player.setLookingUp();
        assertEquals(Direction.UP, player.getDirection());
    }

    @Test
    void testAddPoints() {
        player.addPoints(POINTS_10);
        assertEquals(POINTS_10, player.getScore());
        player.addPoints(POINTS_5);
        assertEquals(POINTS_15, player.getScore());
    }

    @Test
    void testAttached() {
        player.setAttached(true);
        assertTrue(player.isAttached());
        player.setAttached(false);
        assertFalse(player.isAttached());
    }

    @Test
    void testResetPosition() {
        player.setLookingDown();
        player.resetPosition();
        assertEquals(Direction.UP, player.getDirection());
    }
}
