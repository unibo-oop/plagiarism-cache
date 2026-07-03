package view;

/**
 * This interface allow to access to each instance of the vista that is displaying
 * @author Ste
 *
 */
public interface View {

	/**
	 *
	 * @param o
	 */
	void setObserver(ViewObserver o);

	/**
	 *
	 * @return the login handler
	 */
	Login getLogin();
	
	/**
	 * 
	 * @return the menu handler
	 */
	Menu getMenu();

	/**
	 *
	 * @return the user profile handler
	 */
	UserProfile getuserProfile();

	/**
	 *
	 * @return the users table handler
	 */
	UsersTable getUsersTable();

	/**
	 *
	 * @return the warehouse handler
	 */
	Warehouse getWarehouse();

	/**
	 *
	 * @return the workout table handler
	 */
	WorkoutTable getWorkoutTable();

	/**
	 *
	 * @return the employee profile handler
	 */
	EmployeeProfile getEmployeeProfile();

	/**
	 *
	 * @return the administration handler
	 * */
	Administration getAdministration();
	
	/**
	 * 
	 * @return the product details handler
	 */
	ProductDetails getProductDetails();

}
