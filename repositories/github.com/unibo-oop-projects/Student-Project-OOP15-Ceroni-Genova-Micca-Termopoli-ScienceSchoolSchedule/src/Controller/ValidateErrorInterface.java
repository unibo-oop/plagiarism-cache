package Controller;

import Model.ErrorException;

/**
 * @author Massimiliano Micca
 *
 */
public interface ValidateErrorInterface {

    /**
     * this method is used to check, if the cell is free or not if isn't free
     * return a ErrorException.
     * 
     * @param cont
     * @return
     * @throws ErrorException
     */
    boolean validateErrore(Reservation cont) throws ErrorException;

}