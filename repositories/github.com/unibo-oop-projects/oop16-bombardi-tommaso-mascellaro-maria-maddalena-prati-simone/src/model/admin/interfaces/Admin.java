package model.admin.interfaces;

import java.util.Optional;

/**
 * Interface for administrator login.
 */
public interface Admin extends Management, Store {

    /**
     * Login administrator.
     * 
     * @param username
     *              string which represents administrator's username
     * @param password
     *              string which represents administrator's password
     * @throws UnsupportedOperationException
     *              if username and password don't match any administrator
     */
    void loginAdmin(String username, String password) throws UnsupportedOperationException;
    /**
     * Logout administrator.
     */
    void logoutAdmin();
    /**
     * Get current administrator.
     * 
     * @return an optional
     *              empty if there isn't a logged administrator
     *              with a string which represents administrator's name and surname if he is logged
     */
    Optional<String> getCurrentAdmin();

}
