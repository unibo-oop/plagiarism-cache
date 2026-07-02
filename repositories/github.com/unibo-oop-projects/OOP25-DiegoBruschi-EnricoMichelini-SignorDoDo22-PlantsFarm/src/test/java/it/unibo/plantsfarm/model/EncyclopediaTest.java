package it.unibo.plantsfarm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.menu.api.Encyclopedia;
import it.unibo.plantsfarm.model.menu.impl.EncyclopediaImpl;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantRegistry;

final class EncyclopediaTest {

    private final Encyclopedia encyclopedia = new EncyclopediaImpl();
    private final PlantImpl carrotInstance = new PlantImpl(PlantRegistry.CARROT);
    private final PlantImpl begoniaInstance = new PlantImpl(PlantRegistry.BEGONIA);

    /**
     * Basic Test: adding plants and avoiding object duplicates.
     */
    @Test
    void testEncyclopediaManagement() {
        assertEquals(0, encyclopedia.numberPlants());
        assertTrue(encyclopedia.getPlants().isEmpty());

        encyclopedia.addPlant(carrotInstance);
        assertEquals(1, encyclopedia.numberPlants());
        assertTrue(encyclopedia.getPlants().contains(carrotInstance));

        encyclopedia.addPlant(carrotInstance);
        assertEquals(1, encyclopedia.numberPlants());

        encyclopedia.addPlant(begoniaInstance);
        assertEquals(2, encyclopedia.numberPlants());
    }

    /**
     * Test the getUnlockedEdiblePlants feature.
     */
    @Test
    void testFilters() {
        PlantRegistry.CARROT.lock(); 
        PlantRegistry.BEGONIA.lock();
        encyclopedia.addPlant(carrotInstance);
        encyclopedia.addPlant(begoniaInstance);

        carrotInstance.getType().unlock();

        assertTrue(encyclopedia.getUnlockedEdiblePlants().contains(carrotInstance));

        begoniaInstance.getType().unlock();

        assertFalse(encyclopedia.getUnlockedEdiblePlants().contains(begoniaInstance));
        assertEquals(1, encyclopedia.getNumberUnlockedEdiblePlants());
    }

    /**
     * Test the Unlock All feature.
     */
    @Test
    void testUnlockAll() {
        final PlantImpl onion = new PlantImpl(PlantRegistry.ONION);
        final PlantImpl monstera = new PlantImpl(PlantRegistry.MONSTERA);
        onion.getType().lock();
        monstera.getType().lock();

        encyclopedia.addPlant(onion);
        encyclopedia.addPlant(monstera);

        encyclopedia.unlockAll();

        assertTrue(onion.isDiscovered());
        assertTrue(monstera.isDiscovered());
    }
}
