package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * take all the parameters in the: 
 * -  JCombobox
 * -  JRadioButtons
 * -  JCheckboxes
 * -  JTextfields
 * and return each one of them using the methods below methods.
 */
public interface AddBookingView {

	/**
	 * Return the object selected in the JCombobox.
	 * 
	 * @return selected item
	 */
    Object getFlight();

    /**
     * Return the class chosen between Economy, Business, First,
     * to travel with.
     * 
     * @return traveling class
     */
    String getTravelClass();

    /**
     * Return the name of the client making the reservation.
     * 
     * @return name
     */
    String getName();

    /**
     * Return the last name of the client making the reservation.
     * 
     * @return last name
     */
    String getSurname();

    /**
     * Return the Fiscal code of the client making the reservation.
     * 
     * @return fiscal code
     */
    String getFiscalCode();

    /**
     * Return the phone number of the client making the reservation.
     * 
     * @return phone number
     */
    String getTelephoneNumber();

    /**
     * Return the e-mail of the client making the reservation.
     * 
     * @return e-mail
     */
    String getEmail();
    
    /**
     * Return the age of the client making the reservation.
     * 
     * @return age
     */
    String getAge();

    /**
     * Return the document id of the client making the reservation.
     * 
     * @return document id
     */
    String getDocumentId();

    /**
     * Return the weight of the client's luggage. 
     * Two choices: STANDART_WEIGHT or BUSINESS_WEIGHT, both are constants,
     * already defined.
     * 
     * @return luggage weight
     */
    int getWeight();

    /**
     * This is used to add the available flights to the JCombobox,
     * where you can select/chose the desired flight to book.
     * 
     * @param object
     */
    void addFlightToCmbx(Object object);
    
    /**
     * The confirm listener for the confirm button.
     * 
     * @param confirmListener
     */
    void addConfirmListener(ActionListener confirmListener);

    /**
     * The back listener for the back button.
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
