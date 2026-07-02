package view;

/**
 * Interface for the review screen.
 *
 * @author Luca Giorgetti
 *
 */

public interface ReviewScreen {
	/**
	 * Returns the score typed by user.
	 *
	 * @return the selected score
	 */
	int getSelectedScore();

	/**
	 * Returns the review typed by user.
	 *
	 * @return the review typed
	 */
	String getReview();
}
