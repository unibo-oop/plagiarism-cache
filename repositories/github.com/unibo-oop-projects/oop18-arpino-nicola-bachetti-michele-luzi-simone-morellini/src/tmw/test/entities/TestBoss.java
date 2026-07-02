package tmw.test.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.model.entities.BossEntity;
import tmw.model.entities.BossEntityImpl;
import tmw.model.entities.MilkEntityImpl;

/**
 * JUnit test for the boss.
 */
public class TestBoss {

    private static final Dim2D SIZE = new Dim2D(1000, 1000);
    private static final V2d INITIAL_VELOCITY = new V2d(1, 1);
    private static final P2d INITIAL_POSITION = new P2d(0, 0);
    private static final P2d BOSS_FINAL_POSITION = new P2d(25, 25);
    private static final int DAMAGE = 10;
    private static final int HIGH_DAMAGE = 200;

    /**
     * This method test if the boss is created in the right way.
     */
    @Test
    public void creationTest() {
        BossEntity boss = new BossEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        assertTrue(boss.isAlive());
        assertFalse(boss.readyToShoot());
        assertFalse(boss.readyForSpecialAttack());
        assertTrue(boss.intersect(new MilkEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE)));
        assertFalse(boss.intersect(new MilkEntityImpl(new P2d(100, 100), INITIAL_VELOCITY, SIZE)));
        boss.destroy();
        assertFalse(boss.isAlive());
    }

    /**
     * This method test the movement of the boss.
     */
    @Test
    public void movementTest() {
        BossEntity boss = new BossEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        for (int i = 1; i <= 10; i++) {
            P2d bossNewPosition = boss.getCurrentPos().sum(boss.getCurrentVel().mul(boss.getSpeed()));
            boss.update(Optional.of(bossNewPosition));
            assertEquals(new P2d(i * INITIAL_VELOCITY.mul(boss.getSpeed()).getX(),
                    i * INITIAL_VELOCITY.mul(boss.getSpeed()).getY()), boss.getCurrentPos());
        }
        assertEquals(BOSS_FINAL_POSITION, boss.getCurrentPos());
    }

    /**
     * This method test if the boss attack correctly.
     */
    @Test
    public void attackTest() {
        BossEntity boss = new BossEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        double normalSpeed = boss.getSpeed();
        boss.startSpecialAttack();
        assertEquals(normalSpeed * 2, boss.getSpeed(), 0);
        boss.stopSpecialAttack();
        assertTrue(normalSpeed == boss.getSpeed());
        assertFalse(boss.readyForSpecialAttack());
    }

    /**
     * This method test if the boss can take damage.
     */
    @Test
    public void hpTest() {
        BossEntity boss = new BossEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        int stelleHp = boss.getCurrentHealth();
        boss.takeDamage(DAMAGE);
        assertEquals(stelleHp - DAMAGE, boss.getCurrentHealth());
        boss.takeDamage(HIGH_DAMAGE);
        assertEquals(0, boss.getCurrentHealth());
        assertFalse(boss.isAlive());
    }
}
