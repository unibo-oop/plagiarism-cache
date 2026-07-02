package modelimpl.manageprogrammedfilms;

import java.util.List;
import java.util.stream.Collectors;

import model.manageprogrammingfilms.Filter;
import model.manageprogrammingfilms.HandlerList;
import model.manageprogrammingfilms.Sorter;
/**
 * Describe a generic HandlerList.
 * @param <X> type in handler list.
 * */
public final  class HandlerListImpl<X> implements HandlerList<X> {
    /** 
     * Filter a specific list with a specific filter.
     * @param list list to filter
     * @param filter filter to use
     * @return  filtered list
     * */
    @Override
    public List<X> filterBy(final List<X> list, final Filter<X> filter) {
        return list.stream()
                .filter(filter.getPredicate())
                .collect(Collectors.toList());
    }
    /** 
     * Sort a specific list with a specific sorter.
     * @param list list to sort
     * @param sorter sorter to use
     * @return sorted list
     * */
    @Override
    public List<X> sortBy(final List<X> list, final Sorter<X> sorter) {
        return list.stream()
                .sorted(sorter.getComparator())
                .collect(Collectors.toList());
    }

}
