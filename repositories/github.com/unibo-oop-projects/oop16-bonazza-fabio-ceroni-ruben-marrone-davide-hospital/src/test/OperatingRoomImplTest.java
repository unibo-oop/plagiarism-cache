package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Doctor;
import model.DoctorImpl;
import model.Hospital;
import model.Model;
import model.OperatingRoom;
import model.OperatingRoomImpl;
import model.Patient;
import model.PatientImpl;
import model.Ward;
import model.WardImpl;

/**
 * Test for OperatingRoomImpl.
 */
public class OperatingRoomImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Model h;
    private Ward w;
    private OperatingRoom o;
    private Patient p;
    private Patient p2;
    private Doctor d;

    @Before
    public void setUp() throws Exception {
        h = new Hospital();
        w = new WardImpl("Medicine", 100);
        o = new OperatingRoomImpl("Sala 1");
        p = new PatientImpl.Builder()
                .name("Mario")
                .surname("Rossi")
                .codfis("1234")
                .sex("Male")
                .disease("febbre")
                .age(20)
                .priority("Medium")
                .build(); 
        p2 = new PatientImpl.Builder()
            .name("Luca")
            .surname("Verdi")
            .codfis("4321")
            .sex("Male")
            .disease("Calcoli")
            .age(30)
            .priority("High")
            .build(); 
        d = new DoctorImpl.Builder()
                 .name("Carla")
                 .surname("Bianchi")
                 .age(40)
                 .codfis("5678")
                 .sex("Female")
                 .specializ("Dermatologist")
                 .build();

         h.addWard(w);
         h.addOperatingRoom(o);
         h.addDoctor(d);
         h.addPatient(p);
         h.addPatient(p2);
         h.treatPatient(p, d, w);
         h.treatPatient(p2, d, w);
    }

    @Test
    public final void testOperatingRoomImpl() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Name cannot be empty");
        o = new OperatingRoomImpl("");
    }

    @Test
    public final void testAddPatient() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient is already being operated.");
        p.setOperated(true);
        o.addPatient(p);

        exception.expect(IllegalStateException.class);
        exception.expectMessage("Room is not empty.");
        o.addPatient(p2);
        o.addPatient(p);
        o.removePatient();

        o.addPatient(p);
        assertEquals(p, o.getPatient());
        assertEquals(d, o.getDoctor());
        assertTrue(p.isBeingOperated());
    }

    @Test
    public final void testRemovePatient() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Room is empty.");
        o.removePatient();

        o.addPatient(p);
        o.removePatient();
        assertEquals(null, o.getPatient());
        assertEquals(null, o.getDoctor());
        assertFalse(p.isBeingOperated());
    }

    @Test
    public final void testGetName() {
        assertEquals("Sala 1", o.getName());
    }

    @Test
    public final void testGetPatient() {
        o.addPatient(p);
        assertEquals(p, o.getPatient());
    }

    @Test
    public final void testGetDoctor() {
        o.addPatient(p);
        assertEquals(d, o.getDoctor());
    }

    @Test
    public final void testToString() {
        assertEquals("Sala 1", o.toString());
    }
}
