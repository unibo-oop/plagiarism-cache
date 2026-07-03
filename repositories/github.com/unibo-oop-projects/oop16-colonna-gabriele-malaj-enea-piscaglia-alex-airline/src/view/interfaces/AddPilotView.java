package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * return the parameter inserted in their respective fields,
 * in order to register the pilot.
 */
public interface AddPilotView {

    /**
     * Return the name of the pilot from the JTextField.
     * 
     * @return name
     */
    String getName();

    /**
     * Return the last name of the pilot from the JTextField.
     *      
     * @return last name
     */
    String getSurname();

    /**
     * Return the Fiscal code of the pilot from the JTextField.
     * 
     * @return fiscal code
     */
    String getFiscalCode();

    /**
     * Return the phone number of the pilot from the JTextField.
     * 
     * @return phone number
     */
    String getTelephoneNumber();
    
    /**
     * Return the e-mail of the pilot from the JTextField.
     * 
     * @return e-mail
     */
	String getEmail();

	/**
	 * Return the gender of the pilot from the JRadioButton.
	 * 
	 * @return gender
	 */
	String getPilotGender();
	
	/**
	 * Return the birth date of the pilot from the 3 JComboBoxes.
	 * 
	 * @return birth date
	 */
	String getPilotBirthDate();

	/**
	 * Return the pilot's license id typed in the JTextField.
	 * 
	 * @return
	 */
	String getPilotLicenseId();

	/**
     * The listener used for the confirm button, to save the reservation and proceed. 
     * 
     * @param confirmListener
     */
    void addConfirmListener(ActionListener confirmListener);

    /**
     * The back listener used to come back to the previous interface.
     * 
     * @param backListener
     */
    void addBackListener(ActionListener backListener);

    /**
     * Displays an error when confirm button is clicked and not all fields are compiled.
     * 
     * @param error
     */
    void displayErrorMessage(String error);
    
    /**
     * Displays a confirm message when confirm button
     * is clicked to ask if you want to proceed or not.
     * 
     * @param confirm
     */
    void displayConfirmMessage(String confirm);

    /**
     * This method is used to dispose of the current JFrame and pass onto the next one.
     */
    void close();
}
