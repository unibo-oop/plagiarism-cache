package controller.interfaces;

import java.text.ParseException;

/**
 * Add Staff Controller interface.
 */
public interface AddStaffController {

    /**
     * Adds a staff member to the list of staff members.
     * 
     * @throws ParseException if date of birth's format is not correct
     */
    void addStaff() throws ParseException;

}