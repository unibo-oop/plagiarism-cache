package view;

/**
 * Interface of panel of user login.
 *
 * @author Luca
 */
public interface UserLogin {

	/**
	 * Method which returns username inserted by user.
	 *
	 * @return typed username
	 */
	String getUserUsername();

	/**
	 * Method which returns password inserted by user.
	 *
	 * @return typed password
	 */
	String getUserPassword();

	/**
	 * Method which returns password inserted by manager.
	 *
	 * @return typed system password
	 */
	String getManagerPassword();

}
