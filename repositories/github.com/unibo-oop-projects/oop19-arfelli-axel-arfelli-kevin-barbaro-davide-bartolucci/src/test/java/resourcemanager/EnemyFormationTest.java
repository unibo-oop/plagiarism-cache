package resourcemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import formations.EnemyFormation;
import formations.EnemyFormationGenerator;
import formations.EnemyFormationGeneratorImpl;
import formations.EnemyType;
import javafx.util.Pair;

/**
 * This is a JUnit test class used for testing the EnemyFormationImpl and
 * EnemyFormationGeneratorImpl classes.
 */
public class EnemyFormationTest {
    private static EnemyFormationGenerator generator;
    private static final Integer DEFAUL_WIDTH = 1280;
    private static final Integer DEFAULT_HEIGHT = 720;
    private static final Integer ENEMY_NUMBER_1 = 20;
    private static final Integer ENEMY_NUMBER_2 = 10;

    @org.junit.jupiter.api.BeforeAll
    public static void init() {
        generator = new EnemyFormationGeneratorImpl(DEFAUL_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Checks if the size of the Enemy Formation is equal
     * to the number given as argument to the getEnemyFormation method.
     */
    @org.junit.jupiter.api.Test
    public void testFormation() {
        EnemyFormation formation = generator.getEnemyFormation(ENEMY_NUMBER_1);
        assertEquals(formation.getFormationMap().size(), ENEMY_NUMBER_1);
        formation = generator.getEnemyFormation(ENEMY_NUMBER_2);
        assertEquals(formation.getFormationMap().size(), ENEMY_NUMBER_2);
        System.out.println("Formation: \n" + formation.toString());
    }

    /**
     * Checks if all coordinates on the EnemyFormation map are smaller than the VirtualMap size.
     */
    @org.junit.jupiter.api.Test
    public void testCoordinates() {
        final EnemyFormation formation = generator.getEnemyFormation(ENEMY_NUMBER_1);
        for (final Pair<Integer, Integer> p : formation.getFormationMap().keySet()) {
            assertTrue(p.getKey() <= DEFAUL_WIDTH);
            assertTrue(p.getValue() <= DEFAULT_HEIGHT);
        }
    }

    /**
     * Checks if the get getBossWave method works properly.
     */
    @org.junit.jupiter.api.Test
    public void testBoss() {
        final EnemyFormation formation = generator.getBossWave();
        assertTrue(formation.getFormationMap().containsValue(EnemyType.BOSS));
        assertEquals(formation.getFormationMap().size(), 1);
        System.out.println("Boss: \n" + formation.toString());
    }
}
