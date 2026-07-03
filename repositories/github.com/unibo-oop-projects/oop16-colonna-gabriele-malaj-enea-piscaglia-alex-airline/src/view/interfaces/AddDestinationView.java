package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * return each of the parameters inserted in the respective JTextfields.
 */
public interface AddDestinationView {

	/**
	 * Return the destination id.
	 *  
	 * @return destination id
	 */
    String getDestinationId();

    /**
	 * Return the name of the country where airplane will be headed.
     * 
     * @return country name
     */
    String getCountry();

    /**
     * Return the name of the city in the country where the plane is headed.
     * 
     * @return city name
     */
    String getCity();
    
    /**
     * Return the name of airport where the airplane will land.
     * 
     * @return airport name
     */
    String getAirport();

    /**
     * The listener used for the confirm button, to save the actions performed and proceed. 
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
