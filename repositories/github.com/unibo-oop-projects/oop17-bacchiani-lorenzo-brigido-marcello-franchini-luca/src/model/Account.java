package model;

public interface Account {
	/**
	 * set a new password.
	 * @param psw
	 */
	void setPsw(final String psw);
	/**
	 * 
	 * @return password.
	 */
	String getPsw();
	/**
	 * set a new username.
	 * @param user
	 */
	void setUser(final String user);
	/**
	 * 
	 * @return username.
	 */
	String getUser();
	/**
	 * @return staff
	 */
	Worker getStaff();

}
