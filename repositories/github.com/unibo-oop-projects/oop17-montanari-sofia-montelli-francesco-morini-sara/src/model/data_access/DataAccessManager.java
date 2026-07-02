package model.data_access;

import java.util.Set;

/**
 * Implements specific method for the file.
 * @param <X> a generic object for which data is to be managed
 */
public interface DataAccessManager<X> {
    /**
     * @return the set of all elements
     */
    Set<X> getSet();
    /**
     * This method save a new element.
     * @param x the new element to save
     */
    void saveNewElement(X x);
    /**
     * Update the file with the modified element x.
     * @param x is the element to update
     */
    void updateElement(X x);
    /**
     * removes the specified element from the set.
     * @param x the element to remove
     */
    void removeElement(X x);
}
