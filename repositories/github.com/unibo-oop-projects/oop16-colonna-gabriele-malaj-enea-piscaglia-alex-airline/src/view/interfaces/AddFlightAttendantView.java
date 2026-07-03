package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * return the parameters typed, in respective JTextFields and JRadioButtons,
 * in order to register a new flight attendant. 
 */
public interface AddFlightAttendantView {

	/**
     * Return the name of the flight attendant.
     * 
     * @return name
     */
    String getName();

    /**
     * Return the last name of the flight attendant.
     * 
     * @return last name
     */
    String getSurname();

    /**
     * Return the Fiscal code of the flight attendant.
     * 
     * @return fiscal code
     */
    String getFiscalCode();

    /**
     * Return the phone number of the flight attendant.
     * 
     * @return phone number
     */
    String getTelephoneNumber();

    /**
     * Return the e-mail of the flight attendant.
     * 
     * @return e-mail
     */
    String getEmail();
    
    /**
     * Return the gender of the flight attendant.
     * 
     * @return gender
     */
    String getAttendantGender();
	
    /**
     * Return the birth date of the flight attendant in the format of dd/mm/yyyy.
     * 
     * @return birth date
     */
	String getAttendantBirthDate();

	/**
     * The listener used for the confirm button, to actions performed and proceed. 
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
