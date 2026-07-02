package view;

/**
 * Interface for the Manager Scren Panel.
 *
 * @author Luca Giorgetti
 *
 */
public interface ManagerScreen {
	/**
	 * Allow to set the list of all user.
	 *
	 * @param userList
	 *            the lst of all user
	 */
	void setUserList(String[] userList);

	/**
	 * Allow to set the list of all item.
	 *
	 * @param itemList
	 *            the list of all itemm
	 */
	void setItemList(String[] itemList);

	/**
	 * method which allows to get the selected User/Item.
	 *
	 * @return selected item/user by manager
	 */
	String getSelected();

	/**
	 * Sets the list of item borrowed by user.
	 *
	 * @param borrowedList
	 *            list of item borrowed by user
	 */
	void setUserBorrowedList(String[] borrowedList);

	/**
	 * Returns the double clicked object in Manager Screen.
	 * 
	 * @return double clicked object
	 */
	String getDClicked();

}
