
package com.biaren.sportclubmanager.adminbundle.controller;

import com.biaren.sportclubmanager.adminbundle.model.UserImpl;
import com.biaren.sportclubmanager.adminbundle.model.interfaces.User;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;

/**
 * Signup controller class. Holds the logic for create system's users.
 * @author nbrunetti
 *
 */
public class SignupController {
    
    private static SignupController SINGLETON;
    
    private SignupController() {
        
    }
    
    /**
     * SINGLETON: get instance of this class
     * @return {@link SignupController} instance
     */
    public static SignupController getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new SignupController();
        }
        return SINGLETON;
    }
    
    /**
     * Check if the username is available.
     * @param usr {@link String} to check in data storage.
     * @return {@link Boolean} value true if is available, false otherwise
     */
    public boolean checkAvailableUsername(final String usr) {
        return ! BaseModelImpl.getInstance().getUsers().stream().anyMatch(u -> u.getUsername().equals(usr));
    }
    
    /**
     * Check if string is empty, useful in validation field.
     * @param s {@link String} to check
     * @return {@link Boolean} true if string lenght is greater than 0, false otherwise
     */
    public boolean checkEmptyField(final String s) {
        return s.length() > 0;
    }
    
    private static User createUser(final String username, final String password, final String email, User.UserRole userRole) {
        return new UserImpl.Builder()
                .username(username)
                .password(password)
                .email(email)
                .userRole(userRole)
                .build();
    }
    
    //Differenzio AMMINISTRATORE da UTENTE normale per un futuro sviluppo di una parte
    //specifica per l'utente amministratore con più privilegi
    /**
     * Creates a new {@link User} with role as Administrator.
     * @param username {@link String} value of username
     * @param password {@link String} value of password
     * @param email {@link String} value of email
     * @return a new {@link User} with Administation role
     */
    public static User createNewAdministrator(final String username, final String password, final String email) {
        return createUser(username, password, email, User.UserRole.ADMINISTRATOR);
    }
    
    /**
     * Creates a new {@link User} with role as User.
     * @param username {@link String} value of username
     * @param password {@link String} value of password
     * @param email {@link String} value of email
     * @return a new {@link User} with User (base) role
     */
    public static User createNewUser(final String username, final String password, final String email) {
        return createUser(username, password, email, User.UserRole.USER);
    }
}
