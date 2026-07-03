package model;

import java.io.Serializable;
import java.util.Set;


public class PatientHistoryImpl implements PatientHistory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Set<Person> setP;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "storicoPazienti.dat";
	private final FileManager<Person> fm;

	public PatientHistoryImpl() {
		fm = new FileManagerImpl<>();
		setP = fm.load(FILENAME);
	}

	public void addPatient(final Person p) {
		this.patientExists(p);
		this.setP.add(p);
		this.insertInFile();

	}

	public Set<Person> getAllPatient() {
		return this.setP;
	}

	private void insertInFile() {
		fm.save(FILENAME, setP);
	}

	private void patientExists(final Person p) {
		if (this.setP.stream().anyMatch(x -> x.getFiscalCode().equals(p.getFiscalCode()))) {
			throw new IllegalArgumentException("Patient already entered");
		}
	}
}
