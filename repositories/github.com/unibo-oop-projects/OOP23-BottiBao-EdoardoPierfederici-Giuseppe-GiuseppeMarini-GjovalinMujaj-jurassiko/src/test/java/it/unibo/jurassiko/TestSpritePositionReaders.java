package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.reader.impl.SpritePositionsReader;

class TestSpritePositionReaders {

    private static final String TERRITORY_PATH = "config/spritepositions/territorypositions.json";
    private static final String OCEAN_PATH = "config/spritepositions/oceanpositions.json";

    private static final int NUM_TERRITORIES = 21;
    private static final int NUM_OCEANS = 3;
    private static final String SAMPLE_TERRITORY = "Sud America";
    private static final double TERRITORY_X = 16.5;
    private static final double TERRITORY_Y = 80.0;
    private static final String SAMPLE_OCEAN = "Oceano Tetide";
    private static final double OCEAN_X = 40.0;
    private static final double OCEAN_Y = 32.0;

    private Map<String, Pair<Double, Double>> territoryPositions;
    private Map<String, Pair<Double, Double>> oceanPositions;

    @BeforeEach
    void init() {
        this.territoryPositions = new SpritePositionsReader().readFileData(TERRITORY_PATH);
        this.oceanPositions = new SpritePositionsReader().readFileData(OCEAN_PATH);
    }

    @Test
    void testTerritoryPositionReader() {
        assertNotNull(territoryPositions);
        assertFalse(territoryPositions.isEmpty());
        assertEquals(NUM_TERRITORIES, territoryPositions.size());

        // Checks that all coordinates are a valid percentage value
        assertTrue(territoryPositions.values().stream().allMatch(t -> isValid(t.x()) && isValid(t.y())));

        // Checks the coordinates of a sample territory
        assertTrue(territoryPositions.keySet().contains(SAMPLE_TERRITORY));
        final var coordinates = new Pair<>(TERRITORY_X, TERRITORY_Y);
        assertEquals(coordinates, territoryPositions.get(SAMPLE_TERRITORY));
    }

    @Test
    void testOceanPositionReader() {
        assertNotNull(oceanPositions);
        assertFalse(oceanPositions.isEmpty());
        assertEquals(NUM_OCEANS, oceanPositions.size());

        // Checks that all coordinates are a valid percentage value
        assertTrue(oceanPositions.values().stream().allMatch(t -> isValid(t.x()) && isValid(t.y())));

        // Checks the coordinates of a sample ocean
        assertTrue(oceanPositions.keySet().contains(SAMPLE_OCEAN));
        final var coordinates = new Pair<>(OCEAN_X, OCEAN_Y);
        assertEquals(coordinates, oceanPositions.get(SAMPLE_OCEAN));
    }

    /**
     * Checks if the value is a valid percentage.
     * 
     * @param value the number to check
     * @return true is the value is between 0.0 and 100.0, false otherwise
     */
    private boolean isValid(final double value) {
        return value >= 0.0 && value <= 100.0;
    }

}
