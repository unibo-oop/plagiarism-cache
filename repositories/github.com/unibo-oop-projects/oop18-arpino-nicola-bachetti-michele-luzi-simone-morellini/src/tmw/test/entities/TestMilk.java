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
import tmw.model.entities.MilkEntity;
import tmw.model.entities.MilkEntityImpl;

/**
 * JUnit test for the main character.
 */
public class TestMilk {

    private static final Dim2D SIZE = new Dim2D(800, 600);
    private static final V2d INITIAL_VELOCITY = new V2d(1, 1);
    private static final P2d INITIAL_POSITION = new P2d(0, 0);
    private static final P2d ABBRACCIO_POSITION = new P2d(15, 15);
    private static final P2d FINAL_POSITION = new P2d(50, 50);
    private static final int DAMAGE = 30;
    private static final int HEAL = 10;
    private static final int HIGH_HEAL = 200;
    private static final int HIGH_DAMAGE = 200;

    /**
     * This method test if the enemies are created in the right way.
     */
    @Test
    public void creationTest() {
        final MilkEntity milk = new MilkEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        assertTrue(milk.isAlive());
        assertFalse(milk.isThisTheEnd());
        assertFalse(milk.readyToShoot());
        assertTrue(new EnemyAbbraccio(ABBRACCIO_POSITION, INITIAL_VELOCITY, SIZE).intersect(milk));
        milk.destroy();
        assertFalse(milk.isAlive());

    }

    /**
     * This method test the movement of the milk.
     */
    @Test
    public void movementTest() {
        final MilkEntity milk = new MilkEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        double speed = milk.getDefaultSpeed();

        for (int i = 0; i < 10; i++) {
            P2d stelleNewPosition = milk.getCurrentPos().sum(milk.getCurrentVel().mul(milk.getSpeed()));

            milk.update(Optional.of(stelleNewPosition));
        }
        assertEquals(FINAL_POSITION, milk.getCurrentPos());

        milk.setSpeed(speed * 2);
        assertEquals(speed * 2, milk.getSpeed(), 0);
        milk.setDefaultSpeed();
        assertEquals(speed, milk.getSpeed(), 0);
    }

    /**
     * This method test if the milk set values correctly.
     */
    @Test
    public void shootingTest() {
        final MilkEntity milk = new MilkEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        int bulletDamage = milk.getDefaultDamage();
        int reloadTime = milk.getDefaultTimeForReload();

        milk.setDamage(bulletDamage * 2);
        assertEquals(bulletDamage * 2, milk.getDamage());
        milk.setDefaultDamage();
        assertEquals(bulletDamage, milk.getDamage());

        milk.setTimeForReload(reloadTime * 2);
        assertEquals(reloadTime * 2, milk.getTimeForReload());
        milk.setDefaultTimeForReload();
        assertEquals(reloadTime, milk.getTimeForReload());

    }

    /**
     * This method test if the milk can take damage and heal itself.
     */
    @Test
    public void hpTest() {
        MilkEntity milk = new MilkEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        final int maxHp = milk.getMaxHp();
        milk.takeDamage(DAMAGE);
        assertEquals(maxHp - DAMAGE, milk.getCurrentHealth());
        final int milkHp = milk.getCurrentHealth();
        milk.heal(HEAL);
        assertEquals(milkHp + HEAL, milk.getCurrentHealth());
        milk.heal(HIGH_HEAL);
        assertEquals(maxHp, milk.getCurrentHealth());
        milk = new MilkEntityImpl(INITIAL_POSITION, INITIAL_VELOCITY, SIZE);
        milk.takeDamage(HIGH_DAMAGE);
        assertEquals(0, milk.getCurrentHealth());
        assertFalse(milk.isAlive());
    }

}
