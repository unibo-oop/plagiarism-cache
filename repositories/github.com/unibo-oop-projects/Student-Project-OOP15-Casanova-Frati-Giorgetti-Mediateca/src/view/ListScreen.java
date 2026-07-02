package view;

/**
 * Interface for creating a new JFrame with a list printed inside.
 *
 * @author Luca Giorgetti
 */
public interface ListScreen {
	/**
	 * Sets the borrowed list taken by controller.
	 *
	 * @param bList
	 *            list of borrowed item
	 */
	void setBorrowedList(String[] bList);

	/**
	 * Sets the like list taken by controller.
	 *
	 * @param wishlist
	 *            list of liked item
	 */
	void setWishlist(String[] wishlist);

	/**
	 * Returns selected item.
	 *
	 * @return selected item from list
	 */
	String getSelectedItem();

	/**
	 * Sets the reviews list taken by controller.
	 *
	 * @param reviewsList
	 *            the list of reviews
	 */
	void setReviewslist(String[] reviewsList);

}
