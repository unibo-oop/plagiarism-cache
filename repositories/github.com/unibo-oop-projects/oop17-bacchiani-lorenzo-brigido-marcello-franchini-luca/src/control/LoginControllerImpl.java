package control;

import java.util.Set;

import model.Account;
import model.AccountHistory;
import model.AccountHistoryImpl;
import model.AccountImpl;
import model.Worker;

public class LoginControllerImpl implements LoginController {
    /**
     * 
     */
	private static LoginControllerImpl singleton;
	private final AccountHistory aH = new AccountHistoryImpl();
	private Worker logged;
     /**
	 * Il costruttore permette di ritornare un'unica istanza della classe,
	 * secondo il pattern Singleton.
	 * 
	 * @return a LoginController's instance.
	 */
     public static LoginController instance() {
		if (singleton == null) {
			LoginControllerImpl.singleton = new LoginControllerImpl();
		}
		return singleton;
	}
	public void addAccount(final  Worker w, final String usr, final String psw) {
		this.aH.addAccount(new AccountImpl.Builder()
				.psw(psw)
				.user(usr)
				.staff(w)
				.build());
	}

	public void modifyPassword(final String psw) {
		this.aH.modifyPsw(logged, psw);
	}

	public void modifyUsername(final String usr) {
		this.aH.modifyUsr(logged, usr);
	}

    public boolean verifyAccount(final String username, final String psw) {
		return aH.getAllAccount()
				.stream()
				.anyMatch(x -> x.getUser().equals(username) && x.getPsw().equals(psw));
	}

    public boolean isPrimary(final String username, final String psw) {
		return aH.getAllAccount().stream()
				.anyMatch(x -> x.getUser().equals(username) 
						&& x.getPsw().equals(psw) 
						&& x.getStaff().getRole().getCod().equals("O9"));
	}

    public void setStaffLogged(final String username, final String psw) {
		this.logged = aH.getAllAccount()
				.stream()
				.filter(x -> x.getPsw().equals(psw) && x.getUser().equals(username))
				.map(x -> x.getStaff())
				.findFirst()
				.get();
	}

	public Worker getStaffLogged() {
		return this.logged;
	}
	public void deleteAccount(final String fiscalCode) {
		this.aH.deleteAccount(this.aH.getAllAccount()
				.stream()
				.filter(x -> x.getStaff().getFiscalCode().equals(fiscalCode))
				.findFirst()
				.get());
	}
	public Set<Account> getAccounts() {
		return aH.getAllAccount();
	}
}
