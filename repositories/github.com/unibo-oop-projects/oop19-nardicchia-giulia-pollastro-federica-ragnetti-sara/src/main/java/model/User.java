package model;

/**
 * Represent a User in the software domain.
 *
 */
public interface User {

    /**
     * 
     * @return a String that represent the user's username.
     */
    String getUsername();

    /**
     * 
     * @return a String that represent the user's password.
     */
    String getPassword();

    /**
     * Setter for UserPlayer's username.
     * @param username
     *              the username to set
     */
    void setUsername(String username);

    /**
     * Setter for UserPlayer's password.
     * @param password
     *               the password to set
     */
    void setPassword(String password);
}
