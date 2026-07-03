package model;

import java.io.Serializable;
import java.util.Set;

public class DismissalHistoryImpl implements DismissalHistory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Set<Dismissal> setP;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "storicoLicenziamenti.dat";
	private final FileManager<Dismissal> fm;

	public DismissalHistoryImpl() {
		fm = new FileManagerImpl<>();
		setP = fm.load(FILENAME);
	}


	public void addDismiss(final Dismissal l) {
		setP.add(l);
		this.insertInFile();
	}

	public Set<Dismissal> getAllDismiss() {
		return setP;
	}

	private void insertInFile() {
		fm.save(FILENAME, setP);
	}
}
