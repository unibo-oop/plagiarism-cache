package model;

public interface TaekwondoPlatform {

	/**
	 * Check if the inserted data are correct for the login
	 * @param username
	 * @param password
	 * @return true if the credentials are correct
	 */
	public boolean logIn(String username, String password);
}
