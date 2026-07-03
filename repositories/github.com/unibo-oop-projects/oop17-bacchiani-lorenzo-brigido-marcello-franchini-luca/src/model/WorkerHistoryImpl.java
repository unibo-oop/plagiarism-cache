package model;

import java.io.Serializable;
import java.util.Set;

public class WorkerHistoryImpl implements WorkerHistory, Serializable {

	private static final long serialVersionUID = 1L;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "workerHistory.dat";
	private final Set<Worker> setWorker;
	private final FileManager<Worker> fm;

	public WorkerHistoryImpl() {
		this.fm = new FileManagerImpl<>();
		this.setWorker = fm.load(FILENAME);
	}

	public void addWorker(final Worker w) {
		this.workerExist(w);
		this.setWorker.add(w);
		this.insertInFile();
	}

	public Set<Worker> getAllWorkers() {
		return this.setWorker;
	}

	public void removeWorker(final Worker p) {
		this.setWorker.remove(
				this.setWorker
				.stream()
				.filter(x -> x.getFiscalCode().equals(p.getFiscalCode()))
				.findFirst()
				.get());
		this.insertInFile();
	}

	private void insertInFile() {
		fm.save(FILENAME, setWorker);
	}

	private void workerExist(final Worker w)  {
		if (this.setWorker.stream().anyMatch(x -> x.getFiscalCode().equals(w.getFiscalCode()))) {
			throw new IllegalArgumentException("Worker already entered");
		}
	}


}
