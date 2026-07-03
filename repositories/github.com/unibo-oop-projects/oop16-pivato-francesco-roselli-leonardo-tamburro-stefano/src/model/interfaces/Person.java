package model.interfaces;

import java.time.LocalDate;

import javafx.scene.image.Image;

public interface Person {
	
	/**
     * @return person's name
     */
    
	String getFirstName();

    /**
     * @return person's surname
     */
    
	String getLastName();
	
	/**
     * @return person's tax code
     */
	
	String getTaxCode();

    /**
     * @return person's birthDate
     */
	
	public LocalDate getBirthday();
	
	/**
	 * @return person's age
	 */
	
	String getEmail();
	
	/**
	 * @return person's telephone number
	 */
	
	public String getTelephonNumber();
	
	/**
	 * Set an imageProfile
	 * @param imageProfile to be setted
	 */

	public void setImageProfile(Image imageProfile);
	
	/**
	 * @return Imege of user
	 */

	public Image getImageProfile();

}
