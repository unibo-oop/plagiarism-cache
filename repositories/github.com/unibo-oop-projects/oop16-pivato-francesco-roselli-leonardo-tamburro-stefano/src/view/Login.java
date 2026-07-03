package view;

/**
 * 
 * @author Ste
 *
 */
public interface Login {
	
	/**
	 * @return username, null if empty
	 */
	String getUser();
	
	/**
	 * @return password, null if empty
	 */
	String getPassword();

}
