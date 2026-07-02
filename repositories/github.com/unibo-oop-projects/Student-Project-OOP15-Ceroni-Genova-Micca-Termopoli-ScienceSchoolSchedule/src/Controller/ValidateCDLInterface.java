package Controller;

import Model.ErrorException;
import Model.WarningException;

/**
 * @author Massimiliano Micca
 *
 */
public interface ValidateCDLInterface {

    /**
     * 
     * this method check if the course are in the same time slot if they can be
     * in the same time slot, launch a warning otherwise throws an error
     * 
     * @param res
     * @throws WarningException
     * @throws ErrorException
     */
    void validate(Reservation res) throws WarningException, ErrorException;

}