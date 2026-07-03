package model.interfaces;
import model.enumerations.*;

/**
 * Interface to manage
 * login of one admin
 */

public interface Validator {

   /**
    * manages admin's login
    * 
    * @param id
    *           the Id of the admin
    * @param password
    *  			the password entered by the admin
    * @return the type of error if there is, ALL_RIGHT otherwise
    */
	
	public Status validate(String id, String password);
	
}
