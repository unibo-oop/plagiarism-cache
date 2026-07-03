package control;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;
import model.DismissalImpl;
import model.WorkshiftImpl;
import model.Worker;
import model.WorkerImpl;
import model.Workshift;
import model.PrescriptionImpl;
import model.WorkshiftHistory;
import model.WorkshiftHistoryImpl;
import model.DismissalHistory;
import model.DismissalHistoryImpl;
import model.WorkerHistory;
import model.WorkerHistoryImpl;
import model.PrescriptionHistory;
import model.PrescriptionHistoryImpl;
import model.Prescription;
import utilities.Medicine;
import utilities.Role;
import utilities.Department;

public class PersonalControllerImpl implements PersonalController {

	private static PersonalControllerImpl singleton;
	private final PrescriptionHistory pH = new PrescriptionHistoryImpl();
	private final WorkshiftHistory wH = new WorkshiftHistoryImpl();
	private final WorkerHistory workH = new WorkerHistoryImpl();
	private final DismissalHistory dH = new DismissalHistoryImpl();
	private Worker justAddedWorker;
	/**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton.
	 * 
	 * @return a PersonalController's instance.
	 */
	public static PersonalController instance() {
		if (singleton == null) {
			PersonalControllerImpl.singleton = new PersonalControllerImpl();
		}
		return singleton;
	}

	public void addPrescription(final String medicineName, final String fiscalCode, final String cod) {
		if (medicineName != null && fiscalCode != null && cod != null) {
			this.pH.addPrescription(new PrescriptionImpl.Builder()
					.date(LocalDate.now())
					.medicine(Stream
							.of(Medicine.values())
							.filter(x -> x.getNome().equals(medicineName))
							.findFirst()
							.get())
					.patient(PatientControllerImpl.instance().getAllPatients()
							.stream()
							.filter(x -> x.getFiscalCode().equals(fiscalCode))
							.findFirst()
							.get())
					.staff(LoginControllerImpl.instance().getStaffLogged())
					.code(cod).build());	
		} else {
			throw new NoSuchElementException("Complete all fields!");
		}

	}

	public void addWorkshift(final Worker p, final String department, final LocalTime start, final LocalTime end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("Complete al fields");
		} else {
			this.wH.addWorkhour(new WorkshiftImpl.Builder()
					.staff(p)
					.department(Stream.of(Department.values())
							.filter(x -> x.getName().equals(department))
							.findFirst()
							.get())
					.start(start)
					.end(end)
					.build());
		}
	}

	public void modifyWorkshift(final String fiscalCode, final String department, 
			final LocalTime start, final LocalTime end) {
		if (fiscalCode != null && department != null) {
			this.wH.modifyWorkhour(new WorkshiftImpl.Builder()
					.start(start)
					.end(end)
					.staff(workH.getAllWorkers().stream()
							.filter(x -> x.getFiscalCode().equals(fiscalCode))
							.findFirst()
							.get())
					.department(Stream.of(Department.values())
							.filter(x -> x.getName().equals(department))
							.findFirst()
							.get())
					.build());
		} else {
			throw new NoSuchElementException("Complete all fields");
		}
	}

	public Set<Worker> getWorkers() {
		return this.workH.getAllWorkers();
	}

	public void addWorker(final String name, final String surname, final String fiscalCode, final String birthPlace, 
			final String residency, final String sex, final String role, final LocalDate date) {
		this.justAddedWorker = new WorkerImpl.Builder()
				.birthPlace(birthPlace)
				.date(date)
				.fiscalCode(fiscalCode)
				.name(name)
				.residency(residency)
				.role(Stream.of(Role.values())
						.filter(x -> x.getDescr().equals(role))
						.findFirst()
						.get())
				.sex(sex)
				.surname(surname)
				.build();
		this.workH.addWorker(this.justAddedWorker);

	}

	public void deleteWorker(final String fiscalCode, final LocalDate date, final String reason) {
		if (fiscalCode != null && date != null && reason != null) {
			final Worker w = this.workH.getAllWorkers()
					.stream().filter(x -> x.getFiscalCode().equals(fiscalCode))
					.findFirst()
					.get();
			if (w.getRole().getCod().equals("O7") 
			        || w.getRole().getCod().equals("O8") 
			        || w.getRole().getCod().equals("O9")) {
				LoginControllerImpl.instance().deleteAccount(fiscalCode);
			}
			this.dH.addDismiss(new DismissalImpl.Builder()
					.date(date)
					.staff(w)
					.reason(reason).build());
			this.wH.setState(w);
			this.workH.removeWorker(w);

		} else {
			throw new NoSuchElementException("Complete all fields");
		}
	}

	public Worker getJustAddedWorker() {
		return this.justAddedWorker;
	}

	public Set<Workshift> getWorkshifts() {
		return this.wH.getWorkshifts();
	}

	public Set<Prescription> getPrescriptions() {
		return pH.getAllPrescription();

	}
}
