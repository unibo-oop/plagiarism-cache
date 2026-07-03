package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import game.Bullet;
import game.GameImpl;
import game.GameMode;
import game.ID;
import game.Player;
import utilities.Pair;

/**
 * creating the test class for player, bullet and keyInput.
 */
public class TestPlayerAndMore {
    /**
     *test if the player moves and loses hps. 
     */
    @Test
    public void testPlayer() {
        final GameImpl gameTest = new GameImpl(GameMode.SINGLEPLAYER);
        final Player testPlayer = new Player(ID.PLAYER, gameTest);
        assertNotNull(testPlayer);
        final Pair<Integer, Integer> position = testPlayer.getPosition();
        testPlayer.setVelocity(10, 10);
        testPlayer.update();
        assertTrue(testPlayer.getPosition().equals(position));
        final int healtExample = testPlayer.getHealth();
        testPlayer.collide(new Bullet(testPlayer.getPosition().getX(), testPlayer.getPosition().getY(), ID.PLAYER2));
        assertNotEquals(healtExample, testPlayer.getHealth());
    }
    /**
     * test if the bullet moves.
     */
    @Test
    public void testBullet() {
        final Bullet testBullet = new Bullet(0, 0, ID.PLAYER);
        assertNotNull(testBullet);
        final Pair<Integer, Integer> position = testBullet.getPosition();
        testBullet.setVelocity(0, 0);
        testBullet.update();
        assertEquals(position, testBullet.getPosition());
    }
}
