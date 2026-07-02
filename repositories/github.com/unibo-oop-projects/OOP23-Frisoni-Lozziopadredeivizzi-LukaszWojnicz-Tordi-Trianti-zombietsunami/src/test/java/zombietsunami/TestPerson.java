package zombietsunami;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import zombietsunami.model.zombiemodel.api.Zombie;
import zombietsunami.model.zombiemodel.impl.ZombieImpl;
import zombietsunami.model.personmodel.api.Person;
import zombietsunami.model.personmodel.impl.PersonImpl;

/**
 * Test class for Person.
 * 
 * This class test the increase of the Person and if the Person is set properly.
 */
class TestPerson {
    private final Zombie zombie = new ZombieImpl();
    private final Person person = new PersonImpl();
    private static final int NUM_VALUE = 15;

    /**
     * Checks if the increase has been dealt correctly to the Zombie.
     */
    @Test
    void checkIncrease() {
        zombie.increaseStrength();
        assertEquals(zombie.getStrength(), 2);
    }

    /**
     * Checks if the Person is set properly.
     */
    @Test
    void testCoordinates() {
        person.setX(NUM_VALUE);
        assertEquals(NUM_VALUE, person.getX());
        person.setY(NUM_VALUE);
        assertEquals(NUM_VALUE, person.getY());
    }
}
