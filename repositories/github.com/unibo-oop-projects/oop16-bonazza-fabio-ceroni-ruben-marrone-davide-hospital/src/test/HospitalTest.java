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
import model.Hospital;
import model.OperatingRoom;
import model.OperatingRoomImpl;
import model.Patient;
import model.PatientImpl;
import model.Ward;
import model.WardImpl;


/**
 * 
 * class to test the model.
 *
 */
public class HospitalTest {
    /**
     * 
     */
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Hospital hospital;

    private List<Patient> l = new ArrayList<Patient>();
    private List<Doctor> ds = new ArrayList<>();
    private List<Ward> ws = new ArrayList<>();
    private List<OperatingRoom> os = new ArrayList<>();
    private Patient p;
    private Patient p2;
    private Patient p3;
    private Doctor d;
    private Doctor d2;
    private Ward w;
    private Ward w2;
    private Ward w3;
    private OperatingRoom o;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void setUp() {
        hospital = new Hospital();
        w = new WardImpl("Medicine", 3);
        w2 = new WardImpl("Ginecology", 3);
        p = new PatientImpl.Builder()
                .name("Mario")
                .surname("Rossi")
                .codfis("MRARSS137492789K")
                .sex("Male")
                .disease("mal di pancia")
                .age(10)
                .priority("High")
                .build(); 
        p2 = new PatientImpl.Builder()
                .name("Mario")
                .surname("Verdi")
                .codfis("MRARSS137gfd492789K")
                .sex("Female")
                .disease("mal di pancino")
                .age(10)
                .priority("Low")
                .build();
        p3 = new PatientImpl.Builder()
                .name("Mario")
                .surname("Verdi")
                .codfis("MRARSS137gfdsfd492789K")
                .sex("Female")
                .disease("mal di pancino")
                .age(10)
                .priority("Low")
                .build();
         d = new DoctorImpl.Builder()
                 .name("Carlo")
                 .surname("Conti")
                 .age(3)
                 .codfis("fdfg")
                 .sex("Other")
                 .specializ("Oncologist")
                 .build();
         d2 = new DoctorImpl.Builder()
                 .name("Gerry")
                 .surname("Scotti")
                 .age(3)
                 .codfis("fdfgdf33g")
                 .sex("Male")
                 .specializ("Oncologist")
                 .build();
         hospital.addDoctor(d);
         hospital.addPatient(p);
         hospital.addWard(w);
         o = new OperatingRoomImpl("test");
    }
    /**
     * 
     */
    @Test
    public void testRobe() {
        w3 = new WardImpl("Ortopedics", 2);
        hospital.addWard(w3);
        hospital.addPatient(p2);
        hospital.addPatient(p3);
        hospital.treatPatient(p, d, w3);
        hospital.treatPatient(p2, d, w3);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Ward is full");
        hospital.treatPatient(p3, d, w3);
    }
    /**
     * 
     */
    @Test
    public void testAddPatient() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient already exists inside of the Waiting Room");
        hospital.addPatient(p);
        hospital.treatPatient(p, d, w);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient already exists inside of the Hospital");
        hospital.addPatient(p);
        l = hospital.getWaitingPatients();
        assertEquals(p, l.get(0));
    }
    /**
     * 
     */
    @Test
    public void testRemovePatient() {
        hospital.addPatient(p2);
        hospital.removePatient(p2);
        l = hospital.getWaitingPatients();
        assertFalse(l.contains(p2));
        hospital.treatPatient(p, d, w);
        hospital.addOperatingRoom(o);
        hospital.startSurgery(p, o);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient is still in surgery");
        hospital.removePatient(p);
        hospital.endSurgery(o);
        hospital.removePatient(p);
        ds = hospital.getAllDoctors();
        ws = hospital.getAllWards();
        assertEquals(null, ds.get(0).getAssignedPatients());
        assertTrue(ws.get(0).getPatients().isEmpty());
    }
    /**
     * 
     */
    @Test
    public void testAddDoctor() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Doctor already exists");
        hospital.addDoctor(d);
        hospital.addDoctor(d2);
        ds = hospital.getAllDoctors();
        assertTrue(ds.contains(d2));
    }
    /**
     * 
     */
    @Test
    public void testRemoveDoctor() {
        hospital.treatPatient(p, d, w);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Doctor still has assigned patients");
        hospital.removeDoctor(d);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Doctor does not exist");
        hospital.removeDoctor(d2);
        hospital.removePatient(p);
        hospital.removeDoctor(d);
        ds = hospital.getAllDoctors();
        assertFalse(ds.contains(d));
    }
    /**
     *     @Test
     */
    public void testAddWard() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Ward already exists");
        hospital.addWard(w);
        hospital.addWard(w2);
        ws = hospital.getAllWards();
        assertTrue(ws.contains(w2));
    }
    /**
     * 
     */
    @Test
    public void testRemoveWard() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Ward does not exist");
        hospital.removeWard(w2);
        hospital.removeWard(w);
        ws = hospital.getAllWards();
        assertFalse(ws.contains(w2));
    }
    /**
     * 
     */
    @Test
    public void testTreatPatient() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Illegal Ward");
        hospital.treatPatient(p, d, w2);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Illegal Patient");
        hospital.treatPatient(p2, d, w);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Illegal Doctor");
        hospital.treatPatient(p, d2, w);
        hospital.treatPatient(p, d, w);
        l = hospital.getWaitingPatients();
        assertFalse(l.contains(p));
        l = hospital.getAllPatients();
        l.forEach(pt -> {
            if (pt.equals(p)) {
                assertEquals(d, pt.getDoctor());
            }
        });
        ws = hospital.getAllWards();
        ws.forEach(wd -> {
            if (wd.equals(w)) {
            assertTrue(wd.getPatients().contains(p));
            }
        });
        ds = hospital.getAllDoctors();
        ds.forEach(dc -> {
            if (dc.equals(d)) {
            assertTrue(dc.getAssignedPatients().contains(p));
            }
        });
    }
    /**
     * 
     */
    @Test
    public void testGetAllPatients() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No Patients in the Hospital");
        hospital.getAllPatients();
        hospital.treatPatient(p, d, w);
        hospital.addWard(w2);
        hospital.treatPatient(p2, d, w2);
        l = hospital.getAllPatients();
        assertTrue(l.contains(p));
        assertTrue(l.contains(p2));
    }
    /**
     * 
     */
    @Test
    public void testGetWaitingPatients() {
        hospital.removePatient(p);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No Patients in the Waiting Room");
        l = hospital.getWaitingPatients();
        hospital.addPatient(p);
        hospital.addPatient(p2);
        assertTrue(l.get(0).getPriorityLevel() >= l.get(1).getPriorityLevel());
        assertTrue(l.contains(p));
    }
    /**
     * 
     */
    @Test
    public void testGetAllDoctors() {
        hospital.removeDoctor(d);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No Doctors in the Hospital");
        hospital.getAllDoctors();
        hospital.addDoctor(d);
        ds = hospital.getAllDoctors();
        assertTrue(ds.contains(d));
    }
    /**
     * 
     */
    @Test
    public void testGetAllWards() {
        hospital.removeWard(w);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("No Wards in the Hospital");
        hospital.getAllWards();
        hospital.addWard(w);
        ws = hospital.getAllWards();
        assertTrue(ws.contains(w));
    }
    /**
     * 
     */
    @Test
    public void testAddOperatingRoom() {
        hospital.addOperatingRoom(o);
        os = hospital.getAllOperatingRooms();
        assertTrue(os.contains(o));
        exception.expect(IllegalStateException.class);
        exception.expectMessage("OperatingRoom already exists");
        hospital.addOperatingRoom(o);
    }
    /**
     * 
     */
    @Test
    public void testRemoveOperatingRoom() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("OperatingRoom does not exist");
        hospital.removeOperatingRoom(o);
        hospital.addOperatingRoom(o);
        hospital.removeOperatingRoom(o);
        os = hospital.getAllOperatingRooms();
        assertFalse(os.contains(o));
    }
    /**
     * 
     */
    @Test
    public void testStartSurgery() {
        hospital.treatPatient(p, d, w);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("OperatingRoom does not exist");
        hospital.startSurgery(p, o);
        hospital.addOperatingRoom(o);
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient does not exist");
        hospital.startSurgery(p2, o);
        os = hospital.getAllOperatingRooms();
        assertEquals(p, os.get(0).getPatient());
        assertEquals(d, os.get(0).getDoctor());
        assertTrue(p.isBeingOperated());
    }
    /**
     * 
     */
    @Test
    public void testEndSurgery() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("OperatingRoom does not exist");
        hospital.endSurgery(o);
        hospital.addOperatingRoom(o);
        hospital.treatPatient(p, d, w);
        hospital.startSurgery(p, o);
        hospital.endSurgery(o);
        os = hospital.getAllOperatingRooms();
        assertEquals(null, os.get(0).getPatient());
        assertEquals(null, os.get(0).getDoctor());
        assertFalse(p.isBeingOperated());
    }
    /**
     * 
     */
    @Test
    public void testGetAllOperatingRooms() {
        hospital.addOperatingRoom(o);
        os = hospital.getAllOperatingRooms();
        assertTrue(os.contains(o));
    }
    /**
     * 
     */
    @Test
    public void testUpdatePatientLog() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Patient does not exist");
        hospital.updatePatientLog(p2, "test");
        hospital.updatePatientLog(p, "test");
        l = hospital.getAllPatients();
        assertEquals("test", l.get(0).getLog());
    }
}
