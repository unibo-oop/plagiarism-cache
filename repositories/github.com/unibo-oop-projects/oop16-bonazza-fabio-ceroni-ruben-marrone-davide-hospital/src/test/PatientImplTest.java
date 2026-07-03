package test;

import static org.junit.Assert.*;

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
/**
 * 
 *
 *
 */
public class PatientImplTest {
    /**
     * 
     */
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private Patient p;
    private Doctor d;
    private Ward w;
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
    public final void testGetName() {
        assertEquals("Mario", p.getName());
    }

    @Test
    public final void testGetSurname() {
        assertEquals("Rossi", p.getSurname());
    }

    @Test
    public final void testGetCodfis() {
        assertEquals("MRARSS137492789K", p.getCodfis());
    }

    @Test
    public final void testGetSex() {
        assertEquals("Male", p.getSex());
    }

    @Test
    public final void testGetDisease() {
        assertEquals("mal di pancia", p.getDisease());
    }

    @Test
    public final void testGetLog() {
        assertEquals("Log assente", p.getLog());
    }

    @Test
    public final void testGetAge() {
        assertEquals(10, p.getAge());
    }

    @Test
    public final void testGetPriorityName() {
        assertEquals("High", p.getPriorityName());
    }

    @Test
    public final void testGetPriorityLevel() {
        assertEquals(2, p.getPriorityLevel());
    }
    
    @Test
    public final void testsetOperated() {
        p.setOperated(true);
        assertTrue(p.isBeingOperated());
    }
    @Test
    public final void testIsBeingOperated() {
        assertFalse(p.isBeingOperated());
    }

    @Test
    public final void testUpdateLog() {
        String log = "test";
        p.updateLog(log);
        assertEquals(log, p.getLog());
        p.updateLog(log);
        assertEquals(log + '\n'+ log, p.getLog());
    }

    @Test
    public final void testSetDoctor() {
        p.setDoctor(d);
        assertEquals(d, p.getDoctor());
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Doctor already assigned");
        p.setDoctor(d);
    }
    @Test
    public final void testGetDoctor() {
        p.setDoctor(d);
        assertEquals(d, p.getDoctor());
    }
    @Test
    public final void testSetWard() {
        p.setWard(w);
        assertEquals(w, p.getWard());
    }
    @Test
    public final void testToString() {
        assertEquals("Mario Rossi, Code: MRARSS137492789K", p.toString());
    }

    @Test
    public final void testEqualsObject() {
        assertEquals(true, p.equals(p));
    }

}
