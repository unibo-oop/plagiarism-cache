package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * return the parameters typed, in respective JTextFields and JRadioButtons,
 * in order to register a new member to the staff.
 */
public interface AddStaffView {

	/**
     * Return the name of the staff member.
     * 
     * @return name
     */
    String getName();

    /**
     * Return the last name of the staff member.
     * 
     * @return last name
     */
    String getSurname();

    /**
     * Return the Fiscal code of the staff member.
     * 
     * @return fiscal code
     */
    String getFiscalCode();

    /**
     * Return the phone number of the staff member.
     * 
     * @return phone number
     */
    String getTelephoneNumber();

    /**
     * Return the e-mail of the staff member.
     * 
     * @return e-mail
     */
    String getEmail();

    /**
     * Return the user name chosen by the staff member. 
     * 
     * @return userName
     */
    String getUsername();

    /**
     * Return the password chosen by the staff member.
     * 
     * @return password
     */
    String getPassword();

    /**
     * Return the password that the staff member confirmed.
     * 
     * @return password confirmed
     */
    String getConfirmPassWord();
    
    /**
     * Return the gender of the staff member.
     * 
     * @return gender
     */
    String getGender();

    /**
     * Return the birth date of the staff member in the format of dd/mm/yyyy;
     * 
     * @return date of birth
     */
    String getBirthDate();

    /**
     * The listener used for the confirm button, to save the reservation and proceed. 
     * 
     * @param confirmListener
     */
    void addConfirmListener(ActionListener confirmListener);

    /**
     * The back listener used to come back to the previous interface
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
