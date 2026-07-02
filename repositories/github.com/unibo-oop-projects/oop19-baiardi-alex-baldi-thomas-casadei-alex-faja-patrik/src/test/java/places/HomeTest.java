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
import model.places.Home;
import model.places.HomeImpl;

/**
 * Home testing class.
 */
public class HomeTest {

    private final Home home = new HomeImpl(0);

    /**
     * Tests the entry method.
     */
    @Test
    public void testEntry() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> this.home.enter(p, 1));
        people.forEach(p -> assertTrue(this.home.checkPresence(p)));
        final Person person = new PersonImpl();
        this.home.enter(person, 2);
        assertTrue(this.home.checkPresence(person));
        assertThrows(IllegalStateException.class, () -> this.home.enter(person, 4));
    }

    /**
     * Tests the exit method.
     */
    @Test
    public void testExit() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> this.home.enter(p, 1));
        this.home.exit(1);
        people.forEach(p -> assertFalse(this.home.checkPresence(p)));
        final Person person = new PersonImpl();
        this.home.enter(person, 2);
        this.home.exit(2);
        assertFalse(this.home.checkPresence(person));
        assertEquals(List.of(), this.home.exit(2));
    }

    /**
     * Tests the checkPresence method.
     */
    @Test
    public void testCheckPresence() {
        final Person person = new PersonImpl();
        this.home.enter(person, 1);
        assertTrue(this.home.checkPresence(person));
        this.home.exit(1);
        assertFalse(this.home.checkPresence(person));
    }

}
