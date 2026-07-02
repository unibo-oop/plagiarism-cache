package controller;

import utils.TypeItem;

/**
 * Interface for a generic controller.
 *
 * @author
 *
 */

public interface Controller {

	/**
	 * Method who sets the View for the Controller.
	 *
	 * @param v
	 *            View requested by the controller
	 *
	 */
	void setView(view.View v);

	/**
	 * Method which lets the actual user access the program if username and
	 * password are contained into the archive.
	 */
	void userLogin();

	/**
	 * Method which lets the manager access the program.
	 */
	void managerLogin();

	/**
	 *
	 * Method which elaborates inputs from the user and set the list with items
	 * filtered.
	 *
	 * @throws Exception
	 */
	void itemElaboration();

	/**
	 * Method which adds the like of the current item selected by the actual
	 * user.
	 */
	void addLike();

	/**
	 * Method which adds the numerical vote and/or a review given by the actual
	 * user to the current selected item.
	 */
	void addReview();

	/**
	 * Method which sets the view's list of borrowed item of the actual user.
	 */
	void borrowList();

	/**
	 * Method which elaborates infos given by the user and registrates it into
	 * the archive.
	 */
	void registerNewUser();

	/**
	 * Method which uses the selected sit and the actual day to book the
	 * position in the study room.
	 *
	 * @throws Exception
	 */
	void takeSit();

	/**
	 * Method which removes the booking of the selected sit in the actual day.
	 *
	 * @throws Exception
	 */
	void cancelSit();

	/**
	 * Method which extends the borrowing of an object.
	 *
	 * @param book
	 *            book which loan as to be extended
	 */
	void extendBorrow(String book);

	/**
	 * Method which deletes the actual user from the archive.
	 */
	void deleteUser();

	/**
	 * Method which deletes an item selected by the user.
	 */
	void deleteItem();

	/**
	 * Method which sets the list of items for the View.
	 */
	void setAllItemList();

	/**
	 * Method which sets the list of users for the View.
	 */
	void setAllUserList();

	/**
	 * Method which removes the selected item from the wishlist.
	 */
	void removeFromWishList();

	/**
	 * Method which sets the wishlist of the actual user for the View.
	 */
	void setWishlist();

	/**
	 * Method which returns an item borrowed by the user.
	 *
	 * @param item
	 *            item to be returned.
	 */
	void giveBackItem(final String item);

	/**
	 * Method which elaborates the loans of the user.
	 */
	void elaborateLoans();

	/**
	 * Method which creates an item based on its type.
	 *
	 * @param type
	 *            type of item to be create.
	 */
	void itemCreate(TypeItem type);

	/**
	 * Method which changes the fields of the user.
	 */
	void userModify();

	/**
	 * Method which changes the fields of the item.
	 */
	void itemModify();

	/**
	 * Method which borrow an item selected by the actual user.
	 */
	void borrowItem();

	/**
	 * Method which elaborates the infos of an item to be showed in a new
	 * screen.
	 */
	void setItemInfoMediateca();

	/**
	 * Method which returns to the View the books and movies suggested to the
	 * user based on his preferences.
	 */
	void suggestedItems();

	/**
	 * Method which dissociates an item to the user selected by himself.
	 */
	void giveBackItemSelectedByUser();

	/**
	 * Method which sets a field of the View with the taken sits of the
	 * StudyRoom in a given day.
	 */
	void setTakenSitsList();

	/**
	 * Method which sets a field of the View with the reviews of a given Item.
	 */
	void allItemReviews();

	/**
	 * Method which eliminates the reference to the last user logged in the
	 * Controller.
	 */
	void logOut();

	/**
	 * Method which sets the user info.
	 */
	void setUserInfo();

	/**
	 * Method which sets the selected user informations to be seen by the actual
	 * user.
	 */
	void giveOtherUserInfo();

	/**
	 * Method which returns the information of the selected user/item to be
	 * shown in a new screen.
	 */
	void elementSelectedInManager();

	/**
	 * Method which sets the list of item borrowed by the user selected by
	 * manager.
	 */
	void otherUserBorrowList();

	/**
	 * Method which fills the fields of the item to be modified with its actual
	 * data.
	 */
	void setItemModifyField();

	/**
	 * Method which tells if the item passed as string is a book or a movie.
	 *
	 * @param string
	 *            item to be recognized as book or movie.
	 * @return true == book false == movie
	 */
	boolean tellMeIfItemIsBook(String string);

	/**
	 * Returns the number of sits in study room.
	 *
	 * @return number of total sits in study room.
	 */
	int numberOfSits();
}
