package Controller;

import Model.ErrorException;
import Model.WarningException;

/**
 * @author Massimiliano Micca
 *
 */
public interface ValidateWarningInterface {
    /**
     * this method is used for check the hours in a day and the day in a week.
     * 
     * @param cont
     * @throws WarningException
     * @throws ErrorException
     */
    void validateWARNING(Reservation cont) throws WarningException, ErrorException;
}