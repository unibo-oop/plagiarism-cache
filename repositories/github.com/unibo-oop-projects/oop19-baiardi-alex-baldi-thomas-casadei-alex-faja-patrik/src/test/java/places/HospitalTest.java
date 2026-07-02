package places;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import model.people.Person;
import model.people.PersonImpl;
import model.people.Status;
import model.places.Hospital;
import model.places.HospitalImpl;
import model.virus.Virus;
import model.virus.VirusFactoryImpl;

/**
 * Hospital testing class.
 */
public class HospitalTest {

    private final Hospital hospital = new HospitalImpl();
    private final Virus virus = new VirusFactoryImpl(1, 2, 8, 10, 2, 10).createVirus();

    /**
     * Tests the entry method.
     */
    @Test
    public void testEntry() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        final Person person = new PersonImpl();
        people.forEach(p -> p.infect(virus));
        people.forEach(p -> this.hospital.enter(p, 1));
        assertTrue(this.hospital.isAnyoneInHospital());
        assertThrows(IllegalArgumentException.class, () -> this.hospital.enter(person, 2));
        person.infect(virus);
        this.hospital.enter(person, 2);
        assertThrows(IllegalStateException.class, () -> this.hospital.enter(person, 3));
    }

    /**
     * Tests the exitWithOutcome method.
     */
    @Test
    public void testExitWithOutcome() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> p.infect(virus));
        people.forEach(p -> this.hospital.enter(p, 1));
        this.hospital.exitWithOutcome(1).getRecoveredPeople()
                .forEach(p -> assertEquals(Status.SUSCEPTIBLE, p.getStatus()));
    }

    /**
     * Tests the isAnyoneInHospital method.
     */
    @Test
    public void testIsAnyoneInHospital() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> p.infect(virus));
        assertFalse(this.hospital.isAnyoneInHospital());
        people.forEach(p -> this.hospital.enter(p, 1));
        assertTrue(this.hospital.isAnyoneInHospital());
    }
}
