package hotelmaster.database.admin;

import java.util.Set;

import hotelmaster.exceptions.AccountException;

/**
 * Manages the accounts of administrator and secretaries.
 */
public interface AccountManager {

    /**
     * Add a new secretary account.
     * @param username the username 
     * @param password the password
     * @throws AccountException if the username already exits
     */
    void addUserAccount(String username, String password) throws AccountException;

    /**
     * Remove an existing secretary account.
     * @param username the username
     */
    void removeUserAccount(String username);

    /**
     * Get all the existing account in the database.
     * @return a {@link Set} of secretary accounts
     */
    Set<String> getAccounts();

}