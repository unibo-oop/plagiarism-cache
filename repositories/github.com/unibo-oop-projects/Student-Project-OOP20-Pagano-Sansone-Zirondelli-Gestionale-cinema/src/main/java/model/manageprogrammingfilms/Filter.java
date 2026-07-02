package model.manageprogrammingfilms;

import java.util.function.Predicate;
/** 
 * Describe a generic filter.
 * @param <X> type to filter 
 * */
public interface Filter<X> {
    /** 
     * Get used predicate.
     * @return predicate to filter
     * */
    Predicate<X> getPredicate();
}
