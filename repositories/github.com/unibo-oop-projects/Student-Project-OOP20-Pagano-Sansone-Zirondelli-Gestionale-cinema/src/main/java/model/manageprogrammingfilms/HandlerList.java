package model.manageprogrammingfilms;

import java.util.List;

/** Describe an handler that can filter and sorted a generic list. 
 * @param <X> type to handled.
 * */
public interface HandlerList<X> {
    /** 
     * Filter a specific list with a specific filter.
     * @param list list to filter
     * @param filter filter to use
     * @return get filtered list
     * */
    List<X> filterBy(List<X> list, Filter<X> filter);
    /** 
     * Sort a specific list with a specific sorter.
     * @param list list to sort
     * @param sorter sorter to use
     * @return get sorted list
     * */
    List<X> sortBy(List<X> list, Sorter<X> sorter);
}
