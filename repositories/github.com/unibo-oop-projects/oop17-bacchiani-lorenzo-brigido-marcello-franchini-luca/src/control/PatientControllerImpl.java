package control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import model.Person;
import model.PersonImpl;
import model.Worker;
import model.ExamReservation;
import model.ExamReservationImpl;
import model.Hospitalization;
import model.HospitalizationImpl;
import model.PatientHistory;
import model.PatientHistoryImpl;
import model.ExamReservationHistory;
import model.ExamReservationHistoryImpl;
import model.HospitalizationHistory;
import model.HospitalizationHistoryImpl;
import utilities.Exam;
import utilities.Bed;

import java.util.stream.Stream;

public class PatientControllerImpl implements PatientController {
    /**
     * 
     */
	private static PatientControllerImpl singleton;
	private final PatientHistory pH = new PatientHistoryImpl();
	private final HospitalizationHistory hH = new HospitalizationHistoryImpl();
	private final ExamReservationHistory eRH = new ExamReservationHistoryImpl();
	private Person patient;

	/**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton.
	 * 
	 * @return a PatientController's instance.
	 */

	public static PatientController instance() {
		if (singleton == null) {
			PatientControllerImpl.singleton = new PatientControllerImpl();
		}
		return singleton;
	}

	public void addPatient(final String name, final String surname, final String fiscalCode, 
			final LocalDate date, final String place, final String residency, final String sex) {
		this.pH.addPatient(new PersonImpl.Builder()
				.birthPlace(place)
				.date(date)
				.fiscalCode(fiscalCode)
				.name(name)
				.residency(residency)
				.surname(surname)
				.sex(sex)
				.build());	
	}

	public void addBook(final LocalDate date, final LocalTime time, final String fiscalCodePaz, 
			final String examName, final Worker pers) {
		if (date != null && fiscalCodePaz != null) {
			this.eRH.addBooking(new ExamReservationImpl.Builder()
					.date(date)
					.exam(Stream.of(Exam.values()).filter(x -> x.getExamName().equals(examName)).findFirst().get())
					.time(time)
					.patient(pH.getAllPatient().stream().filter(x -> x.getFiscalCode().equals(fiscalCodePaz))
							.findFirst().get())
					.staff(pers)
					.build());
		} else {
			throw new NoSuchElementException("Complete all fields");
		}
	}

	public void addHospitalization(final String cod, final String fiscalCodePaz,
			final String departName, final String roomNumberbedNumber, final String cause, final Worker pers) {
		if (roomNumberbedNumber != null  && departName != null && cod != null) {
			this.hH.addHospitalization(new HospitalizationImpl.Builder()
					.cause(cause)
					.code(cod)
					.letto(PatientControllerImpl.instance().freeBeds(departName)
							.stream().filter(x -> x.getDepartment().getName().equals(departName))
							.filter(x -> x.getRoom().getNumber() == Integer.parseInt(roomNumberbedNumber.substring(7, 8)))
							.filter(x -> x.getNumber() == Integer.parseInt(roomNumberbedNumber.substring(15, 16)))
							.findFirst().get())
					.patient(pH.getAllPatient()
							.stream()
							.filter(x -> x.getFiscalCode().equals(fiscalCodePaz))
							.findFirst().get())
					.staff(pers)
					.build());	
		} else {
			throw new NoSuchElementException("Complete all fields");
		}
	}

	public Set<Person> getAllPatients() {
		return this.pH.getAllPatient();
	}

	public Set<Hospitalization> getAllRecoveries() {
		return hH.getHospitalizations();
	}

	public Set<ExamReservation> getExams() {
		return this.eRH.getAllBooking();
	}

	public Set<Bed> freeBeds(final String departName) {
		return Stream.of(Bed.values())
				.filter(x -> !isBusy(x) && x.getDepartment().getName().equals(departName))
				.collect(Collectors.toSet());
	}

	public void setPatientForInf(final String fiscalCode) {
		this.patient = pH.getAllPatient()
				.stream()
				.filter(x -> x.getFiscalCode().equals(fiscalCode))
				.findFirst()
				.get();
	}

	public Person getPatientForInf() {
		return this.patient;
	}

	public void modifyHospitalizationState(final String fiscalCode, final String state, 
			final LocalDate date, final String note) {
		if (fiscalCode != null && !note.equals("") && date != null) {
			if (state.equals("Dimesso")) {
				this.hH.discharge(hH.getHospitalizations().stream()
						.filter(x -> x.getState().equals("Ricoverato"))
						.filter(x -> x.getPatient().getFiscalCode().equals(fiscalCode))
						.findFirst()
						.get(), 
						date, 
						note);
			} else {
				this.hH.decheased(hH.getHospitalizations().stream()
						.filter(x -> x.getState().equals("Ricoverato"))
						.filter(x -> x.getPatient().getFiscalCode().equals(fiscalCode))
						.findFirst()
						.get(), 
						date, 
						note);
			}
		} else {
			throw new NoSuchElementException("Complete all fields!");
		}
	}

	private boolean isBusy(final Bed l) {
		return this.hH.getHospitalizations()
				.stream()
				.filter(x -> x.getState().equals("Ricoverato"))
				.anyMatch(x -> x.getBed().getNumber() == l.getNumber() 
				&& x.getBed().getRoom().getNumber() == l.getRoom().getNumber() 
				&& x.getBed().getDepartment().getCode().equals(l.getDepartment().getCode()));
	}
}
