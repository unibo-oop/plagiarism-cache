package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

import it.unibo.jurassiko.model.borders.api.Border;
import it.unibo.jurassiko.model.borders.impl.BorderImpl;
import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.impl.OceanFactoryImpl;
import it.unibo.jurassiko.model.territory.impl.TerritoryFactoryImpl;

class TestBorder {

    private Border border;
    private Set<Territory> territories;
    private Set<Ocean> oceans;

    private static final String TERRITORY_NAME_1 = "Madagascar";
    private static final String OCEAN_NAME_1 = "Oceano Tetide";
    private static final String OCEAN_NAME_2 = "Oceano Atlantico";
    private static final Set<String> NEIGHBOUR_NAMES = Set.of("India", "Antartica", "Australia");
    private static final Set<String> NEIGHBOUR_NAME_2 = Set.of("Tibet", "Indonesia", "Nord Africa",
            "Congo", "Sud Africa", "Australia", "India", "Madagascar", "Antartica");

    @BeforeEach
    void init() {
        this.border = new BorderImpl();
        this.territories = new TerritoryFactoryImpl().createTerritories();
        this.oceans = new OceanFactoryImpl().createOceans();
    }

    @Test
    void testBorder() {
        final Territory terr1 = this.territories.stream()
                .filter(s -> TERRITORY_NAME_1.equals(s.getName()))
                .findFirst()
                .get();
        final Ocean ocean1 = this.oceans.stream()
                .filter(o -> OCEAN_NAME_1.equals(o.getName()))
                .findFirst()
                .get();
        assertEquals(NEIGHBOUR_NAMES, this.border.getTerritoriesBorder(terr1, ocean1));
        final Ocean ocean2 = this.oceans.stream()
                .filter(o -> OCEAN_NAME_2.equals(o.getName()))
                .findFirst()
                .get();
        assertNotEquals(NEIGHBOUR_NAMES, this.border.getTerritoriesBorder(terr1, ocean2));
        assertEquals(NEIGHBOUR_NAME_2, this.border.getTerritoriesBorder(terr1, ocean2));
    }
}
