package controller.interfaces;

import model.admin.Pair;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;

/**
 * Interface for controller input operations.
 */
public interface ControllerIn {

    /**
     * Login administrator.
     * 
     * @param username
     *              string which represents administrator's username
     * @param password
     *              string which represents administrator's password
     * @throws IllegalArgumentException
     *              if username and password aren't both valid (strings from 5 to 8 characters)
     * @throws UnsupportedOperationException
     *              if username and password don't match any administrator
     */
    void loginAdmin(String username, String password) throws IllegalArgumentException, UnsupportedOperationException;
    /**
     * Logout administrator.
     */
    void logoutAdmin();
    /**
     * Login user.
     * 
     * @param username
     *              string which represents user's username
     * @param password
     *              string which represents user's password
     * @throws IllegalArgumentException
     *              if username and password aren't both valid (strings from 5 to 8 characters)
     * @throws UnsupportedOperationException
     *              if username and password don't match any user
     */
    void loginUser(String username, String password) throws IllegalArgumentException, UnsupportedOperationException;
    /**
     * Logout user.
     */
    void logoutUser();


    /**
     * Add buy object.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              string which represents the number of objects
     * @throws IllegalArgumentException
     *              if the string which represents the number of objects is not valid
     */
    void addBuyObject(Object1 obj, String numObj) throws IllegalArgumentException; 
    /**
     * Add rent object.
     * 
     * @param obj
    *              enum value which represents the object
     * @param numObj
    *              string which represents the number of objects
     * @param numDays
    *              string which represents the duration of the rent (number of days)
     * @param season
    *              enum value which represents the season of the rent
     * @throws IllegalArgumentException
    *              if the string which represents the number of objects and the one which represents the number of days aren't both valid
     */
    void addRentObject(Object2 obj, String numObj, String numDays, Season season) throws IllegalArgumentException;
    /**
     * Add instructor's lesson.
     * 
     * @param type
     *              enum value which represents the instructor's lesson
     * @param numStudents
     *              string which represents the number of skiers at the lesson
     * @param season
     *              enum value which represents the season of the lesson
     * @throws IllegalArgumentException
     *              if the string which represents the number of skiers is not valid
     */
    void addInstructor(Instructor type, String numStudents, Season season) throws IllegalArgumentException;
    /**
     * Add skipass.
     * 
     * @param type
     *              enum value which represents the skipass
     * @param numObj
     *              string which represents the number of skipass
     * @param season
     *              enum value which represents the season of the skipass
     * @throws IllegalArgumentException
     *              if the string which represents the number of skipass is not valid
     */
    void addSkipass(Skipass type, String numObj, Season season) throws IllegalArgumentException;
    /**
     * Add storage object.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              string which represents the number of objects
     * @param numDays
     *              string which represents the duration of the storage (number of days)
     * @throws IllegalArgumentException
     *              if the string which represents the number of objects and the one which represents the number of days aren't both valid
     */
    void addStorage(Object2 obj, String numObj, String numDays) throws IllegalArgumentException;


    /**
     * Remove operation from cart.
     * 
     * @param index
     *              string which represents the index of the operation that must be removed
     * @throws IllegalArgumentException
     *              if the input string doesn't represent a valid index
     */
    void removeOperation(String index) throws IllegalArgumentException;
    /**
     * Remove all operations from cart.
     */
    void removeAllOperations();
    /**
     * Pay with a credit card.
     * 
     * @param ownerCode
     *             pair with strings that represent the owner and the code of the credit card
     * @param dateCvc
     *             pair with strings that represent the expiration date and the cvc of the credit card
     * @throws IllegalArgumentException
     *              if the input informations of the credit card aren't all valid
     */
    void pay(Pair<String, String> ownerCode, Pair<String, String> dateCvc) throws IllegalArgumentException;


    /**
     * Complete operations.
     * 
     * @throws UnsupportedOperationException
     *              if an error occurs while writing new operations on database file
     */
    void completeOperations() throws UnsupportedOperationException;
    /**
     * Register user.
     * 
     * @param userPass
     *              pair with strings that represent user's username and password
     * @param nameSur
     *              pair with strings that represent user's name and surname
     * @throws IllegalArgumentException
     *              if the input informations of the user aren't all valid
     * @throws IllegalStateException
     *              if username and password match a user that already exists
     * @throws UnsupportedOperationException
     *              if an error occurs while writing new user on database file
     */
    void registerUser(Pair<String, String> userPass, Pair<String, String> nameSur)
            throws IllegalArgumentException, IllegalStateException, UnsupportedOperationException;
    /**
     * Reset application.
     * 
     * @throws UnsupportedOperationException
     *              if an error occurs while writing demo operations or users on database file
     */
    void resetApplication() throws UnsupportedOperationException;

}
