package com.biaren.sportclubmanager.adminbundle.controller;

import java.util.Optional;
import com.biaren.sportclubmanager.adminbundle.model.interfaces.User;
import com.biaren.sportclubmanager.corebundle.controller.MainController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import javafx.stage.Stage;

/**
 * Login controller class. Handles the logic for login access and logout.
 * @author nbrunetti
 *
 */
public class LoginController{
    
    private static LoginController SINGLETON;
    private Optional<User> currentUser = Optional.empty();
    
    private LoginController() {
        
    }
    
    /**
     * SINGLETON: get instance of LoginController.
     * @return {@link LoginController} instance
     */
    public static LoginController getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new LoginController();
        }
        return SINGLETON;
    }

    /**
     * Check if user exists in data storage.
     * @param username {@link String}
     * @param password {@link String}
     * @return {@link Boolean} value, true if user exists, false otherwise.
     */
    public boolean checkUser(final String username, final String password) {
        if (BaseModelImpl.getInstance().getUsers().stream().anyMatch(x -> x.getUsername().equals(username) && x.getPassword().equals(password))) {
            return true;
        } else {
            return false;
        }
    }    
    
    /**
     * Return current user loggedIn
     * @return {@link Optional} {@link User}
     */
    public Optional<User> getUserLoggedIn() {
        return this.currentUser;
    }
    
    /**
     * Simulate login: if user input pass the checkUser, sets the current logged-in user
     * and return true, false otherwise (an Alert will be shown).
     * @param username {@link String} 
     * @param password {@link String}
     * @return {@link Boolean} value true if user input matches data storage, false otherwise.
     */
    public boolean loginUser(final String username, final String password) {
        if (this.checkUser(username, password)) {
            this.currentUser = BaseModelImpl.getInstance().getUserFromUsername(username);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Simulate logout: set current user to {@link Optional} empty value and
     * reload system at start point.
     */
    public void logoutUser() {
        this.currentUser = Optional.empty();
        new MainController(new Stage());
    }
}
