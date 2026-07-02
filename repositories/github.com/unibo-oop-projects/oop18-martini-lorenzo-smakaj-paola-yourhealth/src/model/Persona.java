package model;

import java.time.LocalDate;

public interface Persona {

	/**
     * name getter.
     * @return person's name
     */
    String getNome();
    
    /**
     * surname getter.
     * @return person's surname
     */
    String getCognome();
    
    /**
     * sex getter.
     * @return person's sex
     */
    String getSesso();
    
    /**
     * birthplace getter.
     * @return person's birthplace
     */
    String getLuogoNascita();
    
    /**
     * birth date getter.
     * @return person's birth date
     */
    LocalDate getDataNascita();
}
