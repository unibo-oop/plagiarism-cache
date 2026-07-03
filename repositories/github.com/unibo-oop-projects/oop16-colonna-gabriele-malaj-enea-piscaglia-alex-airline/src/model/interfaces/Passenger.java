package model.interfaces;

/**
 * 
 * Represents a passenger.
 */
public interface Passenger extends Person {

    /**
     * 
     * @return the age of the passenger
     */
    long getAge();

    /**
     * 
     * @return the document identifier of the passenger
     */
    String getDocumentId();

}