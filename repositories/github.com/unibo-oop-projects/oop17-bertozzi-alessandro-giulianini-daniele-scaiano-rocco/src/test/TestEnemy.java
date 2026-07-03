package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.GameImpl;
import game.GameMode;
import game.ID;
import game.Player;
import game.enemy.SmartEnemy;
import game.enemy.FastEnemy;
import game.enemy.BasicEnemy;
import game.enemy.BigEnemy;
import game.enemy.BossEnemy;
import game.enemy.DirEnemy;
import game.enemy.Shot;
import game.enemy.AbstractEnemy;
import utilities.Pair;

/**
 * Class to test the enemy and their shots, {@link BasicEnemy}, {@link FastEnemy}, {@link BigEnemy}, {@link SmartEnemy}, {@link BossEnemy} and {@link Shot}.
 *
 */
public class TestEnemy {

    private static final int EXPECTED = 6;
    /**
     * 
     */
    @Test
    public void testBasicEnemy() {
        final GameImpl game = new GameImpl(GameMode.SINGLEPLAYER);
        final AbstractEnemy basic = new BasicEnemy(game);
        final Player player = new Player(ID.PLAYER, game);
        basic.createThisEnemy();
        assertNotNull(basic);
        final Pair<Integer, Integer> position = basic.getPosition();
        assertNotSame(position, player.getPosition());
        assertNotSame(position, new Pair<Integer, Integer>(0, 0));
        basic.setDead();
        assertTrue(basic.isDead());
        basic.setAlive();
        assertFalse(basic.isDead());
    }
    /**
     * 
     */
    @Test
    public void testFastEnemy() {
        final GameImpl game = new GameImpl(GameMode.SINGLEPLAYER);
        final AbstractEnemy fast = new FastEnemy(game);
        final Player player = new Player(ID.PLAYER, game);
        fast.createThisEnemy();
        assertNotNull(fast);
        final Pair<Integer, Integer> position = fast.getPosition();
        assertNotSame(position, player.getPosition());
        assertNotSame(position, new Pair<Integer, Integer>(0, 0));
        fast.setDead();
        assertTrue(fast.isDead());
        fast.setAlive();
        assertFalse(fast.isDead());
    }
    /**
     * 
     */
    @Test
    public void testBigEnemy() {
        final GameImpl game = new GameImpl(GameMode.SINGLEPLAYER);
        final AbstractEnemy big = new BigEnemy(game);
        final Player player = new Player(ID.PLAYER, game);
        big.createThisEnemy();
        assertNotNull(big);
        final Pair<Integer, Integer> position = big.getPosition();
        assertNotSame(position, player.getPosition());
        assertNotSame(position, new Pair<Integer, Integer>(0, 0));
        big.setDead();
        assertTrue(big.isDead());
        big.setAlive();
        assertFalse(big.isDead());
    }
    /**
     * 
     */
    @Test
    public void testSmartEnemy() {
        final GameImpl game = new GameImpl(GameMode.SINGLEPLAYER);
        final AbstractEnemy smart = new SmartEnemy(game);
        final Player player = new Player(ID.PLAYER, game);
        smart.createThisEnemy();
        assertNotNull(smart);
        final Pair<Integer, Integer> position = smart.getPosition();
        assertNotSame(position, player.getPosition());
        assertNotSame(position, new Pair<Integer, Integer>(0, 0));
        smart.update();
        if (position.getX() > player.getPosition().getX()) {
            position.setX(position.getX() + smart.getVelocity().getX());
            assertEquals(position.getX(), smart.getPosition().getX());
        } else if (position.getX() < player.getPosition().getX()) {
            position.setX(position.getX() - smart.getVelocity().getX());
            assertEquals(position.getX(), smart.getPosition().getX());
        } else if (position.getY() > player.getPosition().getY()) {
            position.setY(position.getY() + smart.getVelocity().getY());
            assertEquals(position.getY(), smart.getPosition().getY());
        } else if (position.getY() < player.getPosition().getY()) {
            position.setY(position.getY() - smart.getVelocity().getY());
            assertEquals(position.getY(), smart.getPosition().getY());
        }
        smart.setDead();
        assertTrue(smart.isDead());
        smart.setAlive();
        assertFalse(smart.isDead());
    }
    /**
     * 
     */
    @Test
    public void testBossEnemy() {
        final GameImpl game = new GameImpl(GameMode.SINGLEPLAYER);
        final AbstractEnemy boss = new BossEnemy(game);
        final Player player = new Player(ID.PLAYER, game);
        boss.createThisEnemy();
        assertNotNull(boss);
        final Pair<Integer, Integer> position = boss.getPosition();
        assertNotSame(position, player.getPosition());
        assertNotSame(position, new Pair<Integer, Integer>(0, 0));
        boss.update();
        if (position.getX() > player.getPosition().getX()) {
            position.setX(position.getX() + boss.getVelocity().getX());
            assertEquals(position.getX(), boss.getPosition().getX());
        } else if (position.getX() < player.getPosition().getX()) {
            position.setX(position.getX() - boss.getVelocity().getX());
            assertEquals(position.getX(), boss.getPosition().getX());
        } else if (position.getY() > player.getPosition().getY()) {
            position.setY(position.getY() + boss.getVelocity().getY());
            assertEquals(position.getY(), boss.getPosition().getY());
        } else if (position.getY() < player.getPosition().getY()) {
            position.setY(position.getY() - boss.getVelocity().getY());
            assertEquals(position.getY(), boss.getPosition().getY());
        }
        boss.setDead();
        assertTrue(boss.isDead());
        boss.setAlive();
        assertFalse(boss.isDead());
    }
    /**
     * 
     */
    @Test
    public void testShoot() {
        final Shot shot = new Shot(6, 6, DirEnemy.UP);
        assertNotNull(shot);
        assertEquals(new Pair<Integer, Integer>(0, 3), shot.getVelocity());
        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED), shot.getPosition());
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED * 2), shot.getPosition());
        shot.setDir(DirEnemy.DOWN);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED), shot.getPosition());
        shot.setDir(DirEnemy.LEFT);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(0, EXPECTED), shot.getPosition());
        shot.setDir(DirEnemy.RIGHT);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED), shot.getPosition());
        shot.setDir(DirEnemy.U_R);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED * 2, EXPECTED * 2), shot.getPosition());
        shot.setDir(DirEnemy.U_L);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED * 3), shot.getPosition());
        shot.setDir(DirEnemy.D_R);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED * 2, EXPECTED * 2), shot.getPosition());
        shot.setDir(DirEnemy.D_L);
        shot.update();
        shot.update();
        assertEquals(new Pair<Integer, Integer>(EXPECTED, EXPECTED), shot.getPosition());
        shot.setDead();
        assertTrue(shot.isDead());
        shot.setAlive();
        assertFalse(shot.isDead());
        shot.update();
        shot.update();
        shot.update();
        shot.update();
        assertTrue(shot.isDead());
    }
}
