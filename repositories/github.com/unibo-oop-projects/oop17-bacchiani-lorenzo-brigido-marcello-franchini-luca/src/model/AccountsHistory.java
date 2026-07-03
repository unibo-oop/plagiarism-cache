package model;


import java.util.Set;

public interface AccountsHistory {

	/**
	 * add a new account connected with a person.
	 * @param p
	 * @param a
	 * @throws IllegalArgumentException
	 */
	void addAccount(final Account a) throws IllegalArgumentException;
	/**
	 * modify account's password.
	 * @param p
	 * @param psw
	 * @throws IllegalArgumentException
	 */
	void modifyPsw(final Worker p, final String psw) throws IllegalArgumentException;
	/**
	 * modify account's username.
	 * @param p
	 * @param usr
	 * @throws IllegalArgumentException
	 */
	void modifyUsr(final Worker p, final String usr) throws IllegalArgumentException;
	/**
	 * 
	 * @return all account.
	 */
	Set<Account> getAllAccount();




}
