package model.interfaces;

/**
 * 
 * Represents a staff member.
 */
public interface Staff extends Person {

    /**
     * 
     * @return the staff member's username
     */
    String getUsername();

    /**
     * 
     * @return the staff member's password
     */
    String getPassword();

    /**
     * Sets the staff member's password.
     * 
     * @param pwd    the staff member's password
     */
    void setPassword(String pwd);

}