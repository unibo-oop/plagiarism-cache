package view.interfaces;

import java.awt.event.ActionListener;

/**
 * The class that will implement this interface will have to
 * create 9 buttons, each representing the:
 * -  Planes view
 * -  Flights view
 * -  Bookings view
 * -  Pilots view
 * -  Destinations view
 * -  Staff view
 * -  Flight attendants view
 * -  Monthly income graph
 * -  Lougout to Login view
 */
public interface MainMenuView {

	/**
     * The ActionListener for the planes button.
     * 
     * @param planeListener
     */
    void addPlaneListener(ActionListener planeListener);

    /**
     * The ActionListener for the destinations button.
     * 
     * @param destListener
     */
    void addDestinationListener(ActionListener destListener);

    /**
     * The ActionListener for the pilots button.
     * 
     * @param pilotListener
     */
    void addPilotListener(ActionListener pilotListener);

    /**
     * The ActionListener for the flights button.
     * 
     * @param flightListener
     */
    void addFlightListener(ActionListener flightListener);

    /**
     * The ActionListener for the bookings button.
     * 
     * @param bookingListener
     */
    void addBookingListener(ActionListener bookingListener);

    /**
     * The ActionListener for the staff button.
     * 
     * @param staffListener
     */
    void addStaffListener(ActionListener staffListener);

    /**
     * The ActionListener for the logout button.
     * 
     * @param logoutListener
     */
    void addLogoutListener(ActionListener logoutListener);
    
    /**
     * The ActionListener for the monthly income button.
     * 
     * @param incomeListener
     */
    void addMonthlyIncomeListener(ActionListener incomeListener);
    
    /**
     * The ActionListener for the flight attendants button.
     * 
     * @param attendatnsListener
     */
    void addFlightAttendantListener(ActionListener attendatnsListener);
   
    /**
     * Displays an error when confirm button is clicked and not all fields are compiled.
     * 
     * @param error
     */
    void displayErrorMessage(String error);
    
    /**
     * This method is used to dispose of the current JFrame and pass onto the next one.
     */
    void close();
    }
