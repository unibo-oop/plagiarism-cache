package model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.tuple.Pair;

import model.entities.zombie.IZombieFactory;
import model.entities.zombie.Zombie;
import model.entities.zombie.ZombieFactory;
import model.entities.zombie.ZombieState;

public class ZombieTest {
    private Zombie clicker;
    private IZombieFactory zobFactory;
    private Pair<Double, Double> positionInitial;

    @BeforeEach
    void setUp() throws Exception {
        zobFactory = new ZombieFactory();
        positionInitial = Pair.of(10.0, 20.0);
        clicker = zobFactory.createClickerZombie(positionInitial);
    }

    @Test
    void testInitialPosition() {
        assertEquals(positionInitial.getLeft(), clicker.getCurrentPos().getLeft());
        assertEquals(positionInitial.getRight(), clicker.getCurrentPos().getRight());
    }

    @Test
    public void testAttackValue() {
        assertEquals(20, clicker.attack());
    }

    @Test
    public void testDamageSufferReducesHealth() {
        clicker.damageSuffer(70);
        assertEquals(930, clicker.getLive());
        assertEquals(ZombieState.ZOMBIE_SUFFER_DAMAGE, clicker.getState());
        assertFalse(clicker.isZombieDead());
    }

    @Test
    public void testDamageSufferKillsZombie() {
        clicker.damageSuffer(1000);
        assertEquals(0, clicker.getLive());
        assertTrue(clicker.isZombieDead());
    }
}
