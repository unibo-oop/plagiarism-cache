package model;

import java.io.Serializable;
import java.util.Set;

public class AccountHistoryImpl implements AccountHistory, Serializable {

    private static final long serialVersionUID = 1L;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME = PATH + "storicoAccount.dat";
	private final FileManager<Account> fm;
	private final Set<Account> accountSet;

	public AccountHistoryImpl() {
		this.fm = new FileManagerImpl<>();
		this.accountSet = fm.load(FILENAME);
	}

	public void addAccount(final Account a) {
		this.findUsername(a.getUser());
		this.accountSet.add(a);
		this.insertInFile();
	}

	public void modifyPsw(final Worker w, final String psw) {
		this.accountSet.stream().filter(x -> x.getStaff().getFiscalCode().equals(w.getFiscalCode())).findFirst().get().setPsw(psw);
		this.insertInFile();
	}

	public void modifyUsr(final Worker w, final String usr) {
		this.findUsername(usr);
		this.accountSet.stream().filter(x -> x.getStaff().getFiscalCode().equals(w.getFiscalCode())).findFirst().get().setUser(usr);
		this.insertInFile();
	}

	public void deleteAccount(final Account a) {
		this.accountSet.remove(a);
		this.insertInFile();
	}

	public Set<Account> getAllAccount() {
		return this.accountSet;
	}

	private void insertInFile() {
		fm.save(FILENAME, accountSet);
	}

	private void findUsername(final String user) {
		if (this.accountSet.stream().map(x -> x.getUser()).anyMatch(x -> x.equals(user))) {
			throw new IllegalArgumentException("username already entered");
		}
	}
}
