package model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class HospitalizationHistoryImpl implements HospitalizationHistory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "storicoRicoveri.dat";
	private final Set<Hospitalization> recovery;
	private final FileManager<Hospitalization> fm;


	public HospitalizationHistoryImpl() {
		this.fm = new FileManagerImpl<>();
		this.recovery = fm.load(FILENAME);
	}

	public void addHospitalization(final Hospitalization r) {
		foundException(r);
		this.recovery.add(r);
		this.insertInFile();
	}

	public Set<Hospitalization> getHospitalizations() {
		return this.recovery;
	}

	public void discharge(final Hospitalization r, final LocalDate date, final String note) {
		this.changeState(r, "Dimesso", date, note);
	}

	public void decheased(final Hospitalization r, final LocalDate date, final String note) {
		this.changeState(r, "Deceduto", date, note);
	}

	private void insertInFile() {
		fm.save(FILENAME, recovery);
	}

	private void foundException(final Hospitalization r) throws IllegalArgumentException {
		if (this.recovery.stream().filter(x -> x.getState().equals("Ricoverato")).anyMatch(x -> x.getPatient().getFiscalCode().equals(r.getPatient().getFiscalCode()))) {
			throw new IllegalArgumentException("paziente gi� inserito");
		} else if (this.recovery.stream().anyMatch(x -> x.getCode().equals(r.getCode()))) {
			throw new IllegalArgumentException("codice ricovero gi� utilizzato");
		} else if (this.recovery.stream().filter(x -> x.getState().equals("Ricoverato")).anyMatch(x -> x.getBed().equals(r.getBed()))) {
			throw new IllegalArgumentException("letto occupato");
		}
	}

	private void changeState(final Hospitalization r, final String state, final LocalDate date, final String note) {
		this.recovery.stream().filter(x -> x.getCode().equals(r.getCode())).findFirst().get().setState(state);
		this.recovery.stream().filter(x -> x.getCode().equals(r.getCode())).findFirst().get().setEndDate(date);
		this.recovery.stream().filter(x -> x.getCode().equals(r.getCode())).findFirst().get().setNote(note);
		this.insertInFile();
	}





}
