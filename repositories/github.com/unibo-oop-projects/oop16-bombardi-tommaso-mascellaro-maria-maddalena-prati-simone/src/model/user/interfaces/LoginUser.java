package model.user.interfaces;

import java.util.Optional;

import model.admin.Pair;
/**
 * 
 */
public interface LoginUser {
    //USER LOGIN
    /**
     * register a new user.
     * @param userPass pair with username and password chosen by user who want to register
     * @param nameSurname pair with name and surname of the user who want to register
     * @throws IllegalStateException if the username chosen already exists
     */
    void register(Pair<String, String> userPass, Pair<String, String> nameSurname) throws IllegalStateException;
    /**
     * @param username user input in login panel
     * @param password password input in login panel
     * @throws UnsupportedOperationException if login credentials are wrong
     */
    void checkLogin(String username, String password) throws UnsupportedOperationException;
    /**
     * get the current user logged.
     * @return an optional with the user that is logged-in now
     */
    Optional<String> getLoggedUserName();
    /**
     * set the current user logged to empty.
     */
    void logout();

}
