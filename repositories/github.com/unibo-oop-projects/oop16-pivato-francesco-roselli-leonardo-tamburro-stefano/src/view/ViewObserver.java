package view;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import model.enumerations.Status;
import model.interfaces.Employee;
import model.interfaces.Exercise;
import model.interfaces.Product;
import model.interfaces.User;

/**
 *
 * Functions implemented by Controller and used by View
 * @author Ste
 *
 */
public interface ViewObserver {

	/**
	 * Perform the login.
	 * @return the login status result
	 */
	Status login();

	/**
	 * Fill the table with all the Customers informations
	 */
	void populateUsersTable();

	/**
	 * Fill the table with all the product present in warehouse
	 */
	void populateProductTable();

	/**
         * Fill the table with all Exercises of the chosen user
         * @param user
         * */
        void populateWorkoutTable(User user);

	/**
         * Fill the table with all Employee informations
         */
        void populateEmployeesTable();

	/**
	 * Opens a file chooser window.
	 * @param scene
	 * @return image
	 */
	Image getProfileImage(Scene scene);

	/**
	 * Delete the profile image and set it to default.
	 * @return default image
	 */
	Image deleteProfileImage();

	 /**
         * Save the current user in the database.
         * @return the status of the operation
         */
        Status saveUserData();

        /**
         * Delete the current user from the database.
         * @param user The current user
         * @return the status of the operation
         */
        Status deleteUserData(User user);

	/**
	 * Take a user and display all it's data in the UserProfile.
	 * @param user The current user
	 */
	void modifyUserData(User user);

	 /**
         * List of all the users present in the database.
         * @return the list of users to be displayed
         */
        List<User> getUsersList();

        /**
         * Save the current product in the database
         * @return the status of the operation
         */
        Status saveProductData();

        /**
         * Delete the current product from the database
         * @param product The current product
         * @return the status of the operation
         */
        Status deleteProductData(Product product);

        /**
         * Take a product and display all its data in the ProductProfile.
         * @param product The current product
         */
        void modifyProductData(Product product);

        /**
         * List containing all the product that are present in the database.
         * @return the list of the products to be displayed
         */
        List<Product> getProducts();

        /**
         * Save the current employee in the database
         * @return the status of the operation
         */
        Status saveEmployeeData();

        /**
         * Delete all the informations of this employee
         * @param employee
         * @return the status of the operation
         */
        Status deleteEmployeeData(Employee employee);

	/**
	 * Take a employee and display all it's data in the EmployeeProfile.
	 * @param employee
	 */
	void modifyEmployeeData(Employee employee);

	/**
	 *
	 * @return the list of employee to be displayed
	 */
	List<Employee> getEmployeeList();



	/**
	 * Add an exercise to the workout table
	 * @param user
	 * @param ex to add
	 * @param day, the day in which the exercise is done
	 * @return the status of this operation
	 */
	Status addExercise(User user, Exercise ex, String day);

	/**
	 * Remove an exercise from the workout table
	 * @param user
	 * @param ex to remove
	 * @param day, the day in which the exercise is done
	 * @return the status of this operation
	 */
	Status removeExercise(User user, Exercise ex, String day);

}
