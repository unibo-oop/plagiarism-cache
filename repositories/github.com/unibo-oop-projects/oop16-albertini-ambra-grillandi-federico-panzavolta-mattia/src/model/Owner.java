package model;

/**
 * This interface represents the passwords used by owner and staff of the multiplex.
 * 
 */
public interface Owner {

    /**
     * Return the owner's password.
     * 
     * @return the password used by the owner
     */
    String getOwnerPassword();

    /**
     * Set the owner's password.
     * 
     * @param ownerPassword
     *            the password of the multiplex's owner
     */
    void setOwnerPassword(String ownerPassword);

    /**
     * Return the staff's password.
     * 
     * @return the password used by the staff
     */
    String getStaffPassword();

    /**
     * Set the staff's password.
     * 
     * @param staffPassword
     *             the password of the multiplex's staff
     */
    void setStaffPassword(String staffPassword);
}
