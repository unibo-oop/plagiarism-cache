package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantRegistry;
import it.unibo.plantsfarm.model.plant.Rarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PlantTest {

    private static final long ADDED_TIME = 35_000L;

    @Test
    void testPlantCreation() {
        final PlantImpl plant = new PlantImpl(PlantRegistry.CARROT);
        assertTrue(plant.needsWater());
        assertEquals(plant.getName(), "Carrot");
        assertEquals(plant.getRarity(), Rarity.COMMON);
        assertEquals(plant.getType(), PlantRegistry.CARROT);
        assertEquals(plant.getGrowthStage(), 0);
    }

    @Test
    void testPlantWatering() {
        final PlantImpl plant = new PlantImpl(PlantRegistry.CARROT);
        final long now = System.currentTimeMillis();
        plant.water(now);
        assertFalse(plant.needsWater());
        plant.water(plant.getLastWateredTime() + PlantImpl.WATER_TIME_COOLDOWN);
        assertFalse(plant.needsWater());
    }

    @Test
    void testPlantGrowth() {
        final PlantImpl plant = new PlantImpl(PlantRegistry.CARROT);
        plant.setCurrentStageTime(plant.getCurrentStageTime() + ADDED_TIME);
        plant.increaseGrowthStage(System.currentTimeMillis());
        assertEquals(plant.getGrowthStage(), 0);
        plant.water(System.currentTimeMillis());
        plant.increaseGrowthStage(System.currentTimeMillis());
        assertEquals(plant.getGrowthStage(), 1);
        assertEquals(plant.getMaxGrowthStage(), 3);
        plant.setGrowthStage(3);
        assertEquals(plant.getGrowthStage(), plant.getMaxGrowthStage());
    }

    @Test
    void testPlantHoeing() {
        final PlantImpl plant = new PlantImpl(PlantRegistry.CARROT);
        plant.setGrowthStage(3);
        assertEquals(plant.getGrowthStage(), 3);
        assertTrue(plant.isMature());
        plant.harvest();
        assertTrue(plant.getHarvestedQuantity() >= 2 && plant.getHarvestedQuantity() <= 3);
    }
}
