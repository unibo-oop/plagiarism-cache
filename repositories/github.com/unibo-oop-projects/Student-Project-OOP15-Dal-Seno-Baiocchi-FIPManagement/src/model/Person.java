package model;

import java.io.Serializable;
import java.util.Date;
/**
 * Define a person
 * @author lucadalseno
 *
 */
public interface Person extends Serializable {
    /**
     * Getter for name
     * @return the name
     */
    String getName();
    
    /**
     * Getter for surname
     * @return the surname
     */
    String getSurname();
    
    /**
     * Getter for birth
     * @return the date of birth
     */
    Date getBirth();
    
    /**
     * Getter for CF
     * @return the cf
     */
    String getCF();
    /**
     * Setter for name
     * @param name
     */
    void setName(String name);
    /**
     * Setter for surname
     * @param surname
     */
    void setSurname(String surname);
    /**
     * Setter for birth
     * @param birth
     */
    void setBirth(Date birth);
    /**
     * Setter for CF
     * @param cf
     */
    void setCF(String cf);
}
