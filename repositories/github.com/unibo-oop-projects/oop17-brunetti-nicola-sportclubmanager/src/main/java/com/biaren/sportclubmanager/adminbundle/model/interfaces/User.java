package com.biaren.sportclubmanager.adminbundle.model.interfaces;

/**
 * Represent a user in the software domain. 
 * @author nbrunetti
 *
 */
public interface User {
	
    /**
     * 
     *  @return a String that represent the user's email
     */
    String getEmail();
	
    /**
     * 
     * @return a String that represent the user's username
     */
    String getUsername();
	
    /**
     * @return a String that represent the user's password
     */
    String getPassword();	
    
    /**
     * @return UserRole
     */
    UserRole getUserRole();   

    /**
     * Enum with the System User Roles.
     * @author Brunetti Nicola
     */
    enum UserRole {
        ADMINISTRATOR,
        USER;
    }
}
