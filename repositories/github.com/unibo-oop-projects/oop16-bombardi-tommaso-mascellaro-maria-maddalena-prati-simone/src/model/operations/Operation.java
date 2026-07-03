package model.operations;

import java.io.Serializable;

import model.admin.Pair;

/**
 * Interface for a generic operation.
 * The interface extends serializable interface,
 *      so that an object of a class which implements this interface can be written on file.
 */
public interface Operation extends Serializable {

    /**
     * Get description.
     * 
     * @return a string which represents the description of the operation type
     */
    String getDescription();

    /**
     * Get detail.
     * 
     * @return a string which represents the detail of the operation
     */
    String getDetail();

    /**
     * Get information.
     * 
     * @return a string which represents some informations about the operation
     */
    String getInfo();

    /**
     * Get buy price.
     * 
     * @return a double which represents the buy price
     */
    Double getPrice();

    /**
     * Get gain.
     * 
     * @return a pair with operation's proceeds and operation's gain
     */
    Pair<Double, Double> getGain();

}
