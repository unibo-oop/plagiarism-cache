package com.biaren.sportclubmanager.corebundle.model.interfaces;

import java.util.Date;

/**
 * Represent a Contract signed between two parts.
 * @author nbrunetti
 *
 */
public interface Contract {
    
    /**
     * Get the start date of contract
     * @return {@link Date} of start of the contract
     */
    Date getInit();
    
    /**
     * Get the end date of contract
     * @return {@link Date} of end of the contract
     */
    Date getEnd();
    
    /**
     * Get Salary for year
     * @return {@link Double} salary for year
     */
    double getSalaryForYear();
}
