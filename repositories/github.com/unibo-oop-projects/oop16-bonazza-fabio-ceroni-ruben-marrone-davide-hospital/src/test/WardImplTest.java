package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Doctor;
import model.DoctorImpl;
import model.Patient;
import model.PatientImpl;
import model.Ward;
import model.WardImpl;

public class WardImplTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    Ward w;
    Patient p;
    Doctor d;
    private  Map<Integer, Patient> m = new HashMap<>();
    private List<Patient> l = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        w = new WardImpl("Medicine",100);
        
        p = new PatientImpl.Builder()
                .name("Mario")
                .surname("Rossi")
                .codfis("MRARSS137492789K")
                .sex("Male")
                .disease("mal di pancia")
                .age(10)
                .priority("High")
                .build(); 
         d = new DoctorImpl.Builder()
                 .name("Carlo")
                 .surname("Conti")
                 .age(34)
                 .codfis("fdfg")
                 .sex("Other")
                 .specializ("Oncologist")
                 .build();
    }
    

    @Test
    public final void testWardImpl() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid Input");
        w = new WardImpl("Medicine",0);
    }

    @Test
    public final void testGetOccupiedRooms() {
        w.enterPatient(p);
        m = w.getOccupiedRooms();
        assertEquals(Optional.of(p), m.values().stream().findFirst());
    }

    @Test
    public final void testGetName() {
        assertEquals("Medicine", w.getName());
    }

    @Test
    public final void testGetNumRooms() {
        assertEquals(100, w.getNumRooms());
    }

    @Test
    public final void testEnterPatient() {
        w.enterPatient(p);
        l = w.getPatients();
        assertEquals(p, l.get(0));
    }

    @Test
    public final void testRemovePatient() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient isn't present");
        w.removePatient(p);
        w.enterPatient(p);
        w.removePatient(p);
        m = w.getOccupiedRooms();
        assertEquals(Optional.empty(), m.values().stream().findFirst());
    }

    @Test
    public final void testGetPatients() {
        w.enterPatient(p);
        l = w.getPatients();
        assertEquals(p, l.get(0));
    }

    @Test
    public final void testEqualsObject() {
        assertEquals(true, w.equals(w));
    }
    @Test
    public final void testToString() {
        assertEquals("Medicine, Occupied Rooms : 0/100", w.toString());
    }

}
