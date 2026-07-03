package model;

import java.io.Serializable;
import java.util.Set;

public class PrescriptionHistoryImpl implements PrescriptionHistory, Serializable {

	private static final long serialVersionUID = 1L;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "prescriptionHistory.dat";
	private final Set<Prescription> setPrescription;
	private final FileManager<Prescription> fm;

	public PrescriptionHistoryImpl() {
		this.fm = new FileManagerImpl<>();
		this.setPrescription = fm.load(FILENAME);
	}

	public void addPrescription(final Prescription p) {
		this.prescriptionExist(p);
		this.setPrescription.add(p);
		this.insertInFile();
	}

	public Set<Prescription> getAllPrescription() {
		return this.setPrescription;
	}

	private void prescriptionExist(final Prescription p) {
		if (this.setPrescription.stream().anyMatch(x -> x.getCode().equals(p.getCode()))) {
			throw new IllegalArgumentException("Ricetta gia' esistente");
		}
	}

	private void insertInFile() {
		fm.save(FILENAME, setPrescription);
	}

}
