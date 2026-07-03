package control;

import java.util.Set;

import model.Account;
import model.Worker;

public interface LoginController {
     /**
	 * add a new account.
	 * @param w
	 * @param usr
	 * @param psw
	 */
	void addAccount(final Worker w, final String usr, final String psw);
	/**
	 * modify password.
	 * @param psw
	 */
	void modifyPassword(final String psw);
	/**
	 * modify username.
	 * @param usr
	 */
	void modifyUsername(final String usr);
     /**
	 * verify account.
	 * @param username
	 * @param psw
	 * @return if username and password match with an account.
	 */
	boolean verifyAccount(final String username, final String psw);
     /**
	 * verify if a primary is trying to log in.
	 * @param userName
	 * @param psw
	 * @return if the account's owner is a primary or not.
	 */
	boolean isPrimary(final String userName, final String psw);
	/**
	 * set the a new variable every time someone log in.
	 * @param userName
	 * @param psw
	 */
	void setStaffLogged(final String userName, final String psw);
	/**
	 * 
	 * @return 
	 *      who has made the last login.
	 */
	Worker getStaffLogged();
	/**
	 * delete an account.
	 * @param fiscalCode
	 */
	void deleteAccount(final String fiscalCode);
	/**
	 * get all account.
	 * @return all accounts.
	 */
     Set<Account> getAccounts();
}
