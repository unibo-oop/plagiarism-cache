package model.armory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.armory.charger.Charger;
import model.armory.charger.FactoryCharger;
import model.armory.munition.Munition;
import model.armory.weapon.Pistol;

public class PistolTest {
    private Pistol pistol;
    private Charger charger;
    private final Pair<Double, Double> initialPos = Pair.of(100.0, 200.0);

    @BeforeEach
    void setUp() {
        // Usa FactoryCharger per creare un drum charger
        charger = new FactoryCharger().createDrumCharger(initialPos);
        pistol = new Pistol(initialPos, 450, 1, charger);
        pistol.aim(Pair.of(1.0, 0.0), initialPos); // aim on the left 
    }

    @Test
    void testInitialAmmoCount() {
        assertEquals(5, pistol.getAmmoCount(), "Initial ammo count should match charger capacity");
    }

    @Test
    void testShootBeforeCooldownReturnsEmpty() {
        List<Munition> fired = pistol.shoot(100); // cooldown 450m
        assertTrue(fired.isEmpty(), "No shot should be fired before cooldown elapsed");
    }

    @Test
    void testShootAfterCooldownReturnsMunition() {
        List<Munition> fired = pistol.shoot(500);
        assertEquals(1, fired.size(), "One munition should be fired after cooldown");
        assertEquals(4, pistol.getAmmoCount(), "Ammo count should decrease by one after shooting");
    }

    @Test
    void testMultipleShotsRespectCooldown() {
        pistol.shoot(500);
        assertEquals(4, pistol.getAmmoCount());

        List<Munition> firedEarly = pistol.shoot(100);
        assertTrue(firedEarly.isEmpty(), "Second shot too early, should not fire");

        List<Munition> firedLate = pistol.shoot(400);
        assertEquals(1, firedLate.size(), "Second shot after cooldown should fire");
        assertEquals(3, pistol.getAmmoCount());
    }

    @Test
    void testShotsPerFireMoreThanOne() {
        Pistol multiShotPistol = new Pistol(initialPos, 0, 3, charger);
        multiShotPistol.aim(Pair.of(0.0, 1.0), initialPos);

        List<Munition> fired = multiShotPistol.shoot(1000);
        assertEquals(3, fired.size(), "Should fire 3 munitions per shoot");
        assertEquals(2, multiShotPistol.getAmmoCount(), "Ammo count should reduce by 3");
    }
}
