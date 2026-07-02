package model.manageprogrammingfilms;

import java.util.Comparator;
/** 
 * Describe a parameterized sorter.
 * @param <X> type to sorter.
 * */
public interface Sorter<X> {
    /** 
     * Get used comparator.
     * @return comparator
     * */ 
    Comparator<X> getComparator();

}
