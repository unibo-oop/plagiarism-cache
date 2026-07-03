package model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

/**
 * This class represent the collection of application's Users.
 */

public interface UserManager extends Serializable {
    /**
     * 
     * Insert a User in User collection.
     * 
     * @param user
     *            Instance of application's user
     * @throws IllegalArgumentException
     *             if the user is already registered
     */
    void add(User user) throws IllegalArgumentException;

    /**
     * Allows to perform actions on the logged-in user.
     * 
     * @return the logged user
     */
    User currentUser();

    /**
     * Save the last user logout date and reinsert the logged user in
     * collection.
     * 
     * @throws IllegalArgumentException
     *             if there are problems to logout the user
     */
    void logout() throws IllegalArgumentException;

    /**
     * Remove a user.
     * 
     * @param user
     *            the user to remove
     * @throws IllegalArgumentException
     *             if the user is not registered
     */
    void remove(User user) throws IllegalArgumentException;

    /**
     * Change the password of the user.
     * 
     * @param user
     *            the user who wants to change the password
     * @param password
     *            the new password
     * @throws NoSuchAlgorithmException
     *             when there are errors in the password generation
     * @throws IllegalArgumentException
     *             if there are problems to change the password because the user
     *             is not registered
     */
    void changePassword(User user, String password) throws NoSuchAlgorithmException, IllegalArgumentException;

    /**
     * Check if the user is registered and can log in.
     * 
     * @param user
     *            the password of the user
     * @return true if the user has already been registered, otherwise false
     * 
     */
    Boolean login(User user);

    /**
     * Set the archive of users.
     * 
     * @param s
     *            a new archive of users
     */
    void addAll(Set<User> s);

    /**
     * @return all registered users
     */
    Set<User> getAll();
}
