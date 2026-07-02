package tmw.test.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.model.entities.EnemyAbbraccio;
import tmw.model.entities.BossEntity;
import tmw.model.entities.BossEntityImpl;
import tmw.model.entities.Enemy;
import tmw.model.entities.EnemyStelle;

/**
 * JUnit test for the enemies.
 */
public class TestEnemy {

    private static final Dim2D SIZE = new Dim2D(800, 600);
    private static final V2d INITIAL_VELOCITY = new V2d(1, 1);
    private static final P2d INITIAL_POSITION = new P2d(0, 0);
    private static final P2d ABBRACCIO_FINAL_POSITION = new P2d(15, 15);
    private static final P2d BOSS_FINAL_POSITION = new P2d(20, 20);
    private static final int DAMAGE = 10;
    private static final int HIGH_DAMAGE = 200;

    /**
     * This method test if the enemies are created in the right way.
     */
    @Test
    public void creationTest() {
        Enemy stelle = new EnemyStelle(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        Enemy abbraccio = new EnemyAbbraccio(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        assertTrue(stelle.isAlive());
        assertTrue(abbraccio.isAlive());
        assertFalse(stelle.readyToShoot());
        assertFalse(abbraccio.readyToShoot());
        assertTrue(stelle.intersect(abbraccio));
        assertTrue(abbraccio.intersect(stelle));
        abbraccio.setPos(ABBRACCIO_FINAL_POSITION);
        assertTrue(abbraccio.intersect(stelle));
        abbraccio.setPos(new P2d(100, 100));
        assertFalse(abbraccio.intersect(stelle));
        stelle.destroy();
        abbraccio.destroy();
        assertFalse(abbraccio.isAlive());
        assertFalse(stelle.isAlive());
    }

    /**
     * This method test the movement of the enemies.
     */
    @Test
    public void movementTest() {
        Enemy stelle = new EnemyStelle(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        Enemy abbraccio = new EnemyAbbraccio(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        BossEntity boss = new BossEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        for (int i = 0; i < 10; i++) {
            P2d stelleNewPosition = stelle.getCurrentPos().sum(stelle.getCurrentVel().mul(stelle.getSpeed()));

            P2d abbraccioNewPosition = abbraccio.getCurrentPos()
                    .sum(abbraccio.getCurrentVel().mul(abbraccio.getSpeed()));

            P2d bossNewPosition = boss.getCurrentPos().sum(boss.getCurrentVel().mul(boss.getSpeed()));

            stelle.update(Optional.of(stelleNewPosition));
            abbraccio.update(Optional.of(abbraccioNewPosition));
            boss.update(Optional.of(bossNewPosition));
        }
        assertEquals(INITIAL_POSITION, stelle.getCurrentPos());
        assertEquals(ABBRACCIO_FINAL_POSITION, abbraccio.getCurrentPos());
        assertEquals(BOSS_FINAL_POSITION, boss.getCurrentPos());
    }

    /**
     * This method test if the enemies can take damage.
     */
    @Test
    public void hpTest() {
        Enemy stelle = new EnemyStelle(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        Enemy abbraccio = new EnemyAbbraccio(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        int stelleHp = stelle.getCurrentHealth();
        int abbraccioHp = abbraccio.getCurrentHealth();
        stelle.takeDamage(DAMAGE);
        abbraccio.takeDamage(DAMAGE);
        assertEquals(stelleHp - DAMAGE, stelle.getCurrentHealth());
        assertEquals(abbraccioHp - DAMAGE, abbraccio.getCurrentHealth());
        stelle.takeDamage(HIGH_DAMAGE);
        abbraccio.takeDamage(HIGH_DAMAGE);
        assertEquals(0, stelle.getCurrentHealth());
        assertEquals(0, abbraccio.getCurrentHealth());
        assertFalse(abbraccio.isAlive());
        assertFalse(stelle.isAlive());
    }

}
