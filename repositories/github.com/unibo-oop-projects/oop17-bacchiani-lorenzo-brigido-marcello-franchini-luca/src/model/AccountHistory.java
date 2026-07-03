package model;

import java.util.Set;

public interface AccountHistory {
	/**
	 * 
	 * @param a
	 *      add new Account.
	 *
	 */
	void addAccount(final Account a);
	/**
	 * modify the password.
	 * @param w
	 * @param psw
	 */
	void modifyPsw(final Worker w, final String psw);
	/**
	 * modify the username.
	 * @param usr
	 * @param a
	 */
	void modifyUsr(final Worker w, final String usr);
	/**
	 * 
	 * @return all account.
	 */
	Set<Account> getAllAccount();
	/**
	 * delete account from set.
	 * @param a.
	 */
	void deleteAccount(final Account a);

}
