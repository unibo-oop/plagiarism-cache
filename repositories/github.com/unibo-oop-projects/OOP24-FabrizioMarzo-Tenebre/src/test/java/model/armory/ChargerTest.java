package model.armory;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.armory.charger.Charger;
import model.armory.charger.Drum;
import model.armory.charger.FactoryCharger;

public class ChargerTest {
    private FactoryCharger factory;

    @BeforeEach
    void setUp() throws Exception {
        factory = new FactoryCharger();
    }

    @Test
    void testCreateDrumChargerNotNull() {
        Pair<Double, Double> pos = Pair.of(10.0, 20.0);
        Charger charger = factory.createDrumCharger(pos);
        assertNotNull(charger, "Factory should create a non-null Drum");
    }

    @Test
    void testCreateDrumChargerHasCorrectType() {
        Pair<Double, Double> pos = Pair.of(0.0, 0.0);
        Charger charger = factory.createDrumCharger(pos);
        assertTrue(charger instanceof Drum, "Factory should return a Drum instance");
    }

    @Test
    void testCreatedDrumHasCorrectInitialLoad() {
        Pair<Double, Double> pos = Pair.of(5.0, 5.0);
        Charger charger = factory.createDrumCharger(pos);
        assertEquals(5, charger.getCurrentLoad(), "Drum created by factory should have a capacity of 5");
    }

    @Test
    void testDrumChargerExtractsMunition() {
        Pair<Double, Double> pos = Pair.of(15.0, 30.0);
        Charger charger = factory.createDrumCharger(pos);
        assertNotNull(charger.extractAmmunition(), "Extracted munition should not be null");
        assertEquals(4, charger.getCurrentLoad(), "Load should decrease after extraction");
    }
}
