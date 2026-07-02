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
import model.places.MeetingPlace;
import model.places.MeetingPlaceImpl;

/**
 * Meeting places testing class.
 */
public class MeetingPlacesTest {

    private final MeetingPlace meetingPlace = new MeetingPlaceImpl();

    /**
     * Tests the entry method.
     */
    @Test
    public void testEntry() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> this.meetingPlace.enter(p, 1));
        people.forEach(p -> assertTrue(this.meetingPlace.checkPresence(p)));
        final Person person = new PersonImpl();
        this.meetingPlace.enter(person, 2);
        assertTrue(this.meetingPlace.checkPresence(person));
        assertThrows(IllegalStateException.class, () -> this.meetingPlace.enter(person, 4));
    }

    /**
     * Tests the exit method.
     */
    @Test
    public void testExit() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> this.meetingPlace.enter(p, 1));
        this.meetingPlace.exit(1);
        people.forEach(p -> assertFalse(this.meetingPlace.checkPresence(p)));
        final Person person = new PersonImpl();
        this.meetingPlace.enter(person, 2);
        this.meetingPlace.exit(2);
        assertFalse(this.meetingPlace.checkPresence(person));
        assertEquals(List.of(), this.meetingPlace.exit(2));
    }

    /**
     * Tests the exitSinglePerson method.
     */
    @Test
    public void testExitSinglePerson() {
        final int nPeople = 20;
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        final Person person = new PersonImpl();
        people.forEach(p -> this.meetingPlace.enter(p, 1));
        people.forEach(p -> this.meetingPlace.exitSinglePerson(p));
        people.forEach(p -> assertFalse(this.meetingPlace.checkPresence(p)));
        assertThrows(IllegalStateException.class, () -> this.meetingPlace.exitSinglePerson(person));
        this.meetingPlace.enter(person, 1);
        this.meetingPlace.exitSinglePerson(person);
        assertFalse(this.meetingPlace.checkPresence(person));
    }

    /**
     * Tests the checkPresence method.
     */
    @Test
    public void testCheckPresence() {
        final Person person = new PersonImpl();
        this.meetingPlace.enter(person, 1);
        assertTrue(this.meetingPlace.checkPresence(person));
        this.meetingPlace.exit(1);
        assertFalse(this.meetingPlace.checkPresence(person));
    }

    /**
     * Tests the isOpen method.
     */
    @Test
    public void testIsOpen() {
        assertTrue(this.meetingPlace.isOpen());
        this.meetingPlace.close();
        assertFalse(this.meetingPlace.isOpen());
        this.meetingPlace.open();
        assertTrue(this.meetingPlace.isOpen());
    }

    /**
     * Tests the isFull method.
     */
    @Test
    public void testIsFull() {
        final int nPeople = 19;
        final Person person = new PersonImpl();
        final List<Person> people = Stream.generate(() -> new PersonImpl()).limit(nPeople).collect(Collectors.toList());
        people.forEach(p -> this.meetingPlace.enter(p, 1));
        this.meetingPlace.enter(person, 2);
        assertTrue(this.meetingPlace.isFull());
        this.meetingPlace.exit(1);
        assertFalse(this.meetingPlace.isFull());
    }

}
