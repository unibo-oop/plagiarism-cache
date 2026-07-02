package view;

/**
 * interface for user menu.
 * 
 * @author Luca Giorgetti
 *
 */
public interface UserMenu {
	/**
	 * Allows to set Suggested books list.
	 *
	 * @param bList
	 *            list of suggested books
	 */
	void setSuggestedBooks(String[] bList);

	/**
	 * Allows to set Suggested movies list.
	 *
	 * @param mList
	 *            list of suggested movies
	 */
	void setSuggestedMovies(String[] mList);

}
