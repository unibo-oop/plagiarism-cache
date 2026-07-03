package model;

import java.io.Serializable;
import java.util.Set;

public class WorkshiftHistoryImpl implements WorkshiftHistory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "storicoTurni.dat";
	private final FileManager<Workshift> fm;
	private final Set<Workshift> workSet;

	public WorkshiftHistoryImpl() {
		this.fm = new FileManagerImpl<>();
		this.workSet = fm.load(FILENAME);	
	}

	public void addWorkhour(final Workshift o) {
		this.workSet.add(o);
		this.insertInFile();

	}

	public void modifyWorkhour(final Workshift o) {
		this.workSet.stream().filter(x -> x.getWorker().getFiscalCode().equals(o.getWorker().getFiscalCode()))
		.filter(x -> x.getState().equals("Attivo")).findFirst().get().changeState();
		this.workSet.add(o);
		this.insertInFile();
	}

	public void setState(final Worker w) {
		this.workSet.stream()
		.filter(x -> x.getWorker().getFiscalCode().equals(w.getFiscalCode()))
		.findFirst()
		.get()
		.changeState();
		this.insertInFile();
	}

	public Set<Workshift> getWorkshifts() {
		return this.workSet;
	}

	private void insertInFile() {
		fm.save(FILENAME, workSet);
	}
}
