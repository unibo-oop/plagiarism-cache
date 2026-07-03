package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Doctor;
import model.DoctorImpl;
import model.Patient;
import model.PatientImpl;

public class DoctorImplTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private Patient p;
    private Doctor d;
    private Doctor d2;
    private List<Patient> l = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
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
         d2 = new DoctorImpl.Builder()
                 .name("Carlo")
                 .surname("Conti")
                 .age(34)
                 .codfis("fdffg")
                 .sex("Other")
                 .specializ("Oncologist")
                 .build();
    }

    

    @Test
    public final void testGetName() {
        assertEquals("Carlo", d.getName());
    }

    @Test
    public final void testGetSurname() {
        assertEquals("Conti", d.getSurname());
    }

    @Test
    public final void testGetCodfis() {
        assertEquals("fdfg", d.getCodfis());
    }

    @Test
    public final void testGetSex() {
        assertEquals("Other", d.getSex());
    }

    @Test
    public final void testGetSpecialization() {
        assertEquals("Oncologist", d.getSpecialization());
    }

    @Test
    public final void testGetAge() {
        assertEquals(34, d.getAge());
    }

    @Test
    public final void testGetAssignedPatients() {
        /*exception.expect(IllegalStateException.class);
        exception.expectMessage("No assigned Patients");*/
        l = d.getAssignedPatients();
        List<Patient> l2 = new ArrayList<>();
        assertEquals(l2, l);
        d.assignPatient(p);
        l = d.getAssignedPatients();
        assertTrue(l.contains(p));
    }

    @Test
    public final void testAssignPatient() {
        d.assignPatient(p);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient already assigned");
        d.assignPatient(p);
        l = d.getAssignedPatients();
        assertTrue(l.contains(p));
    }

    @Test
    public final void testDismissPatient() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient not assigned");
        d.dismissPatient(p);
        d.assignPatient(p);
        l = d.getAssignedPatients();
        assertTrue(l.contains(p));
    }
    @Test
    public final void testSurgery() {
        assertFalse(d.isInSurgery());
        d.setSurgery(true);
        assertTrue(d.isInSurgery());
    }

    @Test
    public final void testEqualsObject() {
        assertTrue(d.equals(d));
        assertFalse(d.equals(d2));
    }
    @Test
    public final void testToString() {
        assertEquals("Carlo Conti: Oncologist, Code: fdfg", d.toString());
    }

}
