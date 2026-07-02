package pvz;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pvz.model.plants.api.Plant;
import pvz.model.plants.impl.PlantFactoryImpl;
import pvz.utilities.PlantType;
import pvz.utilities.Position;

/**
 * Unit tests for the {@link PlantFactoryImpl} class.
 * Ensures that plants are correctly created and initialized.
 */
class PlantFactoryTest {

    private PlantFactoryImpl factory;
    private Position somePos;

    /**
     * Initializes the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        factory = new PlantFactoryImpl();
        somePos = new Position(4, 2);
    }

    /**
     * Tests that the Peashooter is created with correct initial values.
     */
    @Test
     void testCreatePeashooterBasics() {
        final Plant p = factory.createPeashooter(somePos);

        assertEquals(somePos, p.getPosition());

        assertEquals(PlantType.PEASHOOTER, p.mapToEntityType());

        assertEquals(PlantType.PEASHOOTER.getLife(), p.getLife());
    }

    /**
     * Tests that the Sunflower is created with correct initial values.
     */
    @Test
     void testCreateSunflowerBasics() {
        final Plant p = factory.createSunflower(somePos);

        assertEquals(somePos, p.getPosition());
        assertEquals(PlantType.SUNFLOWER, p.mapToEntityType());
        assertEquals(PlantType.SUNFLOWER.getLife(), p.getLife());
    }

    /**
     * Tests that the Wallnut is created with correct initial values.
     */
    @Test
     void testCreateWallnutBasics() {
       final Plant p = factory.createWallnut(somePos);

        assertEquals(somePos, p.getPosition());
        assertEquals(PlantType.WALLNUT, p.mapToEntityType());
        assertEquals(PlantType.WALLNUT.getLife(), p.getLife());
    }

    /**
     * Tests that passing a null position throws a {@link NullPointerException}.
     */
    @Test
     void testFactoryNullPositionThrows() {
        assertThrows(NullPointerException.class, () -> factory.createPeashooter(null));
        assertThrows(NullPointerException.class, () -> factory.createSunflower(null));
        assertThrows(NullPointerException.class, () -> factory.createWallnut(null));
    }

    /**
     * Tests that each call to the factory returns a distinct {@link Plant} instance.
     */
    @Test
     void testDistinctInstances() {
        final Plant p1 = factory.createPeashooter(somePos);
        final Plant p2 = factory.createPeashooter(somePos);
        assertNotSame(p1, p2);

        final Plant s1 = factory.createSunflower(somePos);
        final Plant s2 = factory.createSunflower(somePos);
        assertNotSame(s1, s2);

        final Plant w1 = factory.createWallnut(somePos);
        final Plant w2 = factory.createWallnut(somePos);
        assertNotSame(w1, w2);
    }
}

