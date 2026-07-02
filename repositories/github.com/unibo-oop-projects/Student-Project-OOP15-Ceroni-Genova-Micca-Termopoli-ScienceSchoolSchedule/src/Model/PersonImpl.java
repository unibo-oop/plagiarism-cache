package Model;

import java.io.Serializable;

/**
 * This class implements the class Person, passing the name e surname 
 * 
 * @author Francesco Ceroni
 * 
 */

public class PersonImpl implements Person,Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String surname;

    public PersonImpl(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        
        return this.surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String toString(){
        return this.surname+" "+ this.name;
    }

}
