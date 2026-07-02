package person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.people.Person;
import model.people.PersonImpl;
import model.people.Status;
import model.virus.Virus;
import model.virus.VirusFactory;
import model.virus.VirusFactoryImpl;

public class PersonTest {

    @Test
    public void testInfect() {
        final Person p = new PersonImpl();
        assertEquals(Status.SUSCEPTIBLE, p.getStatus());
        assertFalse(p.getVirus().isPresent());

        final VirusFactory vf = new VirusFactoryImpl(1, 2, 8, 10, 2, 10);
        final Virus v = vf.createVirus();
        p.infect(v);
        assertEquals(Status.INFECTED, p.getStatus());
        assertThrows(IllegalStateException.class, () -> p.infect(v));
        p.setStatus(Status.ILL);
        assertThrows(IllegalStateException.class, () -> p.infect(v));
        p.setStatus(Status.RECOVERED);
        assertThrows(IllegalStateException.class, () -> p.infect(v));
    }

    @Test
    public void testTryToInfect() {
        final Person p = new PersonImpl();
        assertFalse(p.tryToInfect());

        final VirusFactory vf = new VirusFactoryImpl(1, 2, 50, 10, 2, 10);
        p.infect(vf.createVirus());
        for (int i = 0; i < 10; i++) {
            System.out.println(p.tryToInfect()); //Print some values to test randomness
        }

        final Person p1 = new PersonImpl();
        assertFalse(p1.tryToInfect());

        final VirusFactory infectiveVirus = new VirusFactoryImpl(1, 2, 100, 10, 2, 10);
        p1.infect(infectiveVirus.createVirus());
        for (int i = 0; i < 10; i++) {
            assertTrue(p1.tryToInfect());
        }
    }

}
