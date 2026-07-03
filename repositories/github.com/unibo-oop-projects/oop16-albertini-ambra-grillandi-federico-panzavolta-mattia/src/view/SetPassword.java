package view;

/**
 *This is the Interface to build class for set the password of owner and staff.
 */
public interface SetPassword {

    /**
     * This method associate at submit button an ActionListener to set the owner's password.
     */
    void listenerForOwner();

    /**
     * This method associate at submit button an ActionListener to set the staff's password.
     */
    void listenerForStaff();
}