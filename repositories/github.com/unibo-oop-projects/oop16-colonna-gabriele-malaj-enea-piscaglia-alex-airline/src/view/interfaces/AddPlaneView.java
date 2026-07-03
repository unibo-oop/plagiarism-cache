package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * return the parameter inserted in the respective JTextFields
 * to create a plane with different class seats.
 */
public interface AddPlaneView {

	/**
	 * Return the name of the airline of the plane
	 * 
	 * @return airline name
	 */
    String getAirlineName();

    /**
     * Return the number of seats in the economy class
     * 
     * @return economy class seats
     */
    String getNEconomyClassTotalSeats();

    /**
     * Return the number of seats in the first class
     * 
     * @return first class seats
     */
    String getNBusinessClassTotalSeats();

    /**
     * Return the number of seats in the business class
     * 
     * @return business class seats
     */
    String getNFirstClassTotalSeats();

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
