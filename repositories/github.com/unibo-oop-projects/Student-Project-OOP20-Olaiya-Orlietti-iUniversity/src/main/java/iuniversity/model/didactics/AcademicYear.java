package iuniversity.model.didactics;

import java.time.LocalDate;

public interface AcademicYear {

    /**
     * 
     * @return the start date of the academic year
     */
    LocalDate getStart();
    
    /**
     * 
     * @return the end date of the academic year
     */
    LocalDate getEnd();
    
    /**
     * 
     * @return the start date of the first term
     */
    LocalDate getFirstTermStart();
    
    /**
     * 
     * @return the end date of the first term
     */
    LocalDate getFirstTermEnd();
    
    /**
     * 
     * @return the start date of the second term
     */
    LocalDate getSecondTermStart();
    
    /**
     * 
     * @return the end date of the second term
     */
    LocalDate getSecondTermEnd();
    
}
