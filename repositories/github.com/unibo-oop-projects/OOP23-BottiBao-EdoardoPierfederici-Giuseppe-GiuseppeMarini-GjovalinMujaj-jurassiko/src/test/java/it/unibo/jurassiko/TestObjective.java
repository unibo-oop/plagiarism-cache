package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.impl.ConquerContinentsObjective;
import it.unibo.jurassiko.model.objective.impl.ConquerTerritoriesObjective;
import it.unibo.jurassiko.model.objective.impl.DestroyArmyObjective;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player.GameColor;

class TestObjective {

    private static final int NUM_TOTAL_OBJECTIVES = 8;
    private static final int NUM_CONQTERRITORIES = 2;
    private static final int NUM_CONQCONTINENTS = 3;
    private static final int NUM_DESTROYARMY = 3;

    private static final int NUM_TERRITORIES_1 = 12;
    private static final int MIN_DINOS_1 = 0;
    private static final int NUM_TERRITORIES_2 = 9;
    private static final int MIN_DINOS_2 = 3;

    private Set<Objective> objectives;

    @BeforeEach
    void initFactory() {
        this.objectives = new ObjectiveFactoryImpl().createObjectives();
    }

    @Test
    void testObjectiveReader() {
        assertNotNull(objectives);
        assertFalse(objectives.isEmpty());

        // Tests the amount of objectives read per type
        assertEquals(NUM_TOTAL_OBJECTIVES, objectives.size());
        assertEquals(NUM_CONQCONTINENTS,
                objectives.stream().filter(ConquerContinentsObjective.class::isInstance).count());
        assertEquals(NUM_CONQTERRITORIES,
                objectives.stream().filter(ConquerTerritoriesObjective.class::isInstance).count());
        assertEquals(NUM_DESTROYARMY,
                objectives.stream().filter(DestroyArmyObjective.class::isInstance).count());
    }

    @Test
    void testConquerContinents() {
        final Set<String> continents = Set.of("Gondwana Occidentale", "Nord America");
        final String description1 = "Conquista interamente i seguenti continenti: Gondwana Occidentale, Nord America.";
        final String description2 = "Conquista interamente i seguenti continenti: Nord America, Gondwana Occidentale.";

        final var conquerContinentsObjectives = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .map(ConquerContinentsObjective.class::cast)
                .collect(Collectors.toSet());

        // Tests the presence of a sample Set of continents
        assertTrue(conquerContinentsObjectives.stream()
                .anyMatch(o -> o.getContinents().equals(continents)));

        // Tests the description
        final var actualDescription = conquerContinentsObjectives.stream()
                .filter(s -> s.getContinents().equals(continents))
                .findAny()
                .get()
                .getDescription();
        assertTrue(actualDescription.equals(description1) || actualDescription.equals(description2));
    }

    @Test
    void testConquerTerritories() {
        final String description1 = "Conquista 12 territori.";
        final String description2 = "Conquista 9 territori con almeno 3 Dino ciascuno.";

        final var conquerTerritoriesObjectives = objectives.stream()
                .filter(ConquerTerritoriesObjective.class::isInstance)
                .map(ConquerTerritoriesObjective.class::cast)
                .collect(Collectors.toSet());

        // Tests the presence of numTerritories and minDinos correct values
        assertTrue(conquerTerritoriesObjectives.stream()
                .anyMatch(o -> o.getNumTerritories() == NUM_TERRITORIES_1 && o.getMinDinos() == MIN_DINOS_1));
        assertTrue(conquerTerritoriesObjectives.stream()
                .anyMatch(o -> o.getNumTerritories() == NUM_TERRITORIES_2 && o.getMinDinos() == MIN_DINOS_2));

        // Tests the descriptions
        final var actualDescription1 = conquerTerritoriesObjectives.stream()
                .filter(o -> o.getNumTerritories() == NUM_TERRITORIES_1 && o.getMinDinos() == MIN_DINOS_1)
                .findAny()
                .get()
                .getDescription();
        final var actualDescription2 = conquerTerritoriesObjectives.stream()
                .filter(o -> o.getNumTerritories() == NUM_TERRITORIES_2 && o.getMinDinos() == MIN_DINOS_2)
                .findAny()
                .get()
                .getDescription();
        assertEquals(description1, actualDescription1);
        assertEquals(description2, actualDescription2);
    }

    @Test
    void testDestroyArmy() {
        final String description = "Distruggi l'armata di colore BLU. In caso di armata non nemica, conquista 12 territori.";
        final Set<GameColor> armyColors = Set.of(GameColor.valueOf("RED"),
                GameColor.valueOf("BLUE"),
                GameColor.valueOf("GREEN"));

        final var destroyArmyObjectives = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .map(DestroyArmyObjective.class::cast)
                .collect(Collectors.toSet());

        // Tests the presence of the correct army colors
        assertEquals(armyColors, destroyArmyObjectives.stream()
                .map(DestroyArmyObjective::getArmyColor)
                .collect(Collectors.toSet()));

        // Tests the description
        final var actualDescription = destroyArmyObjectives.stream()
                .filter(o -> o.getArmyColor().equals(GameColor.BLUE))
                .findAny()
                .get()
                .getDescription();
        assertEquals(description, actualDescription);
    }

    /**
     * Tests the execution of objective cloning.
     */
    @Test
    void testClone() {
        final var contObjective = objectives.stream()
                .filter(ConquerContinentsObjective.class::isInstance)
                .findAny()
                .map(ConquerContinentsObjective.class::cast)
                .get();

        final var contObjectiveClone = (ConquerContinentsObjective) contObjective.getClone();
        assertEquals(contObjective.getType(), contObjectiveClone.getType());
        assertEquals(contObjective.getContinents(), contObjectiveClone.getContinents());
        assertEquals(contObjective.getDescription(), contObjectiveClone.getDescription());

        final var terrObjective = objectives.stream()
                .filter(ConquerTerritoriesObjective.class::isInstance)
                .findAny()
                .map(ConquerTerritoriesObjective.class::cast)
                .get();

        final var terrObjectiveClone = (ConquerTerritoriesObjective) terrObjective.getClone();
        assertEquals(terrObjective.getType(), terrObjectiveClone.getType());
        assertEquals(terrObjective.getNumTerritories(), terrObjectiveClone.getNumTerritories());
        assertEquals(terrObjective.getMinDinos(), terrObjectiveClone.getMinDinos());
        assertEquals(terrObjective.getDescription(), terrObjectiveClone.getDescription());

        final var armyObjective = objectives.stream()
                .filter(DestroyArmyObjective.class::isInstance)
                .findAny()
                .map(DestroyArmyObjective.class::cast)
                .get();

        final var armyObjectiveClone = (DestroyArmyObjective) armyObjective.getClone();
        assertEquals(armyObjective.getType(), armyObjectiveClone.getType());
        assertEquals(armyObjective.getArmyColor(), armyObjectiveClone.getArmyColor());
        assertEquals(armyObjective.getDescription(), armyObjectiveClone.getDescription());
    }

}
