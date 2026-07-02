package view;

/**
 * Interface for user show.
 *
 * @author Luca Giorgetti
 *
 */
public interface UserShow {
	/**
	 * Starts user screen.
	 *
	 * @param v
	 *            the calling View name
	 */
	void startUserShow(View v);

	/**
	 * Sets user info.
	 *
	 * @param nameS
	 *            the user name
	 * @param surnameS
	 *            the user surname
	 * @param usernameS
	 *            the user username
	 * @param passwordS
	 *            the user password
	 * @param birthDateS
	 *            the user birtdate
	 * @param emailS
	 *            the user email
	 * @param telephoneS
	 *            the user telephone number
	 * @param bookPref1S
	 *            the user first book genre preferred
	 * @param bookPref2S
	 *            the user second book genre preferred
	 * @param bookPref3S
	 *            the user third book genre preferred
	 * @param filmPref1S
	 *            the user first movie genre preferred
	 * @param filmPref2S
	 *            the user second movie genre preferred
	 * @param filmPref3S
	 *            the user third movie genre preferred
	 */
	void setUserField(String nameS, String surnameS, String usernameS,
			String passwordS, String birthDateS, String emailS,
			String telephoneS, String bookPref1S, String bookPref2S,
			String bookPref3S, String filmPref1S, String filmPref2S,
			String filmPref3S);

}
