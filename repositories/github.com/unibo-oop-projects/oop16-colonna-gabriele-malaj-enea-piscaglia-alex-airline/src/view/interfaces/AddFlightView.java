package view.interfaces;

import java.awt.event.ActionListener;

public interface AddFlightView {

	/**
	 * Return the selected plane from the list in the JCombobox.
	 * 
	 * @return plane
	 */
    String getPlane();

    /**
     * Return the selected Destination from the list in the JCombobox.
     * 
     * @return destination
     */
    String getDestination();

    /**
     * Return the time of departure in the format of hh:mm.
     * 
     * @return departure time
     */
    String getDepartureTime();

    /**
     * Return the time of arrival in the format of hh:mm.
     * 
     * @return arrival time
     */
    String getArrivalTime();

    /**
     * Return the base price typed in JTextfield for the flight ticket.
     * 
     * @return price
     */
    String getBasicPrice();

    /**
     * Return the selected pilot from the list in the JCombobox.
     * 
     * @return pilot
     */
    String getPilot();

    /**
     * Return the selected plane from the list in the JCombobox.
     * 
     * @return coo-pilot
     */
    String getCopilot();
    
    /**
     * Return the selected flight attendants from the list in the CheckComboBox.
     * 
     * @return flight attendants
     */
    String[] getFlightAttendants();
    
    /**
     * Return the date of when the plane will take off.
     * 
     * @return departure date
     */
    String getDepartureDate();

    /**
     * Return the date of when the plane will arrive to destination.
     * 
     * @return arrival date
     */
    String getArrivalDate();
    
    /**
     * This method adds items, then to be shown in the plane JCombobox.
     * 
     * @param planeId
     */
    void addPlaneToCmbx(Object planeId);

    /**
     * This method adds items, then to be shown in the destination JCombobox.
     * 
     * @param destinationId
     */
    void addDestinationToCmbx(Object destinationId);

    /**
     * This method adds items, then to be shown in the pilot JCombobox.
     * 
     * @param pilotId
     */
    void addPilotToCmbx(Object pilotId);

    /**
     * This method adds items, then to be shown in the coo-pilot JCombobox.
     * 
     * @param copilotId
     */
    void addCopilotToCmbx(Object copilotId);

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
