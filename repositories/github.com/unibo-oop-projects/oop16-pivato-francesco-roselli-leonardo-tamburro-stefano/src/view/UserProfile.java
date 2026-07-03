package view;

import java.time.LocalDate;

import javafx.scene.image.Image;
import model.enumerations.SubscriptionType;
import model.interfaces.User;

/**
 * 
 * Interface provided by view and used by controller to retrieve data
 * @author Ste
 * 
 */
public interface UserProfile {
	
	/**
	 * @return name, null if the field is empty
	 */
	String getName();
	
	/**
	 * @return surname, null if the field is empty
	 */
	String getSurname();
	
	/**
	 * @return birthdate, null if the field is empty
	 */
	LocalDate getBirthdate();
	
	/**
	 * @return phone number, null if the field is empty
	 */
	String getPhoneNumber();
	
	/**
	 * @return e-mail address, null if the field is empty
	 */
	String getEmail();
	
	/**
	 * @return fiscal code, null if the field is empty
	 */
	String getTaxCode();
	
	/**
	 * @return the profile image set by user
	 */
	Image getProfileImage();
	
	/**
	 * @return type of subscription the user purchased
	 */
	SubscriptionType getSubscriptionType();
	
	/**
	 * @return date in which the subscription become valid, null if the field is empty
	 */
	LocalDate getValidFrom();
	
	/**
	 * @return expiration date of subscription, null if the field is empty
	 */
	LocalDate getValidTo();
	
	/**
	 * @return payment date of subscription, null if the field is empty
	 */
	LocalDate getPaymentDate();
	
	/**
     * function used to set all the fields in order to simplify modification insertions
     * @param user data
     */
    public void setFields(User user);

}
