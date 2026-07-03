package hotelmaster.database.login;

import com.google.common.base.Optional;

import hotelmaster.login.AccountLevel;

/**
 * Manages the login within the database.
 */
public interface LoginManager {

    /**
     * Check if username and password correspond.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     * @return The {@link AccountLevel} of this account, {@link Optional#empty}
     *         if username or password are incorrect
     */
    Optional<AccountLevel> logIn(String username, String password);

    /**
     * Change the password of an existing account (administrator or secretary).
     * 
     * @param username
     *            the username
     * @param password
     *            the old password
     * @param newPassword
     *            the new password
     * @return The {@link AccountLevel} of this account after changing password,
     *         {@link Optional#empty} if the password cannot be changed
     *         (username or password are incorrect)
     */
    Optional<AccountLevel> changePassword(String username, String password, String newPassword);

}