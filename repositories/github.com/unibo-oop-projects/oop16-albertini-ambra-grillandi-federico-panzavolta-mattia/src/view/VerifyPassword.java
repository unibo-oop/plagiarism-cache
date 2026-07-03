package view;

/**
 * This is the Interface to build class for verify the password of owner and staff.
 */
public interface VerifyPassword {

    /**
     * This method set the action listener for control the owner's password.
     * 
     * @param startingView
     *            the starting view to set invisible after the insert of the correct password.
     */
    void listenerForOwner(StartingView startingView);

    /**
     * This method set the action listener for control the staff's password.
     * 
     * @param startingView
     *            the starting view to set invisible after the insert of the correct password.
     */
    void listenerForStaff(StartingView startingView);
}